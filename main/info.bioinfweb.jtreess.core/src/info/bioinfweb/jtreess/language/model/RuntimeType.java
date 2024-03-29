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
package info.bioinfweb.jtreess.language.model;


import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import info.bioinfweb.jtreess.language.io.RuntimeTypeAdapter;



@XmlJavaTypeAdapter(RuntimeTypeAdapter.class)
public class RuntimeType {
	public static final RuntimeType LENGTH = new RuntimeType(BasicType.LENGTH);
	public static final RuntimeType NUMBER = new RuntimeType(BasicType.NUMBER);
	public static final RuntimeType COLOR = new RuntimeType(BasicType.COLOR);
	public static final RuntimeType STRING = new RuntimeType(BasicType.STRING);
	public static final RuntimeType BOOLEAN = new RuntimeType(BasicType.BOOLEAN);
	public static final RuntimeType SELECTOR = new RuntimeType(BasicType.SELECTOR);
	
	
	public static enum BasicType {
		/** Indicates a length value with a unit. */
		LENGTH,
		
		/** Indicates a numeric value without a unit. */
		NUMBER, 
		
		COLOR,
		
		STRING,
		
		BOOLEAN,
		
		/** Indicates a selector implementation. */
		SELECTOR,
		
		ENUM_TYPE;	
	}
	
	
	private BasicType basicType; 
	private String enumType;

	
	private RuntimeType() {  // For use by JAXB.
		this(BasicType.LENGTH);
	}
	
	
	public RuntimeType(BasicType basicType) {
		super();
		if (BasicType.ENUM_TYPE.equals(basicType)) {
			throw new IllegalArgumentException("Instances of enum type representations have to be created using a contructur with an enum type argument.");
		}
		else {
			this.basicType = basicType;
			this.enumType = null;
		}
	}


	public RuntimeType(String enumType) {
		super();
		if (enumType == null) {
			throw new IllegalArgumentException("The enum type must not be null.");
		}
		else if ("".equals(enumType)) {
			throw new IllegalArgumentException("The enum type must not be an empty string.");
		}
		else {
			this.basicType = BasicType.ENUM_TYPE;
			this.enumType = enumType;
		}
	}


	public BasicType getBasicType() {
		return basicType;
	}


	public String getEnumType() {
		return enumType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicType == null) ? 0 : basicType.hashCode());
		result = prime * result + ((enumType == null) ? 0 : enumType.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuntimeType other = (RuntimeType) obj;
		if (basicType != other.basicType)
			return false;
		if (enumType == null) {
			if (other.enumType != null)
				return false;
		} else if (!enumType.equals(other.enumType))
			return false;
		return true;
	}


	@Override
	public String toString() {
		if (BasicType.ENUM_TYPE.equals(getBasicType())) {
			return getEnumType();
		}
		else {
			return getBasicType().toString();
		}
	}
	
	
	public static RuntimeType parseRuntimeType(String representation) {
		if (representation == null) {
			throw new IllegalArgumentException("Cannot parse from a string representation that is null.");
		}
		else if ("".equals(representation)) {
			throw new IllegalArgumentException("Cannot parse from an empty string representation.");
		}
		else {
			BasicType basicType = null;
			try {
				basicType = BasicType.valueOf(representation.toUpperCase());
			}
			catch (IllegalArgumentException e) {}
			
			if (basicType == null) {
				return new RuntimeType(representation);  // Enum type.  //TODO Edit case?
			}
			else {
				return new RuntimeType(basicType);
			}
		}
	}
}
