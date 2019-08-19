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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "support")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupportedSoftwareEntry {
	@XmlElement(name="software")
	private String name;
	
	@XmlElement(name="versionInterval")
	private List<SoftwareVersionInterval> supportedVersions = new ArrayList<SoftwareVersionInterval>();
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}


	public List<SoftwareVersionInterval> getSupportedVersions() {
		return supportedVersions;
	}
}
