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
import java.util.Map;

import info.bioinfweb.jtreess.language.model.EnumerationTypeInformation;
import info.bioinfweb.jtreess.language.model.FunctionPseudoFunctionInformation;
import info.bioinfweb.jtreess.language.model.PropertyInformation;
import info.bioinfweb.jtreess.language.model.PseudoClassInformation;
import info.bioinfweb.jtreess.language.model.SimpleSelectorInformation;
import info.bioinfweb.jtreess.language.model.SoftwareInformation;
import info.bioinfweb.jtreess.language.model.UnitInformation;
import info.bioinfweb.jtreess.language.model.ValueConstantInformation;



public class LanguageDefinitions {
	private static LanguageDefinitions firstInstance = null;
	
	
	private Map<String, SimpleSelectorInformation> simpleSelectorInformation = new HashMap<String, SimpleSelectorInformation>();
	private Map<String, PseudoClassInformation> pseudoClassInformation = new HashMap<String, PseudoClassInformation>(); 
	private Map<String, FunctionPseudoFunctionInformation> functionInformation = new HashMap<String, FunctionPseudoFunctionInformation>(); 
	private Map<String, PropertyInformation> propertyInformation = new HashMap<String, PropertyInformation>();
	private Map<String, ValueConstantInformation> constantsInformation = new HashMap<String, ValueConstantInformation>(); 
	private Map<String, UnitInformation> unitInformation = new HashMap<String, UnitInformation>();
	private Map<String, EnumerationTypeInformation> enumTypeInformation = new HashMap<String, EnumerationTypeInformation>(); 
	private Map<String, SoftwareInformation> softwareInformation = new HashMap<String, SoftwareInformation>();
	
	
	private LanguageDefinitions() {
		super();
	}
	
	
	public static LanguageDefinitions getInstance() {
		if (firstInstance == null) {
			firstInstance = new LanguageDefinitions(); 
		}
		return firstInstance;
	}
	
	
	public Map<String, SimpleSelectorInformation> getSimpleSelectorInformation() {
		return simpleSelectorInformation;
	}
	
	
	public Map<String, PseudoClassInformation> getPseudoClassInformation() {
		return pseudoClassInformation;
	}
	
	
	public Map<String, FunctionPseudoFunctionInformation> getFunctionInformation() {
		return functionInformation;
	}
	
	
	public Map<String, PropertyInformation> getPropertyInformation() {
		return propertyInformation;
	}
	
	
	public Map<String, ValueConstantInformation> getConstantsInformation() {
		return constantsInformation;
	}
	
	
	public Map<String, UnitInformation> getUnitInformation() {
		return unitInformation;
	}
	
	
	public Map<String, EnumerationTypeInformation> getEnumTypeInformation() {
		return enumTypeInformation;
	}
	
	
	public Map<String, SoftwareInformation> getSoftwareInformation() {
		return softwareInformation;
	}
}
