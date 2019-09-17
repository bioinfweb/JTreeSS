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
package info.bioinfweb.jtreess.execute.implementation.selectorpseudofunction;


import java.util.List;

import info.bioinfweb.jtreess.execute.ApplicationDataProvider;
import info.bioinfweb.jtreess.execute.RuntimeValue;
import info.bioinfweb.jtreess.execute.implementation.FunctionImplementation;
import info.bioinfweb.jtreess.execute.implementation.SelectorImplementation;



public abstract class AbstractPseudofunctionImplementation implements FunctionImplementation {
	protected abstract <N> SelectorImplementation determineSelectorImplementation(List<RuntimeValue> parameters, N node, List<Integer> nodeIndices, 
			ApplicationDataProvider<N> dataProvider);
	
	
	@Override
	public <N> RuntimeValue execute(List<RuntimeValue> parameters, N node, List<Integer> nodeIndices, ApplicationDataProvider<N> dataProvider) {
		return new RuntimeValue(determineSelectorImplementation(parameters, node, nodeIndices, dataProvider));
	}
}
