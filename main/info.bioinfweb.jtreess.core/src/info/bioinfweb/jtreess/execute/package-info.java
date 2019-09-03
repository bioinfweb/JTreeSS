/**
 * Contains the classes implementing the execution/interpretation of <i>TreeSS</i> files.
 *  
 * @author Ben St&ouml;ver
 */
@javax.xml.bind.annotation.XmlSchema(
		namespace = info.bioinfweb.jtreess.language.io.XMLConstants.LANGUAGE_DEFINITION_NS, 
		xmlns={
        @javax.xml.bind.annotation.XmlNs(prefix = info.bioinfweb.jtreess.language.io.XMLConstants.LANGUAGE_DEFINITION_NS_PREFIX, 
        		namespaceURI = info.bioinfweb.jtreess.language.io.XMLConstants.LANGUAGE_DEFINITION_NS)
    },
		elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package info.bioinfweb.jtreess.execute;