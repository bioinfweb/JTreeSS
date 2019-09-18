package info.bioinfweb.jtreess.test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import info.bioinfweb.jtreess.language.io.LanguageDefinitionReader;



public class JTreeSSTestTools {
	public static JAXBContext createJAXBContext() throws JAXBException {
		return LanguageDefinitionReader.createJAXBContext();
	}
}
