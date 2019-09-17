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

import info.bioinfweb.commons.collections.CollectionUtils;
import info.bioinfweb.jtreess.execute.ApplicationDataProvider;
import info.bioinfweb.jtreess.execute.RuntimeValue;
import info.bioinfweb.jtreess.execute.implementation.SelectorImplementation;



/**
 * Implements the pseudofunction {@code nth-on-level(<numeric value>)}. It selects the nth node on every level of the tree.
 * <p>
 * Note that a level is considered independent of the actual parent node and at most one node per level will be selected. This is different 
 * from {@link NthChildPseudofunctionImplementation} which selects the nth child of each parent node and could select multiple nodes on the 
 * same level if there are several parent nodes with a sufficient number of child nodes. 
 * 
 * @author Ben St&ouml;ver
 */
public class NthOnLevelPseudofunctionImplementation extends AbstractPseudofunctionImplementation {
	private static class NthOnLevelSelectorImplementation extends SelectorImplementationAdapter {
		private int index;
		
		
		public NthOnLevelSelectorImplementation(int index) {
			super();
			this.index = index;
		}

		
		@Override
		public <N> boolean affectsNode(N node, List<Integer> nodeIndices, ApplicationDataProvider<N> dataProvider) {
			if (!nodeIndices.isEmpty()) {
				int currentIndex = CollectionUtils.getLastElement(nodeIndices);
				for (int level = nodeIndices.size() - 2; level > 0; level--) {  // Start at the level above the current node.
					//if (nodeIndices.get(level))
				}
			}
			return false;
		}
	}
	
	
	@Override
	protected <N> SelectorImplementation determineSelectorImplementation(List<RuntimeValue> parameters, N node,
			List<Integer> nodeIndices, ApplicationDataProvider<N> dataProvider) {

		return null;
	}
}
