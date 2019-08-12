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
package info.bioinfweb.jtreess.language;


import java.awt.Color;



public class RuntimeValue {
	private RuntimeType type; 
	private Object value; 
	
	
	public void setValue(Object value) {
		if (value instanceof Double) {
			this.type = new RuntimeType(RuntimeType.BasicType.UNIFORM_NUM_VAL);
		}
		else if (value instanceof Color) {
			this.type = new RuntimeType(RuntimeType.BasicType.COLOR);
		}
		else if (value instanceof Boolean) {
			this.type = new RuntimeType(RuntimeType.BasicType.BOOLEAN);
		}
		else if (value instanceof String) {
			this.type = new RuntimeType(RuntimeType.BasicType.STRING);
		}
		else if (value instanceof Length) {
			this.type = new RuntimeType(RuntimeType.BasicType.LENGTH);
		}
		else {
			throw new IllegalArgumentException("No matching runtime time for " + type + "could be found.");
		}
		this.value = value;
	}
	
	
	public void setEnumValue(String value, String type) {
		this.value = value;
		this.type = new RuntimeType(type);
		if(value == null) {
			throw new IllegalArgumentException("The value must not be null.");
		}
		else if (type == null) {
			throw new IllegalArgumentException("The enum type must not be null.");
		}
		else if(value.equals("")) {
			throw new IllegalArgumentException("The value must not be an empty string.");
		}
		else if(type.equals("")) {
			throw new IllegalArgumentException("The enum type must not be an empty string.");
		}
	}
	
	
	public double getNumericValue() {
		if (!(type.equals(RuntimeType.UNIFORM_NUM_VAL))) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
					RuntimeType.UNIFORM_NUM_VAL + "\".");
		}
		return (Double)value;
	}
	
	
	public Color getColorValue() {
		if (!(type.equals(RuntimeType.COLOR))) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
					RuntimeType.BasicType.COLOR + "\".");
		}
		return (Color)value;
	}
	
	
	public Boolean getBooleanValue() {
		if (!(type.equals(RuntimeType.BOOLEAN))) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
				 RuntimeType.BasicType.BOOLEAN + "\".");
		}
		return (Boolean)value;
	}
	
		
	public String getStringOrEnumValue() {
		if (!(type.equals(RuntimeType.STRING))|| !(type.getBasicType().equals(RuntimeType.BasicType.ENUM_TYPE))) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
					RuntimeType.BasicType.STRING + "\" or \"" + new RuntimeType(RuntimeType.BasicType.ENUM_TYPE) + "\".");
		}
		return (String)value;
	}
	

	public Length getLengthValue() {
		if (!(type.equals(RuntimeType.LENGTH))) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
				 RuntimeType.BasicType.LENGTH + "\".");
		}
		return (Length)value;
	}
	
	
	public RuntimeType getType() {
		return type;
	}
}
