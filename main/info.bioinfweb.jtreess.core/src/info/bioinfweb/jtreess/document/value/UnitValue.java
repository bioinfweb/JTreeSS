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


import info.bioinfweb.jtreess.document.DocumentElement;



public class UnitValue extends Value {
	private String unit; 
	private double number;
	private String numberRepresentation;
	
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param parent
	 * @param numberUnitRepresentation
	 * @param numberRepresentation
	 * @param unit the unit associated with this value or {@code null} if there is no unit
	 */
	public UnitValue(DocumentElement parent, String numberUnitRepresentation, String numberRepresentation, String unit) {
		super(parent, ValueType.UNIT_VALUE, numberUnitRepresentation);
		this.numberRepresentation = numberRepresentation;
		this.number = Double.parseDouble(numberRepresentation); //Maybe there has to be a test whether the number is in an acceptable range before setting the value
		this.unit = unit;
	}

	
	public String getUnit() {
		return unit;
	}

	
	public double getNumber() {
		return number;
	}


	public String getNumberRepresentation() {
		return numberRepresentation;
	}
}
