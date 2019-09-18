/*
 * JTreeSS - A Java library for reading and evaluating TreeSS documents
 * Copyright (C) 2019 Ben Stöver, Charlotte Schmitt
 * <http://bioinfweb.info/JTreeSS>
 * 
 * This file is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This file is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package info.bioinfweb.jtreess.language.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class EnumerationTypeInformationTest {
	@Test
	public void testKeySetDecorator() {
		EnumerationTypeInformation info = new EnumerationTypeInformation();
		info.getConstantsToDescriptionsMap().put("A", "Description of A.");
		info.getConstants().add("A");  // Do not overwrite description.
		info.getConstants().add("B");  // Add new entry with null description.
		info.getConstantsToDescriptionsMap().put("C", "Description of C.");
		
		assertTrue(info.getConstantsToDescriptionsMap().containsKey("A"));
		assertTrue(info.getConstants().contains("A"));
		assertEquals("Description of A.", info.getConstantDescription("A"));
		
		assertTrue(info.getConstantsToDescriptionsMap().containsKey("B"));
		assertTrue(info.getConstants().contains("B"));
		assertNull(info.getConstantDescription("B"));
		
		assertTrue(info.getConstantsToDescriptionsMap().containsKey("C"));
		assertTrue(info.getConstants().contains("C"));
		assertEquals("Description of C.", info.getConstantDescription("C"));
		
		info.setConstantsToDescriptionsMap(new HashMap<String, String>());
		assertTrue(info.getConstants().isEmpty());
	}
	
	
	@Test
	public void test_Unmashalling() throws JAXBException {
		EnumerationTypeInformation info = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/types/text-align.xml")), EnumerationTypeInformation.class).getValue();
		
		assertEquals(4, info.getConstants().size());
		
		assertTrue(info.getConstants().contains("left"));
		assertEquals("Left text alignment.", info.getConstantDescription("left"));
		
		assertTrue(info.getConstants().contains("right"));
		assertNull(info.getConstantDescription("right"));
		
		assertTrue(info.getConstants().contains("center"));
		assertNull(info.getConstantDescription("center"));

		assertTrue(info.getConstants().contains("justify"));
		assertNull(info.getConstantDescription("justify"));
		
		assertNull(info.getIntroductoryVersion());
		assertNull(info.getRemovingVersion());
		
		assertEquals(1, info.getExamples().size());
		Example example = info.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*node\\s*\\{\\s*text-align:\\s*center\\;\\s*\\}\\s*"));
		assertEquals("Sets the text alignment of all nodes to center.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree_center_alignment.svg", example.getResultImageURL());
		
		assertTrue(info.getSupportedSoftware().isEmpty());
	}
	
	
	public static void main(String[] args) throws JAXBException {
		EnumerationTypeInformation info = new EnumerationTypeInformation();
		info.getConstantsToDescriptionsMap().put("A", "Description of A.");
		info.getConstants().add("A");  // Do not overwrite description.
		info.getConstants().add("B");  // Add new entry with null description.
		info.getConstantsToDescriptionsMap().put("C", "Description of C.");
		
		Marshaller marshaller = JTreeSSTestTools.createJAXBContext().createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(info, System.out);
	}
}
