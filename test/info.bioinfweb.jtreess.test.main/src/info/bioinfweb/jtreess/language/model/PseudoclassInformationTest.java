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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.execute.implementation.selectorpseudofunction.FirstChildPseudoselectorImplementation;
import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class PseudoclassInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		PseudoClassInformation info = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/pseudoclasses/first-child.xml")), PseudoClassInformation.class).getValue();
		
		assertEquals("Selects the first child node of each parent node in a tree.", info.getDescription());
		assertEquals("0.2", info.getIntroductoryVersion());
		assertNull(info.getRemovingVersion());

		assertEquals(2, info.getValidSelectors().size());
		assertTrue(info.getValidSelectors().contains("node"));
		assertTrue(info.getValidSelectors().contains("branch"));

		assertNotNull("The implementation was not loaded.", info.getPseudoClassImplementation());
		assertEquals(FirstChildPseudoselectorImplementation.class.getCanonicalName(), info.getPseudoClassImplementation().getClass().getCanonicalName());
		
		assertEquals(1, info.getExamples().size());
		Example example = info.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*branch:first-child\\s*\\{\\s*color:\\s*green\\;\\s*\\}\\s*"));
		assertEquals("Sets the line color leading to the first child node of every parent node to green.", example.getDescription());
	}
}
