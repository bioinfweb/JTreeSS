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


import java.util.HashMap;



public class LanguageDefinitions {
	private static LanguageDefinitions firstInstance = null;
	
	
	private HashMap<String, SimpleSelectorInformation> simpleSelectorInformation = new HashMap<String, SimpleSelectorInformation>();
	private HashMap<String, PseudoClassInformation> pseudoClassInformation = new HashMap<String, PseudoClassInformation>(); 
	private HashMap<String, FunctionPseudoFunctionInformation> functionInformation = new HashMap<String, FunctionPseudoFunctionInformation>(); 
	private HashMap<String, PropertyInformation> propertyInformation = new HashMap<String, PropertyInformation>();
	private HashMap<String, ValueConstant> constantsInformation = new HashMap<String, ValueConstant>(); 
	private HashMap<String, UnitInformation> unitInformation = new HashMap<String, UnitInformation>();
	private HashMap<String, EnumerationTypeInformation> enumTypeInformation = new HashMap<String, EnumerationTypeInformation>(); 
	private HashMap<String, SoftwareInformation> softwareInformation = new HashMap<String, SoftwareInformation>();
	
	
	private LanguageDefinitions() {
		super();
	}
	
	
	public static LanguageDefinitions getInstance() {
		if (firstInstance == null) {
			firstInstance = new LanguageDefinitions(); 
		}
		return firstInstance;
	}
	
	
	public HashMap<String, SimpleSelectorInformation> getSimpleSelectorInformation() {
		return simpleSelectorInformation;
	}
	
	
	public HashMap<String, PseudoClassInformation> getPseudoClassInformation() {
		return pseudoClassInformation;
	}
	
	
	public HashMap<String, FunctionPseudoFunctionInformation> getFunctionInformation() {
		return functionInformation;
	}
	
	
	public HashMap<String, PropertyInformation> getPropertyInformation() {
		return propertyInformation;
	}
	
	
	public HashMap<String, ValueConstant> getConstantsInformation() {
		return constantsInformation;
	}
	
	
	public HashMap<String, UnitInformation> getUnitInformation() {
		return unitInformation;
	}
	
	
	public HashMap<String, EnumerationTypeInformation> getEnumTypeInformation() {
		return enumTypeInformation;
	}
	
	
	public HashMap<String, SoftwareInformation> getSoftwareInformation() {
		return softwareInformation;
	}
}
