package info.bioinfweb.jtreess.test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import info.bioinfweb.jtreess.language.model.PropertyInformation;
import info.bioinfweb.jtreess.language.model.SimpleSelectorInformation;



public class JTreeSSTestTools {
	public static JAXBContext createJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(SimpleSelectorInformation.class, PropertyInformation.class);
	}
}
