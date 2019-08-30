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



/**
 * Instances of this class are used with {@link RuntimeValue#setValue(Object)} and {@link RuntimeValue#getLengthValue()} 
 * and represent distances that consist of a numeric values and a unit of length.
 * 
 * @author Charlotte Schmitt
 * @author Ben St&ouml;ver
 */
public class Length {
	private double numericValue;
	private String unit;
	
	
	public Length(double numericValue, String unit) {
		super();
		if ((unit == null) && (numericValue != 0.0)) {
			throw new IllegalArgumentException("The unit may only be null if the numeric value is 0.0.");
		}
		else if ("".equals(unit) && (numericValue != 0.0)) {
			throw new IllegalArgumentException("The unit may only be an empty string if the numeric value is 0.0.");
		}
		else {
			this.numericValue = numericValue;
			this.unit = unit;
		}
	}


	public double getNumericValue() {
		return numericValue;
	}
	
	
	public void setNumericValue(double numericValue) {
		this.numericValue = numericValue;
	}
	
	
	public String getUnit() {
		return unit;
	}
	
	
	public void setUnit(String unit) {
		this.unit = unit;
	} 
}
