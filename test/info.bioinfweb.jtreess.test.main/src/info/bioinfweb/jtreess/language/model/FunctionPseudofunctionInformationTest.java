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
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.FunctionImplementationPlaceholder;
import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class FunctionPseudofunctionInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		FunctionPseudoFunctionInformation info = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/functions/matches.xml")), FunctionPseudoFunctionInformation.class).getValue();
		
		assertEquals(RuntimeType.SELECTOR, info.getReturnType());
		
		assertEquals(1, info.getValidParamLists().size());  //TODO Also test overloading in other case.
		ParameterList list = info.getValidParamLists().get(0);
		assertEquals(0, list.getVariableParamIndex());
		assertEquals(1, list.getValidParamList().size());
		assertEquals(RuntimeType.SELECTOR, list.getValidParamList().get(0));

		assertNotNull(info.getFunctionImplementation());
		assertEquals(FunctionImplementationPlaceholder.class.getCanonicalName(), info.getFunctionImplementation().getClass().getCanonicalName());
		
		assertEquals(1, info.getExamples().size());
		Example example = info.getExamples().get(0);
		assertTrue("Code example \"" + example.getCode() + "\" was not expected.", 
				example.getCode().matches("\\s*node\\:matches\\(\\:first, \\:last\\)\\s*\\{\\s*text-height:\\s*0.8em\\;\\s*\\}\\s*"));
		assertEquals("Sets the font size of all nodes in a subtree that are the first or last element on their level to 80 % of the default font size of the document.", example.getDescription());
		assertEquals("Tree.nexml", example.getTreeURL());
		assertEquals("Tree_terminal_selector_firstLast.svg", example.getResultImageURL());
		
		assertTrue(info.getSupportedSoftware().isEmpty());
	}
}
