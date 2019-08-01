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


import info.bioinfweb.jtreess.document.DocumentElement;



/**
 * Super interface used by all types of selectors. Classes should never implement this interface directly but use one of the
 * inherited interfaces instead.
 * 
 * @author Ben St&ouml;ver
 * @author Charlotte Schmitt
 */
public interface Selector extends DocumentElement {	
	public String getName();

	public SelectorType getType();
}