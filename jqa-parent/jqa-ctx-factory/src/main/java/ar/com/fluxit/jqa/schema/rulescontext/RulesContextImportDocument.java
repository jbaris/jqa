/*
 * An XML document type.
 * Localname: RulesContextImport
 * Namespace: http://www.fluxit.com.ar/jqa/schema/rulescontext
 * Java type: ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument
 *
 * Automatically generated - do not modify.
 */
package ar.com.fluxit.jqa.schema.rulescontext;


/**
 * A document containing one RulesContextImport(@http://www.fluxit.com.ar/jqa/schema/rulescontext) element.
 *
 * This is a complex type.
 */
public interface RulesContextImportDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(RulesContextImportDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF9E27A87AB0EF51FC7D6933A8CD44427").resolveHandle("rulescontextimport593bdoctype");
    
    /**
     * Gets the "RulesContextImport" element
     */
    ar.com.fluxit.jqa.schema.rulescontext.RulesContextImport getRulesContextImport();
    
    /**
     * Sets the "RulesContextImport" element
     */
    void setRulesContextImport(ar.com.fluxit.jqa.schema.rulescontext.RulesContextImport rulesContextImport);
    
    /**
     * Appends and returns a new empty "RulesContextImport" element
     */
    ar.com.fluxit.jqa.schema.rulescontext.RulesContextImport addNewRulesContextImport();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument newInstance() {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (ar.com.fluxit.jqa.schema.rulescontext.RulesContextImportDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}