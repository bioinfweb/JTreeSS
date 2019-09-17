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


import info.bioinfweb.jtreess.TreeSSProcessor;



/**
 * This interface needs to be implemented by applications that want to make use of <i>JTreeSS</i>. It contains methods to provide information
 * necessary to process a <i>TreeSS</i> document, e.g., on the tree topology, metadata values or size information.
 * <p>
 * The actual formatting of the tree is done by multiple calls of {@link #setNodeFormat(Object, String, RuntimeValue)}. Implementations of this
 * interface need to be passed to {@link TreeSSProcessor#applyDocument(info.bioinfweb.jtreess.document.Document, ApplicationDataProvider)}.
 * 
 * @author Ben St&ouml;ver
 *
 * @param <N> the tree node type used within the application
 */
public interface ApplicationDataProvider<N> {
	public N getRoot();
	
	public int getChildCount(N parent);
	
	public N getChild(N parent, int index);
	
	public N getParent(N child);
	
	public double getNumericTreeMetadataValue();  //TODO Add parameter for metadata path. Should data structures from JPhyloIO be used here or respective classes from there be moved to commons?
	
	public double getNumericNodeMetadataValue(N node);  //TODO Add parameter for metadata path. Should data structures from JPhyloIO be used here or respective classes from there be moved to commons?
	//TODO Are other types of metadata values necessary? Should these even be generic to use theme, e.g., in external function implementations?
	
	/**
	 * Returns the number of levels of the target tree.
	 * 
	 * @return the length of longest path from the root to any leaf node
	 */
	public int getTreeHeight();
	
	//TODO Add methods to convert between units/gather size information.
	
	public void setTreeFormat(String selectorName, String propertyName, RuntimeValue value); 

	public void setNodeFormat(N node, String selectorName, String propertyName, RuntimeValue value); 
}
