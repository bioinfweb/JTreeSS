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


import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import info.bioinfweb.jtreess.language.io.SoftwareIDMapAdapter;



@XmlRootElement(name = "software")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoftwareInformation {
	private String url;
	
	@XmlJavaTypeAdapter(SoftwareIDMapAdapter.class)
	private Map<String, String> ids = new TreeMap<String, String>();
	
	
	public String getUrl() {
		return url;
	}
	
	
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * Returns a map that contains ID types as keys and IDs as values (e.g., "bio.tools" -> "treegraph_2"). 
	 * 
	 * @return an editable map instance
	 */
	public Map<String, String> getIds() {
		return ids;
	}


	public void setIds(Map<String, String> ids) {  // For use in JAXB.
		this.ids = ids;
	}
}
