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

import info.bioinfweb.jtreess.document.DocumentElement;
import info.bioinfweb.jtreess.document.Function;



public class PseudoFunction extends Selector {
	private Function function; 
	private Selector.SelectorType type;
	private List<DocumentElement> children = new ArrayList<DocumentElement>(); 
	
	
	public PseudoFunction(DocumentElement parent, SelectorType type) {
		super(parent, type);
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	public List<DocumentElement> getChildren() {
		return children;
	}
}
