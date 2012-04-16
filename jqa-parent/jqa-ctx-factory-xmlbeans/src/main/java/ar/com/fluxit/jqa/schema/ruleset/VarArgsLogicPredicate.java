/*
 * XML Type:  VarArgsLogicPredicate
 * Namespace: http://www.fluxit.com.ar/jqa/schema/ruleset
 * Java type: ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate
 *
 * Automatically generated - do not modify.
 */
package ar.com.fluxit.jqa.schema.ruleset;


/**
 * An XML VarArgsLogicPredicate(@http://www.fluxit.com.ar/jqa/schema/ruleset).
 *
 * This is a complex type.
 */
public interface VarArgsLogicPredicate extends ar.com.fluxit.jqa.schema.ruleset.Predicate
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(VarArgsLogicPredicate.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA7A5C0A6A4AE9BE15689417136C55B17").resolveHandle("varargslogicpredicatef0a1type");
    
    /**
     * Gets a List of "predicate" elements
     */
    java.util.List<ar.com.fluxit.jqa.schema.ruleset.Predicate> getPredicateList();
    
    /**
     * Gets array of all "predicate" elements
     * @deprecated
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate[] getPredicateArray();
    
    /**
     * Gets ith "predicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate getPredicateArray(int i);
    
    /**
     * Returns number of "predicate" element
     */
    int sizeOfPredicateArray();
    
    /**
     * Sets array of all "predicate" element
     */
    void setPredicateArray(ar.com.fluxit.jqa.schema.ruleset.Predicate[] predicateArray);
    
    /**
     * Sets ith "predicate" element
     */
    void setPredicateArray(int i, ar.com.fluxit.jqa.schema.ruleset.Predicate predicate);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "predicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate insertNewPredicate(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "predicate" element
     */
    ar.com.fluxit.jqa.schema.ruleset.Predicate addNewPredicate();
    
    /**
     * Removes the ith "predicate" element
     */
    void removePredicate(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        /** @deprecated No need to be able to create instances of abstract types */
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate newInstance() {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        /** @deprecated No need to be able to create instances of abstract types */
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (ar.com.fluxit.jqa.schema.ruleset.VarArgsLogicPredicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}