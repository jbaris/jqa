/*
 * XML Type:  Rule
 * Namespace: http://www.fluxit.com.ar/jqa/schema/ruleset
 * Java type: ar.com.fluxit.jqa.schema.ruleset.Rule
 *
 * Automatically generated - do not modify.
 */
package ar.com.fluxit.jqa.schema.ruleset;


/**
 * An XML Rule(@http://www.fluxit.com.ar/jqa/schema/ruleset).
 *
 * This is a complex type.
 */
public interface Rule extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Rule.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA7A5C0A6A4AE9BE15689417136C55B17").resolveHandle("rule9152type");
    
    /**
     * Gets the "filterPredicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate getFilterPredicate();
    
    /**
     * Sets the "filterPredicate" element
     */
    void setFilterPredicate(ar.com.fluxit.jqa.schema.ruleset.Predicate filterPredicate);
    
    /**
     * Appends and returns a new empty "filterPredicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate addNewFilterPredicate();
    
    /**
     * Gets the "checkPredicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate getCheckPredicate();
    
    /**
     * Sets the "checkPredicate" element
     */
    void setCheckPredicate(ar.com.fluxit.jqa.schema.ruleset.Predicate checkPredicate);
    
    /**
     * Appends and returns a new empty "checkPredicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate addNewCheckPredicate();
    
    /**
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * Gets the "message" attribute
     */
    java.lang.String getMessage();
    
    /**
     * Gets (as xml) the "message" attribute
     */
    org.apache.xmlbeans.XmlString xgetMessage();
    
    /**
     * Sets the "message" attribute
     */
    void setMessage(java.lang.String message);
    
    /**
     * Sets (as xml) the "message" attribute
     */
    void xsetMessage(org.apache.xmlbeans.XmlString message);
    
    /**
     * Gets the "priority" attribute
     */
    int getPriority();
    
    /**
     * Gets (as xml) the "priority" attribute
     */
    org.apache.xmlbeans.XmlInt xgetPriority();
    
    /**
     * True if has "priority" attribute
     */
    boolean isSetPriority();
    
    /**
     * Sets the "priority" attribute
     */
    void setPriority(int priority);
    
    /**
     * Sets (as xml) the "priority" attribute
     */
    void xsetPriority(org.apache.xmlbeans.XmlInt priority);
    
    /**
     * Unsets the "priority" attribute
     */
    void unsetPriority();
    
    /**
     * Gets the "bidirectionalCheck" attribute
     */
    boolean getBidirectionalCheck();
    
    /**
     * Gets (as xml) the "bidirectionalCheck" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetBidirectionalCheck();
    
    /**
     * True if has "bidirectionalCheck" attribute
     */
    boolean isSetBidirectionalCheck();
    
    /**
     * Sets the "bidirectionalCheck" attribute
     */
    void setBidirectionalCheck(boolean bidirectionalCheck);
    
    /**
     * Sets (as xml) the "bidirectionalCheck" attribute
     */
    void xsetBidirectionalCheck(org.apache.xmlbeans.XmlBoolean bidirectionalCheck);
    
    /**
     * Unsets the "bidirectionalCheck" attribute
     */
    void unsetBidirectionalCheck();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static ar.com.fluxit.jqa.schema.ruleset.Rule newInstance() {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static ar.com.fluxit.jqa.schema.ruleset.Rule parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (ar.com.fluxit.jqa.schema.ruleset.Rule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}