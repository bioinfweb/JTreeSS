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
package info.bioinfweb.jtreess.test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import info.bioinfweb.jtreess.language.model.Example;
import info.bioinfweb.jtreess.language.model.ParameterList;
import info.bioinfweb.jtreess.language.model.PropertyInformation;
import info.bioinfweb.jtreess.language.model.RuntimeType;
import info.bioinfweb.jtreess.language.model.SimpleSelectorInformation;
import info.bioinfweb.jtreess.language.model.SoftwareVersionInterval;
import info.bioinfweb.jtreess.language.model.SupportedSoftwareEntry;



public class PropertyInformationMashaller {
	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SimpleSelectorInformation.class, PropertyInformation.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 
		PropertyInformation information = new PropertyInformation();
		ParameterList list = new ParameterList();
		list.getValidParamList().add(RuntimeType.LENGTH);
		list.getValidParamList().add(RuntimeType.COLOR);
		information.getValidParamLists().add(list);
		
		Example example = new Example();
		example.setCode("node {\n  text-height: 0.8em;\n}");
		example.setDescription("Sets the font size of all nodes in a tree to 80 % of the default font size of the document.");
		example.setTreeURL("http://example.com/Tree.nexml");
		information.getExamples().add(example);
		
		SupportedSoftwareEntry entry = new SupportedSoftwareEntry();
		entry.setName("TreeGraph 2");
		entry.getSupportedVersionIntervals().add(new SoftwareVersionInterval("1.2", "2.1"));
		entry.getSupportedVersionIntervals().add(new SoftwareVersionInterval("2.5"));
		information.getSupportedSoftware().add(entry);
		 
		jaxbMarshaller.marshal(information, System.out);
	}
}
