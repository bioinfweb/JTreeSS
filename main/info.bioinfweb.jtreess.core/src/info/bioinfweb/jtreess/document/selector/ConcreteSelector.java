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
package info.bioinfweb.jtreess.document.selector;


import java.util.ArrayList;
import java.util.List;

import info.bioinfweb.jtreess.document.AbstractDocumentElement;
import info.bioinfweb.jtreess.document.DocumentElement;



public class ConcreteSelector extends AbstractDocumentElement implements Selector {
	private SelectorType type;
	private String name; 
	final List<String> validIdSelectorNames = new ArrayList<String>();
	final List<String> validSimpleSelectorNames = new ArrayList<String>(); 
	final List<String> validPseudoClassNames = new ArrayList<String>(); 
	final List<String> validPseudoFunctionNames = new ArrayList<String>(); 

	public ConcreteSelector(DocumentElement parent, SelectorType type, String name) {
		super(parent);
		if (type == null) {
			throw new IllegalArgumentException("The type must not be null.");
		}
		else {
			this.type = type; 
		}
		
		if ((type == SelectorType.ID_SELECTOR) && !(validIdSelectorNames.contains(name))) {
			System.out.println(name + "is no valid name for an ID selector.");				
		}
		else if ((type == SelectorType.SIMPLE_SELECTOR) && !(validSimpleSelectorNames.contains(name))) {
			System.out.println(name + "is no valid name for a simple selector.");				
		}
		else if ((type == SelectorType.PSEUDO_CLASS) && !(validPseudoClassNames.contains(name))) {
			System.out.println(name + "is no valid name for a pseudo class.");				
		}
		else {
			this.name = name; 
		}
	}
	

	@Override
	public String getName() {
		return name;
	}


	@Override
	public SelectorType getType() {
		return type;
	}
}
