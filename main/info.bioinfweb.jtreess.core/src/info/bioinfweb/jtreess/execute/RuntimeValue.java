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
package info.bioinfweb.jtreess.execute;


import java.awt.Color;

import info.bioinfweb.jtreess.execute.implementation.SelectorImplementation;
import info.bioinfweb.jtreess.language.model.RuntimeType;
import info.bioinfweb.jtreess.language.model.RuntimeType.BasicType;



/**
 * Instances of this class store values that are calculated during runtime (the interpretation of <i>TreeSS</i> document).
 * <p>
 * Depending on the type, instances of different classes can be used as values.
 * 
 * @author Ben St&ouml;ver
 * @author Charlotte Schmitt
 */
public class RuntimeValue {
	private RuntimeType type = null; 
	private Object value = null; 
	
	
	/**
	 * Sets the value and the type of this instance. The type of this instance will automatically be set depending on the specified object.
	 * <p>
	 * Instances of the following classes are valid:
	 * <ul>
	 *   <li>{@link Double} for numeric values without any unit (The type will be set to {@link RuntimeType#NUMERIC_VALUE_NO_UNIT}.)</i>
	 *   <li>{@link Color} for color values (The type will be set to {@link RuntimeType#COLOR}.)</li>
	 *   <li>{@link Boolean} for boolean values (The type will be set to {@link RuntimeType#BOOLEAN}.)</i>
	 *   <li>{@link String} for string values (The type will be set to {@link RuntimeType#STRING}.)</i>
	 *   <li>{@link Length} for length values, i.e., numeric values with a unit of length (The type will be set to {@link RuntimeType#LENGTH}.)</li>
	 *   <li>{@link SelectorImplementation} for selector implementations (The type will be set to {@link RuntimeType#SELECTOR_IMPLEMENTATION}.)</li>
	 * </ul>
	 * Note that {@link #setEnumValue(String, String)} must be used to set enumeration states as values of this instance. If a {@link String}
	 * is specified here, it will be interpreted as {@link RuntimeType#STRING}.
	 * 
	 * @param value an instance of a valid value object (See list above.)
	 * @throws IllegalArgumentException if the specified object is not an instance or a subclass of a valid object type (See list above.)
	 * @see #setEnumValue(String, String) 
	 */
	public void setValue(Object value) throws IllegalArgumentException {
		if (value == null) {
			this.type = null;
		}
		if (value instanceof Double) {
			this.type = new RuntimeType(RuntimeType.BasicType.NUMERIC_VALUE_NO_UNIT);
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
		else if (value instanceof SelectorImplementation) {
			this.type = new RuntimeType(RuntimeType.BasicType.SELECTOR_IMPLEMENTATION);
		}
		else {
			throw new IllegalArgumentException("No matching runtime time for " + type + "could be found.");
		}
		this.value = value;
	}
	
	
	/**
	 * Sets a state of an <i>TreeSS</i> enumeration type of the value of this instance. 
	 * 
	 * @param value the state
	 * @param type the enumeration type the specified state belongs to
	 * @see #setValue(Object)
	 */
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
	
	
	/**
	 * Returns the value stored in this instance as a numeric value without unit.
	 * 
	 * @return the stored value
	 * @throws IllegalStateException if the stored value does not represent a numeric value without unit
	 */
	public double getNumericValue() {
		if (!RuntimeType.NUMERIC_VALUE_NO_UNIT.equals(getType())) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
					RuntimeType.NUMERIC_VALUE_NO_UNIT + "\".");
		}
		return (Double)value;
	}
	
	
	/**
	 * Returns the value stored in this instance as a color.
	 * 
	 * @return the stored value
	 * @throws IllegalStateException if the stored value does not represent a color
	 */
	public Color getColorValue() {
		if (!RuntimeType.COLOR.equals(getType())) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
					RuntimeType.BasicType.COLOR + "\".");
		}
		return (Color)value;
	}
	
	
	/**
	 * Returns the value stored in this instance as a boolean.
	 * 
	 * @return the stored value
	 * @throws IllegalStateException if the stored value does not represent a boolean
	 */
	public Boolean getBooleanValue() {
		if (!RuntimeType.BOOLEAN.equals(getType())) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
				 RuntimeType.BasicType.BOOLEAN + "\".");
		}
		return (Boolean)value;
	}
	
		
	/**
	 * Returns the value stored in this instance as a {@link String} that may represent a string value or the state of a 
	 * <i>TreeSS</i> enumeration value. Use {@link #getType()} to distinguish between the two.
	 * 
	 * @return the stored value
	 * @throws IllegalStateException if the stored value cannot be cast to a {@link String}
	 */
	public String getStringOrEnumValue() {
		if (!(RuntimeType.STRING.equals(getType()) || 
				((getType() != null) && RuntimeType.BasicType.ENUM_TYPE.equals(getType().getBasicType())))) {
			
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
					RuntimeType.BasicType.STRING + "\" or \"" + new RuntimeType(RuntimeType.BasicType.ENUM_TYPE) + "\".");
		}
		return (String)value;
	}
	

	/**
	 * Returns the value stored in this instance as a length value.
	 * 
	 * @return the stored value
	 * @throws IllegalStateException if the stored value does not represent a length value
	 */
	public Length getLengthValue() {
		if (!RuntimeType.LENGTH.equals(getType())) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
				 RuntimeType.BasicType.LENGTH + "\".");
		}
		return (Length)value;
	}
	

	/**
	 * Returns the value stored in this instance as a selector implementation.
	 * 
	 * @return the stored value
	 * @throws IllegalStateException if the stored value does not represent a selector implementation
	 */
	public SelectorImplementation getSelectorImplementationValue() {
		if (!RuntimeType.SELECTOR_IMPLEMENTATION.equals(getType())) {
			throw new IllegalStateException("Cannot return a value of type \"" + type + "\" as an \"" + 
				 RuntimeType.BasicType.SELECTOR_IMPLEMENTATION + "\".");
		}
		return (SelectorImplementation)value;
	}
	
	
	/**
	 * Determines whether this instance currently stores a value. If {@code false} is returned the value getter functions 
	 * will throw an {@link IllegalStateException} and {@link #getType()} will return {@code null}.
	 * 
	 * @return {@code true} if a value is currently stored or {@code false} otherwise
	 */
	public boolean hasValue() {
		return value != null;
	}
	
	
	/**
	 * The type of value this instance currently represents.  
	 * 
	 * @return the type of the stored value or {@code null} if no value is present
	 */
	public RuntimeType getType() {
		return type;
	}
}