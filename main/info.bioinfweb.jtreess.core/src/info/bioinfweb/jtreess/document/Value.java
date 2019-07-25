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
package info.bioinfweb.jtreess.document;


import java.util.ArrayList;
import java.util.List;



public class Value extends AbstractDocumentElement{
	private List<DocumentElement> children = new ArrayList<DocumentElement>(); 
	private String name; 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Value(DocumentElement parent) {
		super(parent);
	}

	public List<DocumentElement> getChildren() {
		return children;
	}
	
	public static Value unitValue(String name) {
		return unitValue(name); 
	}
	
	public static Value valueFunction(String name) { 
		return valueFunction(name); 
	}
	
	public static Value stringValue(String name) { 
		return stringValue(name); 
	}
	
	public static Value identifier(String name) { 
		return identifier(name); 
	}
	
	public static Value decValue(String name) { 
		return decValue(name); 
	}
	
	public static Value hexValue(String name) { 
		return hexValue(name); 
	}
	
}
