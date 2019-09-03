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


import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import info.bioinfweb.jtreess.execute.implementation.ConversionImplementation;
import info.bioinfweb.jtreess.language.io.ImplementationAdapter;
import info.bioinfweb.jtreess.language.io.XMLConstants;



public class UnitInformation extends BasicInformation {
	@XmlPath(XMLConstants.IMPLEMENTATION_CLASS_XPATH)
	@XmlJavaTypeAdapter(ImplementationAdapter.ConversionImplementationAdapter.class)
	private ConversionImplementation conversionImplementation;

	
	public UnitInformation() {
		super("unit");
	}
	

	public ConversionImplementation getConversionImplementation() {
		return conversionImplementation;
	}

	
	public void setConversionImplementation(ConversionImplementation conversionImplementation) {
		this.conversionImplementation = conversionImplementation;
	} 
}
