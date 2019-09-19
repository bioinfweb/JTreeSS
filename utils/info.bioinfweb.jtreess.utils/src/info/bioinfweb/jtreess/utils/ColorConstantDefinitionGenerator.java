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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.bind.JAXBException;



public class ColorConstantDefinitionGenerator {
	private static void writeFile(File file, String name, String color) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		try {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
					"<treeSSLangDefition xmlns=\"http://bioinfweb.info/xmlns/TreeSS/languageDefinition\" type=\"property\">\r\n" + 
					"	<value type=\"color\">" + color + "</value>\r\n" + 
					"	<firstVersion>0.2</firstVersion>\r\n" + 
					"\r\n" + 
					"	<example>\r\n" + 
					"		<code>\r\n" + 
					"			branch {\r\n" + 
					"				line-color: " + name + ";\r\n" + 
					"			}\r\n" + 
					"		</code>\r\n" + 
					"		<description>Sets the line color of all branches in a tree to " + name + ".</description>\r\n" + 
					"	</example>\r\n" + 
					"</treeSSLangDefition>");
		}
		finally {
			writer.close();
		}
	}
	
	
	public static void main(String[] args) throws IOException, JAXBException {
		// This class was refactored not to use JAXB anymore, since namespace achieving correct namespace declarations and element orders would have destroyed the reading implementation in core and would have been very complex. Tab idention was also not easily possible. Writing the XML manually here was sadly much more convenient and efficient.
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("data/CSSColors.txt")));
		try {
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("\\t");
				String name = parts[0].trim();
				String color = parts[1].trim();
				
				File outputFile = new File("data/output/colorConstants/" + name + ".xml");
				System.out.println("Writing " + outputFile.getName() + " with color " + color);
				writeFile(outputFile, name, color);
				
				line = reader.readLine();
			}
		}
		finally {
			reader.close();
		}
	}
}
