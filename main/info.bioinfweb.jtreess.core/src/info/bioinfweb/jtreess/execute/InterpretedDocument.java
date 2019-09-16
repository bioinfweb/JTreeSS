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


import java.util.List;



public class InterpretedDocument<N> {
	//TODO Add data structure to store implementations that check whether a node is affected by a rule and the values for each property. 
	//     (That latter may also have to be calculated due to metadata dependencies or document-dependent units.)
	
	
	/**
	 * Applies all formats to the specified node (or any attached objects) of the target tree.
	 * 
	 * @param node the node in the phylogenetic target tree
	 * @param nodeIndices a list with the child index of {@code node} and all its parent nodes (If {@code node} is the root this list will be empty.) 
	 * @param dataProvider the data provider to be used to obtain metadataValues or topological information not contained in {@code nodeIndices}
	 */
	public void applyFormats(N node, List<Integer> nodeIndices, ApplicationDataProvider<N> dataProvider) {
		//TODO Iterate over all selector rules, check if the node is affected and apply all contained property rules
	}
}
