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
package info.bioinfweb.jtreess.document.value;


import info.bioinfweb.jtreess.document.AbstractDocumentElement;
import info.bioinfweb.jtreess.document.DocumentElement;
import info.bioinfweb.languagedefinition.AnalyseConstant;



public class Value extends AbstractDocumentElement{
	public static enum ValueType {
		UNIT_VALUE, 
		COLOR,
		STRING,
		CONSTANT;
	}
	
	
	private String text; 
	private ValueType type;
	
	
	public ValueType getType() {
		return type;
	}


	public Value(DocumentElement parent, ValueType type, String name) {
		super(parent);
		this.text = name; 
		this.type = type;
	}
	
	
	/**
	 * Returns a textual value that depends on {@link #getType()}.
	 * <p>
	 * <ul>
	 *   <li>{@code UNIT_VALUE}: The string representation of the decimal value and its unit as it was found in the file.</li>
	 *   <li>{@code HEX_VALUE}: The string representation of the hexadecimal value as it was found in the file.</li>
	 *   <li>{@code STRING}: The string constant as it was found in the file without leading and trailing quotation marks.</li>
	 *   <li>{@code IDENTIFIER}: The string used as the identifier.</li>
	 * </ul>
	 * 
	 * @return the text representation of the modeled value
	 */
	public String getText() {
		return text;
	}
	
	public void proveConstant(Value value) {
		if (value.getType().equals(ValueType.CONSTANT)) {
			AnalyseConstant.analyseValueConstant(value); 
		}
	}
}
