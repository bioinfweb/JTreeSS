package info.bioinfweb.jtreess.language.model;


import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class ValueConstantInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		ValueConstantInformation constantInformation = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/properties/text-height.xml")), ValueConstantInformation.class).getValue();
	}
}
