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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;



@XmlAccessorType(XmlAccessType.FIELD)
public class BasicInformation {
	@XmlAttribute(name="type")
	private String treeSSLangDefitionType = "simpleSelector";	

	private String description;
	
	@XmlElement(name="example")
	private List<Example> examples = new ArrayList<Example>();
	
	@XmlElement(name="firstVersion")
	private String introductoryVersion;  //TODO Which class should this have?
	
	@XmlElement(name="versionAfter")
	private String removingVersion;  //TODO Which class should this have?
	
	@XmlElement(name="support")
	private List<SupportedSoftwareEntry> supportedSoftware = new ArrayList<SupportedSoftwareEntry>();

	
	public BasicInformation(String treeSSLangDefitionType) {
		super();
		this.treeSSLangDefitionType = treeSSLangDefitionType;
	}


	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<Example> getExamples() {
		return examples;
	}
	
	
	public List<SupportedSoftwareEntry> getSupportedSoftware() {
		return supportedSoftware;
	}


	public String getIntroductoryVersion() {
		return introductoryVersion;
	}


	public void setIntroductoryVersion(String introductoryVersion) {
		this.introductoryVersion = introductoryVersion;
	}


	public String getRemovingVersion() {
		return removingVersion;
	}


	public void setRemovingVersion(String removingVersion) {
		this.removingVersion = removingVersion;
	}
}
