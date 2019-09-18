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


import javax.xml.bind.annotation.adapters.XmlAdapter;

import info.bioinfweb.jtreess.execute.implementation.LengthConversionImplementation;
import info.bioinfweb.jtreess.execute.implementation.DynamicValueImplementation;
import info.bioinfweb.jtreess.execute.implementation.FunctionImplementation;
import info.bioinfweb.jtreess.execute.implementation.Implementation;
import info.bioinfweb.jtreess.execute.implementation.SelectorImplementation;



public class ImplementationAdapter<I extends Implementation> extends XmlAdapter<String, I> {
	public static class ConversionImplementationAdapter extends ImplementationAdapter<LengthConversionImplementation> {
		public ConversionImplementationAdapter() {
			super(LengthConversionImplementation.class);
		}
	}
	
	
	public static class DynamicValueImplementationAdapter extends ImplementationAdapter<DynamicValueImplementation> {
		public DynamicValueImplementationAdapter() {
			super(DynamicValueImplementation.class);
		}
	}
	
	
	public static class FunctionImplementationAdapter extends ImplementationAdapter<FunctionImplementation> {
		public FunctionImplementationAdapter() {
			super(FunctionImplementation.class);
		}
	}
	
	
	public static class SelectorImplementationAdapter extends ImplementationAdapter<SelectorImplementation> {
		public SelectorImplementationAdapter() {
			super(SelectorImplementation.class);
		}
	}
	
	
	private Class<I> implementationClass;
	
	
	public ImplementationAdapter(Class<I> implementationClass) {
		super();
		this.implementationClass = implementationClass;
	}


	@Override
	public String marshal(I implementation) throws Exception {
		return implementation.getClass().getCanonicalName();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public I unmarshal(String className) throws Exception {
		Class<?> selectorClass = Class.forName(className);
		if (implementationClass.isAssignableFrom(selectorClass)) {
			return (I)selectorClass.newInstance();  //TODO Possibly use constructor with parameters here. 
		}
		else {
			throw new ClassNotFoundException("The found class " + selectorClass.getCanonicalName() + " does not inherit from " + 
					implementationClass.getCanonicalName() + ".");  //TODO Adjust message if Implementation or XXXImplementation should become an interface in the future.
		}
	}
}
