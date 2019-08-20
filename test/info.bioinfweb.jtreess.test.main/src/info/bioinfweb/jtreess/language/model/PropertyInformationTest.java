package info.bioinfweb.jtreess.language.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class PropertyInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		PropertyInformation propertyInformation = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/properties/text-height.xml")), PropertyInformation.class).getValue();
		
		assertEquals("Sets the font size of a text element.", propertyInformation.getDescription());
		assertEquals("1.0", propertyInformation.getIntroductoryVersion());
		assertNull(propertyInformation.getRemovingVersion());

		assertEquals(1, propertyInformation.getValidParamLists().size());
		ParameterList list = propertyInformation.getValidParamLists().get(0);
		assertEquals(ParameterList.NO_VARIABLE_PARAM, list.getVariableParamIndex());
		assertEquals(1, list.getValidParamList().size());
		assertEquals(RuntimeType.LENGTH, list.getValidParamList().get(0));

		assertEquals(2, propertyInformation.getValidSelectors().size());
		assertTrue(propertyInformation.getValidSelectors().contains("node"));
		assertTrue(propertyInformation.getValidSelectors().contains("scale"));
		
		assertEquals(1, propertyInformation.getExamples().size());
		Example example = propertyInformation.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*node\\s*\\{\\s*text-height:\\s*0\\.8em\\;\\s*\\}\\s*"));
		assertEquals("Sets the font size of all nodes in a tree to 80 % of the default font size of the document.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree_terminal_selector.svg", example.getResultImageURL());
		
		assertEquals(1, propertyInformation.getSupportedSoftware().size());
		SupportedSoftwareEntry entry = propertyInformation.getSupportedSoftware().get(0);
		assertEquals("TreeGraph 2", entry.getName());
		assertEquals(1, entry.getSupportedVersionIntervals().size());
		assertEquals(entry.getSupportedVersionIntervals().get(0), new SoftwareVersionInterval("2.14.0"));
	}
}
