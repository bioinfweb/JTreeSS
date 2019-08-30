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


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.collections4.set.AbstractSetDecorator;

import info.bioinfweb.jtreess.language.io.EnumerationConstantsMapAdapter;



@XmlRootElement(name = "treeSSLangDefition")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnumerationTypeInformation extends BasicInformation {
	@XmlJavaTypeAdapter(EnumerationConstantsMapAdapter.class)
	private Map<String, String> constantsToDescriptionsMap;
	
	@XmlTransient
	private Set<String> constants;
	

	public EnumerationTypeInformation() {
		super("type");
		constantsToDescriptionsMap = new HashMap<>();
		constants = new AbstractSetDecorator<String>() {
			private static final long serialVersionUID = 1L;


			@Override
			public boolean add(String constant) {
				boolean result = !constantsToDescriptionsMap.containsKey(constant);
				if (result) {
					constantsToDescriptionsMap.put(constant, null);
				}
				return result;
			}

			
			@Override
			public boolean addAll(Collection<? extends String> constants) {
				boolean result = false;
				for (String constant : constants) {
					result = result || add(constant);
				}
				return result;
			}
		};
	}


	public Set<String> getConstants() {
		return constants;
	}
	
	
	public String getConstantDescription(String constant) {
		return constantsToDescriptionsMap.get(constant);
	}


	public Map<String, String> getConstantsToDescriptionsMap() {
		return constantsToDescriptionsMap;
	}
}
