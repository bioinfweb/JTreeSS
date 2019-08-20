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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlReadTransformer;
import org.eclipse.persistence.oxm.annotations.XmlTransformation;
import org.eclipse.persistence.oxm.annotations.XmlWriteTransformer;
import org.eclipse.persistence.oxm.annotations.XmlWriteTransformers;

import info.bioinfweb.jtreess.execute.RuntimeValue;
import info.bioinfweb.jtreess.execute.implementation.DynamicValueImplementation;
import info.bioinfweb.jtreess.language.io.RuntimeValueAttributeTransformer;
import info.bioinfweb.jtreess.language.io.RuntimeValueTypeFieldTransformer;
import info.bioinfweb.jtreess.language.io.RuntimeValueValueFieldTransformer;
import info.bioinfweb.jtreess.language.io.XMLConstants;



/**
 * Models the value of a <i>TreeSS</i> constant. Its type can be determined using {@link #getValue()} and then {@link RuntimeValue#getType()}. 
 * 
 * @author Ben St&ouml;ver
 * @author Charlotte Schmitt
 */
@XmlRootElement(name = "treeSSLangDefition")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValueConstantInformation extends BasicInformation {
	@XmlTransformation
	@XmlReadTransformer(transformerClass=RuntimeValueAttributeTransformer.class)
  @XmlWriteTransformers({
      @XmlWriteTransformer(xmlPath=XMLConstants.TAG_VALUE + "/text()", transformerClass=RuntimeValueValueFieldTransformer.class),
      @XmlWriteTransformer(xmlPath=XMLConstants.ATTR_TYPE + "/text()", transformerClass=RuntimeValueTypeFieldTransformer.class),  //TODO Is "attribute()" correct?
  })  //TODO Is writing even necessary?
	private RuntimeValue value;
	
	private DynamicValueImplementation dynamicValueImplementation;  //TODO Is this necessary or can dynamic values just be written into the value property? If it is, should there still be a value property at the same time?
	
	
	public ValueConstantInformation() {
		super("constant");
	}
	

	public RuntimeValue getValue() {
		return value;
	}
	
	
	public void setValue(RuntimeValue value) {
		this.value = value;
	}
	
	
	public DynamicValueImplementation getDynamicValueImplementation() {
		return dynamicValueImplementation;
	}
}
