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


import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import info.bioinfweb.jtreess.execute.implementation.SelectorImplementation;
import info.bioinfweb.jtreess.language.io.ImplementationAdapter.SelectorImplementationAdapter;



@XmlRootElement(name = "treeSSLangDefition")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleSelectorInformation extends BasicInformation {
	@XmlElementWrapper(name="validProperties")
	@XmlElement(name="property")
	private Set<String> validProperties = new HashSet<String>();
	
	@XmlElementWrapper(name="validPseudoSelectors")
	@XmlElement(name="selector")
	private Set<String> validPseudoSelectors = new HashSet<String>();
	
	@XmlJavaTypeAdapter(SelectorImplementationAdapter.class)  //TODO Adjust to XML structure when defined. (Will probably contain language-specific parent tags.)
	private SelectorImplementation selectorImplementation;
	
	
	public SimpleSelectorInformation() {
		super("simpleSelector");
	}


	public Set<String> getValidProperties() {
		return validProperties;
	}
	
	
	public Set<String> getValidPseudoSelectors() {
		return validPseudoSelectors;
	}


	public SelectorImplementation getSelectorImplementation() {
		return selectorImplementation;
	}
}
