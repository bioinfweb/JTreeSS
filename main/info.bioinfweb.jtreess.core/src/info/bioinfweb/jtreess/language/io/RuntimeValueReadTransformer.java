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
package info.bioinfweb.jtreess.language.io;


import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.foundation.AbstractTransformationMapping;
import org.eclipse.persistence.mappings.transformers.AttributeTransformer;
import org.eclipse.persistence.oxm.record.DOMRecord;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;

import info.bioinfweb.jtreess.execute.RuntimeValue;



public class RuntimeValueReadTransformer implements AttributeTransformer, XMLConstants {
	private AbstractTransformationMapping mapping;
	
	
	@Override
	public void initialize(AbstractTransformationMapping mapping) {
		this.mapping = mapping;
	}


	@Override
	public Object buildAttributeValue(Record record, Object instance, Session session) {
		String type = null;
		String value = null;
		
		for (DatabaseField field : mapping.getFields()) {
			if (field.getName().contains(ATTR_TYPE)) {
				type = (String)record.get(field);
			} 
			else if (field.getName().contains(TAG_VALUE)) {
				value = (String)record.get(field);
			}
		}
		
		System.out.println(type);
		System.out.println(value);
		
		return new RuntimeValue();
	}
}
