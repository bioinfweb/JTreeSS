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
package info.bioinfweb.jtreess.utils;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import info.bioinfweb.commons.io.TextReader;
import info.bioinfweb.jtreess.execute.RuntimeValue;
import info.bioinfweb.jtreess.language.io.LanguageDefinitionReader;
import info.bioinfweb.jtreess.language.model.Example;
import info.bioinfweb.jtreess.language.model.ValueConstantInformation;



public class ColorConstantDefinitionGenerator {
	private static ValueConstantInformation createInfoObject(String name, String color) {
		ValueConstantInformation result = new ValueConstantInformation();
		result.setValue(new RuntimeValue(Color.decode(color)));
		result.setIntroductoryVersion("0.2");
		
		Example example = new Example();
		example.setCode("\n" + 
				"			branch {\n" +  
				"				line-color: " + name + ";\n" +
				"			}\n" +
				"		");
		example.setDescription("Sets the line color of all branches in a tree to " + name + ".");
		result.getExamples().add(example);
		
		return result;
	}
	
	
	public static void main(String[] args) throws IOException, JAXBException {
		Marshaller marshaller = LanguageDefinitionReader.createJAXBContext().createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("data/CSSColors.txt")));
		try {
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("\\t");
				String name = parts[0].trim();
				String color = parts[1].trim();
				
				File outputFile = new File("data/output/colorConstants/" + name + ".xml");
				System.out.println("Writing " + outputFile.getName() + " with color " + color);
				
				StringWriter stringWriter = new StringWriter();
				marshaller.marshal(createInfoObject(name, color), stringWriter);
				
				
				String fileContents = stringWriter.toString().replaceAll("<ld:", "<").replaceAll("</ld:", "</").replaceAll("xmlns:ld=", "xmlns=");
				FileWriter writer = new FileWriter(outputFile);
				try {
					writer.write(fileContents);
				}
				finally {
					writer.close();
				}
				
				line = reader.readLine();
				break;
			}
		}
		finally {
			reader.close();
		}
		
	}
}
