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
package ar.com.fluxit.jqa.bce;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface BCERepository {

	Map<Type, Collection<Integer>> getAllocations(Type type);

	Integer getDeclarationLineNumber(Type type);

	Collection<Type> getInterfaces(Type type);

	File getSourceFile(String typeName, File[] sourceDirs)
			throws FileNotFoundException;

	Collection<Type> getSuperClasses(Type type);

	Map<Type, Collection<Integer>> getThrows(Type type);

	Map<Type, Collection<Integer>> getUses(Type type);

	Type lookupType(Class<?> clazz) throws ClassNotFoundException;

	Type lookupType(String typeName) throws ClassNotFoundException;

	Type parse(FileInputStream classFile, String typeName)
			throws TypeFormatException, IOException;

}
