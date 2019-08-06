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
	public enum UnitType {
		MM, //W3C does not recommend that 
		CM, //W3C does not recommend that 
		IN, //W3C does not recommend that 
		PT, //W3C does not recommend that 
		PC, //W3C does not recommend that 
		PX,
		EM,
		REM,
		NONE; //font-size of root element
	}
	private UnitType unitType;
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
		this.number = Double.parseDouble(numberRepresentation);
		this.unit = unit;
		setUnitType(unit); 
	}
	
	
	public UnitType getUnitType() {
		return unitType;
	}


	public void setUnitType(String unit) {
		if (unit == "cm" || unit == "CM") {
			this.unitType = UnitType.CM;
		}
		else if (unit == "mm" || unit == "MM") {
			this.unitType = UnitType.MM;
		}
		else if (unit == "in" || unit == "IN") {
			this.unitType = UnitType.IN;
		}
		else if (unit == "pt" || unit == "PT") {
			this.unitType = UnitType.PT;
		}
		else if (unit == "pc" || unit == "PC") {
			this.unitType = UnitType.MM;
		}
		else if (unit == "px" || unit == "PX") {
			this.unitType = UnitType.MM;
		}
		else if (unit == "em" || unit == "EM") {
			this.unitType = UnitType.MM;
		}
		else if (unit == "rem" || unit == "REM") {
			this.unitType = UnitType.MM;
		}
		else if (unit == null){
			this.unitType = UnitType.NONE;
		}
		else {
			System.out.println(unit + "is an invalid unit. Valid units are cm, mm, in, pt, pc, px, em, rem and no unit.");
		}
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
