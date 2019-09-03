package info.bioinfweb.jtreess.language.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.execute.RuntimeValue;
import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class ValueConstantInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		ValueConstantInformation constantInformation = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/constants/blue.xml")), ValueConstantInformation.class).getValue();
		
		assertEquals(RuntimeValue.parseRuntimeValue("#0000FF", RuntimeType.COLOR), constantInformation.getValue());
		assertEquals("1.0", constantInformation.getIntroductoryVersion());
		assertNull(constantInformation.getRemovingVersion());
		
		assertEquals(1, constantInformation.getExamples().size());
		Example example = constantInformation.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*branch\\s*\\{\\s*line-color:\\s*blue\\;\\s*\\}\\s*"));
		assertEquals("Sets the line color of all branches in a tree to blue.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree_blue_branches.svg", example.getResultImageURL());
		
		assertEquals(1, constantInformation.getSupportedSoftware().size());
		SupportedSoftwareEntry entry = constantInformation.getSupportedSoftware().get(0);
		assertEquals("TreeGraph 2", entry.getName());
		assertEquals(1, entry.getSupportedVersionIntervals().size());
		assertEquals(entry.getSupportedVersionIntervals().get(0), new SoftwareVersionInterval("2.14.0"));
	}
	
	
	public static void main(String[] args) throws JAXBException {
		ValueConstantInformation info = new ValueConstantInformation();
		info.setValue(RuntimeValue.parseRuntimeValue("#0000FF", RuntimeType.COLOR));
		
		Marshaller marshaller = JTreeSSTestTools.createJAXBContext().createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(info, System.out);
	}
}
