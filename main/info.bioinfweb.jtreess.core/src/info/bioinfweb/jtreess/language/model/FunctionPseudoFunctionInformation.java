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


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import info.bioinfweb.jtreess.execute.implementation.FunctionImplementation;



public class FunctionPseudoFunctionInformation extends BasicInformation {
	public static final int NO_VARIABLE_PARAM = -1; 
	
	
	private FunctionImplementation functionImplementation; 
	private RuntimeType returnType;
	private List<ParamListEntry> validParamList = new ArrayList<ParamListEntry>();
	private int variableParamIndex = NO_VARIABLE_PARAM;
	
	
	public FunctionImplementation getFunctionImplementation() {
		return functionImplementation;
	}
	
	
	public void setFunctionImplementation(FunctionImplementation functionImplementation) {
		this.functionImplementation = functionImplementation;
	}
	
	
	public RuntimeType getReturnType() {
		return returnType;
	}
	
	
	public void setReturnType(RuntimeType returnType) {
		this.returnType = returnType;
	}


	public List<ParamListEntry> getValidParamList() {
		return validParamList;
	}


	public int getVariableParamIndex() {
		return variableParamIndex;
	}


	public void setVariableParamIndex(int variableParamIndex) {
		this.variableParamIndex = variableParamIndex;
	}
}
