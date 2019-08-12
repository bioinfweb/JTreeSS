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
package info.bioinfweb.jtreess.language;


import java.util.ArrayList;
import java.util.List;



public class BasicInformation {
	private String description; 
	private String example; 
	private List<SupportedSoftwareEntry> supportedSoftware = new ArrayList<SupportedSoftwareEntry>();
	private String introductoryVersion;  //TODO Which class should this have?
	private String removingVersion;  //TODO Which class should this have?
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getExample() {
		return example;
	}
	
	
	public void setExample(String example) {
		this.example = example;
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
