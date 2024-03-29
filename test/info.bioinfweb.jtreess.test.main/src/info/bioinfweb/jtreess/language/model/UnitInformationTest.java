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


import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class UnitInformationTest {
	@Test
	public void test_Unmashalling_absolute() throws JAXBException {
		UnitInformation info = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/units/pt.xml")), UnitInformation.class).getValue();
		
		assertEquals(info.getUnitInMM(), 0.35277777777777777, 0.000000000001);
		assertFalse(info.isVariable());
		assertEquals("A desktop publishing point. (1 pt = 1/72 in).", info.getDescription());
		
		assertEquals(1, info.getExamples().size());
		Example example = info.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*node\\s*\\{\\s*font-size:\\s*12pt\\;\\s*\\}\\s*"));
		assertEquals("Sets the font size of all nodes to a height of 12 pt.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree.svg", example.getResultImageURL());
		
		assertEquals(0, info.getSupportedSoftware().size());
	}

	
	@Test
	public void test_Unmashalling_relative() throws JAXBException {
		UnitInformation info = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/units/blu.xml")), UnitInformation.class).getValue();
		
		assertTrue(info.isVariable());
		assertTrue("Description \"" + info.getDescription() + "\" was not expected.", 
				info.getDescription().matches("A branch length unit is length to be used to display a branch with a stored length of 1. "
						+ "The length of a branch length unit\\s+in mm or in varies and is defined by the application of each tree document."));
		
		assertEquals(1, info.getExamples().size());
		Example example = info.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*scale\\s*\\{\\s*width:\\s*500\\s*blu\\;\\s*\\}\\s*"));
		assertEquals("Sets the width of the scale bar to 500 branch length units.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree_scale500.svg", example.getResultImageURL());
		
		assertEquals(0, info.getSupportedSoftware().size());
	}
}
