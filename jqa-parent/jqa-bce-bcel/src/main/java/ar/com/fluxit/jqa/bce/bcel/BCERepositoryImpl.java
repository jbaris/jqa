/*******************************************************************************
 * Copyright (c) 2013 Flux IT.
 * 
 * This file is part of JQA (http://github.com/fluxitsoft/jqa).
 * 
 * JQA is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 * 
 * JQA is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with JQA. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package ar.com.fluxit.jqa.bce.bcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sourceforge.pmd.ast.ASTAllocationExpression;
import net.sourceforge.pmd.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.ast.ASTCompilationUnit;
import net.sourceforge.pmd.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.ast.ASTImportDeclaration;
import net.sourceforge.pmd.ast.ASTName;
import net.sourceforge.pmd.ast.ASTPackageDeclaration;
import net.sourceforge.pmd.ast.CharStream;
import net.sourceforge.pmd.ast.JavaCharStream;
import net.sourceforge.pmd.ast.JavaParser;
import net.sourceforge.pmd.ast.SimpleNode;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.ConstantCP;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Signature;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.generic.ATHROW;
import org.apache.bcel.generic.CPInstruction;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldOrMethod;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.LocalVariableInstruction;
import org.jaxen.JaxenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.fluxit.jqa.bce.BCERepository;
import ar.com.fluxit.jqa.bce.Type;
import ar.com.fluxit.jqa.bce.TypeFormatException;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class BCERepositoryImpl implements BCERepository {

	private static Logger LOGGER = LoggerFactory
			.getLogger(BCERepositoryImpl.class);

	private final String javaVersion;
	private final CacheManager cacheManager;
	private final File[] sourcesDirs;

	// FIXME replace tokens "Class" to "Type" ("Type" is generic)
	public BCERepositoryImpl(Collection<File> classPathFiles,
			String javaVersion, File[] sourcesDirs) {
		ClassPathLoader.INSTANCE.setClassPath(classPathFiles);
		this.javaVersion = javaVersion;
		this.sourcesDirs = sourcesDirs;
		this.cacheManager = CacheManager.newInstance(getClass()
				.getResourceAsStream("/ehcache.xml"));
	}

	private void addToResult(int sourceLine, Type type,
			Map<Type, Collection<Integer>> result) {
		Collection<Integer> collection = result.get(type);
		if (collection == null) {
			collection = new HashSet<Integer>();
		}
		collection.add(sourceLine);
		result.put(type, collection);
	}

	private SimpleNode doGetTypeNode(SimpleNode node, String typeShortName) {
		if (typeShortName.contains("$")) {
			// Is inner type
			String currentTypeName = typeShortName.substring(0,
					typeShortName.indexOf("$"));
			String nextTypeName = typeShortName.substring(typeShortName
					.indexOf("$") + 1);
			SimpleNode declaration = findFirstLevelTypeDeclarationNode(node,
					currentTypeName);
			return doGetTypeNode(declaration, nextTypeName);
		} else {
			return findFirstLevelTypeDeclarationNode(node, typeShortName);
		}
	}

	private List<SimpleNode> findChildNodesWithXPath(Type parentType,
			String xpathString) {
		SimpleNode typeNode = getTypeNode(parentType);
		try {
			@SuppressWarnings("unchecked")
			List<SimpleNode> result = typeNode
					.findChildNodesWithXPath(xpathString);
			for (Iterator<SimpleNode> iterator = result.iterator(); iterator
					.hasNext();) {
				SimpleNode simpleNode = iterator.next();
				if (simpleNode
						.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class) != typeNode) {
					iterator.remove();
				}
			}
			return result;
		} catch (JaxenException e) {
			throw new IllegalArgumentException(
					"Error while looking for child of " + parentType.getName()
							+ " with xpath " + xpathString, e);
		}
	}

	private SimpleNode findFirstLevelAnonymousTypeDeclarationNode(
			SimpleNode parentNode, String namedTypeShortName) {
		List<SimpleNode> declarations = findFirstLevelAnonymousTypeDeclarationNodes(
				parentNode, namedTypeShortName);
		return declarations.get(Integer.valueOf(namedTypeShortName) - 1);
	}

	private List<SimpleNode> findFirstLevelAnonymousTypeDeclarationNodes(
			SimpleNode parentNode, String namedTypeShortName) {
		List<SimpleNode> result = new ArrayList<SimpleNode>();
		for (int i = 0; i < parentNode.jjtGetNumChildren(); i++) {
			SimpleNode children = (SimpleNode) parentNode.jjtGetChild(i);
			if (!(children instanceof ASTClassOrInterfaceDeclaration || children instanceof ASTEnumDeclaration)) {
				if (children instanceof ASTAllocationExpression
						&& children
								.containsChildOfType(ASTClassOrInterfaceBody.class)) {
					// Is an anonymous type
					result.add(children);
				}
				result.addAll(findFirstLevelAnonymousTypeDeclarationNodes(
						children, namedTypeShortName));
			}
		}
		return result;
	}

	private SimpleNode findFirstLevelNamedTypeDeclarationNode(
			SimpleNode parentNode, String namedTypeShortName) {
		List<SimpleNode> declarations = findFirstLevelNamedTypeDeclarationNodes(
				parentNode, namedTypeShortName);
		if (declarations.isEmpty()) {
			throw new IllegalArgumentException(
					"Can not find declaration line number of type: "
							+ namedTypeShortName);
		} else if (declarations.size() > 1) {
			LOGGER.warn("Multiple declaration line numbers found for the type: "
					+ namedTypeShortName);
		}
		return declarations.get(0);
	}

	private List<SimpleNode> findFirstLevelNamedTypeDeclarationNodes(
			SimpleNode parentNode, String namedTypeShortName) {
		List<SimpleNode> result = new ArrayList<SimpleNode>();
		for (int i = 0; i < parentNode.jjtGetNumChildren(); i++) {
			SimpleNode children = (SimpleNode) parentNode.jjtGetChild(i);
			if (children instanceof ASTClassOrInterfaceDeclaration
					|| children instanceof ASTEnumDeclaration) {
				if (children.getImage().equals(namedTypeShortName)) {
					result.add(children);
				}
			} else {
				result.addAll(findFirstLevelNamedTypeDeclarationNodes(children,
						namedTypeShortName));
			}
		}
		return result;
	}

	private SimpleNode findFirstLevelTypeDeclarationNode(SimpleNode node,
			String typeShortName) {
		if (isAnonymous(typeShortName)) {
			return findFirstLevelAnonymousTypeDeclarationNode(node,
					typeShortName);
		} else {
			return findFirstLevelNamedTypeDeclarationNode(node, typeShortName);
		}
	}

	@Override
	public Map<Type, Collection<Integer>> getAllocations(final Type type) {
		// TODO cache
		final Map<Type, Collection<Integer>> result = new HashMap<Type, Collection<Integer>>();
		final Visitor visitor = new EmptyVisitor() {

			@Override
			public void visitCode(final Code code) {
				final ConstantPool constantPool = getWrappedType(type)
						.getConstantPool();
				final Map<Instruction, Integer> instructionOffset = new HashMap<Instruction, Integer>();
				iterateInstructions(code, type, instructionOffset,
						new org.apache.bcel.generic.EmptyVisitor() {

							@Override
							public void visitNEW(org.apache.bcel.generic.NEW obj) {
								final int sourceLine = getSourceLineNumber(
										code, instructionOffset.get(obj));
								ConstantPoolGen cpg = new ConstantPoolGen(
										constantPool);
								addToResult(sourceLine,
										BcelJavaType.create(obj.getType(cpg)),
										result);
							};

							@Override
							public void visitNEWARRAY(
									org.apache.bcel.generic.NEWARRAY obj) {
								final int sourceLine = getSourceLineNumber(
										code, instructionOffset.get(obj));
								addToResult(sourceLine,
										BcelJavaType.create(obj.getType()),
										result);
							};

						});
			}
		};
		new DescendingVisitor(getWrappedType(type), visitor).visit();
		// FIXME remove the bellow code?
		// Prevent concurrent modification
		Map<Type, Collection<Integer>> anonymousAllocations = new HashMap<Type, Collection<Integer>>();
		for (Type allocationType : result.keySet()) {
			// Visit anonymous types
			if (allocationType.isAnonymous()) {
				anonymousAllocations.putAll(getAllocations(allocationType));
			}
		}
		result.putAll(anonymousAllocations);
		return result;
	}

	private CacheManager getCacheManager() {
		return this.cacheManager;
	}

	private ASTCompilationUnit getCompilationUnit(Type type) {
		final Cache cache = getCacheManager().getCache("COMPILATION_UNIT");
		Element result = cache.get(type.getName());
		if (result == null) {
			InputStream sourceFile = null;
			try {
				sourceFile = getSourceFile(type);
				final CharStream stream = new JavaCharStream(sourceFile);
				final JavaParser javaParser = new JavaParser(stream);
				if ("1.5".equals(this.javaVersion)
						|| "1.6".equals(this.javaVersion)) {
					javaParser.setJDK15();
				}
				final ASTCompilationUnit compilationUnit = javaParser
						.CompilationUnit();
				result = new Element(type.getName(), compilationUnit);
				cache.put(result);
			} finally {
				if (sourceFile != null) {
					try {
						sourceFile.close();
					} catch (IOException e) {
						LOGGER.error("Can not close source file input stream",
								e);
					}
				}
			}
		}
		return (ASTCompilationUnit) result.getObjectValue();
	}

	@Override
	public Integer getDeclarationLineNumber(Type type) {
		SimpleNode typeNode = getTypeNode(type);
		return typeNode.getBeginLine();
	}

	private Type getException(ConstantPool constantPool,
			CPInstruction instruction) {
		ConstantCP constantRef = (ConstantCP) constantPool
				.getConstant(instruction.getIndex());
		String typeName = constantPool.getConstantString(
				constantRef.getClassIndex(), Constants.CONSTANT_Class);
		return BcelJavaType.create(typeName);
	}

	private Collection<Integer> getFieldSourceLines(Type parentType,
			Type fieldType, String fieldName) {
		List<Integer> result = new ArrayList<Integer>();
		String xpathString = String
				.format("//VariableDeclarator/../Type[.//ClassOrInterfaceType[@Image='%s' or @Image='%s'] or PrimitiveType[@Image='%s']]",
						fieldType.getName(), fieldType.getShortName(),
						fieldType.getShortName());
		List<SimpleNode> fieldTypeNodes = findChildNodesWithXPath(parentType,
				xpathString);
		if (fieldTypeNodes.isEmpty()) {
			throw new IllegalArgumentException("Field not found: " + fieldName);
		} else {
			for (SimpleNode fieldTypeNode : fieldTypeNodes) {
				result.add(fieldTypeNode.getBeginLine());
			}
			return result;
		}

	}

	private int getFirstFieldOrMethodInstructionSourceLine(Code code) {
		final InstructionList instructionList = new InstructionList(
				code.getCode());
		for (int i = 0; i < instructionList.size(); i++) {
			Instruction instruction = instructionList.getInstructions()[i];
			if (instruction instanceof FieldOrMethod) {
				int offset = instructionList.getInstructionPositions()[i];
				return getSourceLineNumber(code, offset);
			}
		}
		throw new IllegalArgumentException(
				"FieldOrMethodInstruction instruction not found at :" + code);
	}

	@Override
	public Collection<Type> getInterfaces(Type type) {
		try {
			org.apache.bcel.classfile.JavaClass[] interfaces;
			interfaces = org.apache.bcel.Repository
					.getInterfaces(getWrappedType(type));
			final List<Type> result = new ArrayList<Type>(interfaces.length);
			for (final org.apache.bcel.classfile.JavaClass interfaz : interfaces) {
				result.add(BcelJavaType.create(interfaz));
			}
			return result;
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private int getMethodSourceLine(final Method method, Type parentType) {
		Code code = method.getCode();
		if (isConstructor(method)) {
			// Constructors
			return getFirstFieldOrMethodInstructionSourceLine(code);
		} else {
			// Non constructors
			String xpathString = "//MethodDeclarator[@Image='"
					+ method.getName() + "']";
			if (method.getArgumentTypes().length == 0) {
				xpathString += "[not(.//FormalParameter)]";
			} else {
				for (org.apache.bcel.generic.Type argumentType : method
						.getArgumentTypes()) {
					Type argumentType2 = BcelJavaType.create(argumentType);
					xpathString += "[.//FormalParameter//*[@Image='"
							+ argumentType2.getShortName() + "']]";
				}
			}
			List<SimpleNode> fieldTypeNodes = findChildNodesWithXPath(
					parentType, xpathString);
			if (fieldTypeNodes.size() == 1) {
				return fieldTypeNodes.get(0).getBeginLine();
			} else if (fieldTypeNodes.size() > 1) {
				throw new IllegalArgumentException(
						"More than one method found for: " + method.getName()
								+ " at type: " + parentType.getName());
			} else {
				// fieldTypeNodes == 0
				if (xpathString
						.equals("//ConstructorDeclaration[count(FormalParameters/FormalParameter) = 0]")) {
					// Implicit constructor
					return getDeclarationLineNumber(parentType);
				} else {
					throw new IllegalArgumentException("Method not found: "
							+ method.getName() + " for type: "
							+ parentType.getName());
				}
			}
		}
	}

	private String getPackage(ASTCompilationUnit compilationUnit) {
		ASTPackageDeclaration packageDeclaration = compilationUnit
				.getFirstChildOfType(ASTPackageDeclaration.class);
		return packageDeclaration.getFirstChildOfType(ASTName.class).getImage();
	}

	@Override
	public File getSourceFile(String typeName, File[] sourceDirs)
			throws FileNotFoundException {
		for (File sourceDir : sourceDirs) {
			String sourceFile = sourceDir.getPath() + "/"
					+ typeName.replace(".", "/");
			if (sourceFile.contains("$")) {
				sourceFile = sourceFile.substring(0, sourceFile.indexOf('$'));
			}
			sourceFile += ".java";
			File result = new File(sourceFile);
			if (!result.exists()) {
				LOGGER.warn("Can not find the source code for type ["
						+ typeName + "] in [" + sourceDir + "]");
			} else {
				return result;
			}
		}
		throw new FileNotFoundException(
				"Can not find the source code for type [" + typeName + "]");
	}

	private InputStream getSourceFile(Type type) {
		return getSourceFile(type, this.sourcesDirs);
	}

	private InputStream getSourceFile(Type type, File[] sourcesDir2) {
		try {
			return new FileInputStream(getSourceFile(type.getName(),
					sourcesDir2));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(
					"Can not find the source code for type [" + type.getName()
							+ "]", e);
		}
	}

	private String getSourceLine(Type type, int sourceLine) {
		if (sourceLine < 1) {
			throw new IllegalArgumentException(
					"Line number must be greater than or equal to 1");
		}
		InputStream stream = null;
		try {
			stream = getSourceFile(type);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stream));
			String line = null;
			for (int i = 0; i < sourceLine; i++) {
				line = br.readLine();
			}
			return line;
		} catch (IOException e) {
			throw new IllegalStateException(
					"An error occured while reading source file", e);
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				LOGGER.error("Can not close source file input stream", e);
			}
		}
	}

	private int getSourceLineNumber(Code code, int offset) {
		final LineNumberTable lineNumberTable = code.getLineNumberTable();
		return lineNumberTable.getSourceLine(offset);
	}

	@Override
	public Collection<Type> getSuperClasses(Type type) {
		org.apache.bcel.classfile.JavaClass[] superClasses;
		try {
			superClasses = org.apache.bcel.Repository
					.getSuperClasses(getWrappedType(type));
			final List<Type> result = new ArrayList<Type>(superClasses.length);
			for (final org.apache.bcel.classfile.JavaClass superClass : superClasses) {
				result.add(BcelJavaType.create(superClass));
			}
			return result;
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public Map<Type, Collection<Integer>> getThrows(final Type type) {
		final Map<Type, Collection<Integer>> result = new HashMap<Type, Collection<Integer>>();
		JavaClass wrappedType = getWrappedType(type);
		final Visitor visitor = new EmptyVisitor() {

			@Override
			public void visitCode(Code code) {
				InstructionList instructionList = new InstructionList(
						code.getCode());
				Instruction lastInstruction = null;
				int lastOffset = 0;
				for (int i = 0; i < instructionList.size(); i++) {
					Instruction currentInstruction = instructionList
							.getInstructions()[i];
					int currentOffset = instructionList
							.getInstructionPositions()[i];
					if (currentInstruction instanceof ATHROW) {
						if (lastInstruction instanceof CPInstruction) {
							final int sourceLine = getSourceLineNumber(code,
									lastOffset);
							addToResult(
									sourceLine,
									getException(code.getConstantPool(),
											(CPInstruction) lastInstruction),
									result);
						}
					}
					lastInstruction = currentInstruction;
					lastOffset = currentOffset;
				}
			}

			@Override
			public void visitMethod(Method obj) {
				final ExceptionTable exceptionTable = obj.getExceptionTable();
				if (exceptionTable != null) {
					for (String exceptionName : exceptionTable
							.getExceptionNames()) {
						final int sourceLine = getMethodSourceLine(obj, type);
						addToResult(sourceLine,
								BcelJavaType.create(exceptionName), result);
					}
				}
			}

		};
		new DescendingVisitor(wrappedType, visitor).visit();
		return result;
	}

	private SimpleNode getTypeNode(Type type) {
		// TODO cache
		final ASTCompilationUnit compilationUnit = getCompilationUnit(type);
		return doGetTypeNode(compilationUnit, type.getShortName());
	}

	@Override
	public Map<Type, Collection<Integer>> getUses(final Type type) {
		// TODO cache
		final Map<Type, Collection<Integer>> result = new HashMap<Type, Collection<Integer>>();
		final Visitor visitor = new EmptyVisitor() {

			@Override
			public void visitCode(final Code code) {
				final ConstantPool constantPool = code.getConstantPool();
				final Map<Instruction, Integer> instructionOffset = new HashMap<Instruction, Integer>();
				iterateInstructions(code, type, instructionOffset,
						new org.apache.bcel.generic.EmptyVisitor() {

							@Override
							public void visitGETSTATIC(GETSTATIC obj) {
								final int sourceLine = getSourceLineNumber(
										code, instructionOffset.get(obj));
								ConstantPoolGen cpg = new ConstantPoolGen(
										constantPool);
								addToResult(sourceLine, BcelJavaType.create(obj
										.getReferenceType(cpg)), result);
							}

							@Override
							public void visitInvokeInstruction(
									InvokeInstruction obj) {
								final int sourceLine = getSourceLineNumber(
										code, instructionOffset.get(obj));
								ConstantPoolGen cpg = new ConstantPoolGen(
										constantPool);
								addToResult(sourceLine, BcelJavaType.create(obj
										.getReferenceType(cpg)), result);
							}

							@Override
							public void visitINVOKEVIRTUAL(INVOKEVIRTUAL obj) {
								ConstantPoolGen cpg = new ConstantPoolGen(
										constantPool);
								org.apache.bcel.generic.Type returnType = obj
										.getReturnType(cpg);
								BcelJavaType type = BcelJavaType
										.create(returnType);
								if (type != null) {
									final int sourceLine = getSourceLineNumber(
											code, instructionOffset.get(obj));
									addToResult(sourceLine, type, result);
								}
							}

							@Override
							public void visitLocalVariableInstruction(
									LocalVariableInstruction obj) {
								ConstantPoolGen cpg = new ConstantPoolGen(
										constantPool);
								org.apache.bcel.generic.Type returnType = obj
										.getType(cpg);
								BcelJavaType type = BcelJavaType
										.create(returnType);
								if (type != null) {
									final int sourceLine = getSourceLineNumber(
											code, instructionOffset.get(obj));
									addToResult(sourceLine, type, result);
								}
							}

						});
				// Catchs blocks exceptions
				for (CodeException codeException : code.getExceptionTable()) {
					String typeName = constantPool.getConstantString(
							codeException.getCatchType(),
							Constants.CONSTANT_Class);
					final LineNumberTable lineNumberTable = code
							.getLineNumberTable();
					final int sourceLine = lineNumberTable
							.getSourceLine(codeException.getHandlerPC());
					addToResult(sourceLine, BcelJavaType.create(typeName),
							result);
				}
			}

			private void visitExceptionTable(ExceptionTable exceptionTable,
					int sourceLine) {
				for (String exceptionName : exceptionTable.getExceptionNames()) {
					addToResult(sourceLine, BcelJavaType.create(exceptionName),
							result);
				}
			}

			@Override
			public void visitField(Field field) {
				// Skip generated fields
				if ((field.getAccessFlags() & Constants.ACC_SYNTHETIC) == 0) {
					final Type fieldType = BcelJavaType
							.create(org.apache.bcel.generic.Type.getType(field
									.getSignature()));
					Collection<Integer> sourceLines = getFieldSourceLines(type,
							fieldType, field.getName());
					for (Integer sourceLine : sourceLines) {
						addToResult(sourceLine, fieldType, result);
					}
				}
			}

			@Override
			public void visitJavaClass(JavaClass obj) {
				final int sourceLine = getDeclarationLineNumber(BcelJavaType
						.create(obj));
				for (String type : obj.getInterfaceNames()) {
					addToResult(sourceLine, BcelJavaType.create(type), result);
				}
				addToResult(sourceLine,
						BcelJavaType.create(obj.getSuperclassName()), result);
				for (Attribute attribute : obj.getAttributes()) {
					if (attribute instanceof Signature) {
						visitSignature((Signature) attribute, sourceLine);
					}
				}
			}

			@Override
			public void visitMethod(Method method) {
				if ((method.getAccessFlags() & Constants.ACC_SYNTHETIC) == 0) {
					final int sourceLineNumber = getMethodSourceLine(method,
							type);
					String sourceLine = getSourceLine(type, sourceLineNumber);
					final List<String> typeNames = TypeNameTranslator
							.signatureToTypeNames(method.getSignature());
					for (final String typeName : typeNames) {
						BcelJavaType methodType = BcelJavaType.create(typeName);
						if (methodType != null
								&& sourceLine.contains(methodType
										.getShortName())) {
							addToResult(sourceLineNumber, methodType, result);
						}
					}
					for (Attribute attribute : method.getAttributes()) {
						if (attribute instanceof Signature) {
							visitSignature((Signature) attribute,
									sourceLineNumber);
						} else if (attribute instanceof ExceptionTable) {
							visitExceptionTable((ExceptionTable) attribute,
									sourceLineNumber);
						}
					}
				}
			}

			public void visitSignature(Signature obj, int sourceLine) {
				final ConstantUtf8 constantString = (ConstantUtf8) obj
						.getConstantPool().getConstant(obj.getSignatureIndex());
				String signature = constantString.getBytes();
				for (String type : TypeNameTranslator
						.signatureToTypeNames2(signature)) {
					addToResult(sourceLine, BcelJavaType.create(type), result);
				}
			}

		};
		new DescendingVisitor(getWrappedType(type), visitor).visit();
		// Add throws
		for (Entry<Type, Collection<Integer>> entry : getThrows(type)
				.entrySet()) {
			for (Integer sourceLine : entry.getValue()) {
				addToResult(sourceLine, entry.getKey(), result);
			}
		}
		return result;
	}

	private org.apache.bcel.classfile.JavaClass getWrappedType(Type type) {
		return ((BcelJavaType) type).getWrapped();
	}

	boolean hasImport(ASTCompilationUnit compilationUnit, Type type) {
		if (type.getPackage().equals("java.lang")
				|| type.getPackage().equals(getPackage(compilationUnit))) {
			// The java.lang package is imported by default
			// The types on the same package are not imported
			return true;
		} else {
			for (ASTImportDeclaration importDeclaration : compilationUnit
					.findChildrenOfType(ASTImportDeclaration.class)) {
				String importTypeName = importDeclaration.getFirstChildOfType(
						ASTName.class).getImage();
				if (importTypeName.equals(type.getName())) {
					return true;
				}
			}
			return false;
		}
	}

	private boolean isAnonymous(String currentTypeName) {
		try {
			new Integer(currentTypeName);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isConstructor(final Method method) {
		return method.getName().matches("<.*>");
	}

	private void iterateInstructions(Code code, Type type,
			Map<Instruction, Integer> instructionOffset,
			org.apache.bcel.generic.Visitor visitor) {
		InstructionList instructionList = new InstructionList(code.getCode());
		@SuppressWarnings("unchecked")
		Iterator<InstructionHandle> iterator = instructionList.iterator();
		while (iterator.hasNext()) {
			InstructionHandle ih = iterator.next();
			final Instruction instruction = ih.getInstruction();
			final int offset = ih.getPosition();
			instructionOffset.put(instruction, offset); // TODO refactor?
			instruction.accept(visitor);
		}
	}

	@Override
	public Type lookupType(Class<?> clazz) throws ClassNotFoundException {
		// TODO cache
		final org.apache.bcel.classfile.JavaClass lookupClass = org.apache.bcel.Repository
				.lookupClass(clazz);
		if (lookupClass == null) {
			throw new ClassNotFoundException("Class not found: "
					+ clazz.getName());
		} else {
			return BcelJavaType.create(lookupClass);
		}
	}

	@Override
	public Type lookupType(String typeName) throws ClassNotFoundException {
		// TODO cache
		return BcelJavaType.create(typeName);
	}

	@Override
	public Type parse(FileInputStream classFile, String typeName)
			throws TypeFormatException, IOException {
		try {
			return BcelJavaType.create(new ClassParser(classFile, typeName)
					.parse());
		} catch (final org.apache.bcel.classfile.ClassFormatException e) {
			throw new TypeFormatException(e);
		}
	}

}
