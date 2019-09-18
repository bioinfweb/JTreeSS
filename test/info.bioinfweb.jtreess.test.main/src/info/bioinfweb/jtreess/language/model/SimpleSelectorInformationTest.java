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
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class SimpleSelectorInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		SimpleSelectorInformation selectorInformation = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/selectors/node.xml")), SimpleSelectorInformation.class).getValue();
		
		assertEquals("Selects all nodes of a tree.", selectorInformation.getDescription());
		assertEquals("2.1", selectorInformation.getIntroductoryVersion());
		assertEquals("2.12", selectorInformation.getRemovingVersion());
		
		assertEquals(1, selectorInformation.getExamples().size());
		Example example = selectorInformation.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*node\\s*\\{\\s*font-size:\\s*0\\.8em\\;\\s*\\}\\s*"));
		assertEquals("Sets the font size of all nodes in a tree to 80 % of the default font size of the document.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree_terminal_selector.svg", example.getResultImageURL());
		
		assertEquals(1, selectorInformation.getSupportedSoftware().size());
		SupportedSoftwareEntry entry = selectorInformation.getSupportedSoftware().get(0);
		assertEquals("TreeGraph 2", entry.getName());
		assertEquals(2, entry.getSupportedVersionIntervals().size());
		assertEquals(entry.getSupportedVersionIntervals().get(0), new SoftwareVersionInterval("2.14.0", "2.22.0"));
		assertEquals(entry.getSupportedVersionIntervals().get(1), new SoftwareVersionInterval("3.0.0"));
	}
}
