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

import info.bioinfweb.jtreess.document.value.Value;



public class PropertyRule extends AbstractDocumentElement{
	private String propertyName;
	private List <DocumentElement> values = new ArrayList<>();
	private List <String> validPropertyNames = new ArrayList<>();
		
	
	public PropertyRule(DocumentElement parent) {
		super(parent);
	}
	

	/**
	 * Returns a list that stores one or more values for this property.
	 * <p>
	 * Values can either be instances of {@link Value} or one of its subclasses or instances of {@link Function}.
	 * 
	 * @return a list with at least one element
	 */
	public List<DocumentElement> getValues() {
		return values;
	}
	
	
	public String getPropertyName() {
		return propertyName;
	}
	

	public void setPropertyName(String propertyName) {
//		if (validPropertyNames.contains(propertyName)) {
			this.propertyName = propertyName;
//		}
//		else {
//			System.out.println(propertyName + "is no valid identifier for a property name.");
//		}
	}
}
