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
package info.bioinfweb.jtreess.document.selector;


import java.util.ArrayList;
import java.util.List;

import info.bioinfweb.jtreess.document.DocumentElement;



/**
 * Represents a selector that is not a pseudo-class or pseudo-function and stores possibly associated pseudo-selectors.
 * <p>
 * This class be used to model universal, simple, and ID selectors.
 * 
 * @author Ben St&ouml;ver
 */
public class ConcreteNonPseudoSelector extends ConcreteSelector implements NonPseudoSelector {
	private List<PseudoSelector> pseudoSelectors = new ArrayList<PseudoSelector>();
	
	
	public ConcreteNonPseudoSelector(DocumentElement parent, SelectorType type, String name) {
		super(parent, type, name);
		if (type.isPseudoSelector()) {
			throw new IllegalArgumentException("The type " + type + " is not a valid type for a non-pseudo-selector.");
		}
	}

	
	@Override
	public List<PseudoSelector> getPseudoSelectors() {
		return pseudoSelectors;
	}
}