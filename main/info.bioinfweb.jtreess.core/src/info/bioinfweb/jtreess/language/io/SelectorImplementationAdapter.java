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

import info.bioinfweb.jtreess.execute.implementation.SelectorImplementation;



//TODO Extract superclass when all XXXImplementation classes are defined that implements shared functionality for all XXXImplementationAdapter classes (or make this class independent of the actual class type if possible).
public class SelectorImplementationAdapter extends XmlAdapter<String, SelectorImplementation> {
	@Override
	public String marshal(SelectorImplementation implementation) throws Exception {
		return implementation.getClass().getCanonicalName();
	}

	
	@Override
	public SelectorImplementation unmarshal(String className) throws Exception {
		Class<?> selectorClass = Class.forName(className);
		if (SelectorImplementation.class.isAssignableFrom(selectorClass)) {
			return (SelectorImplementation)selectorClass.newInstance();  //TODO Possibly use constructor with parameters here. 
		}
		else {
			throw new ClassNotFoundException("The found class " + selectorClass.getCanonicalName() + " does not inherit from " + 
					SelectorImplementation.class.getCanonicalName() + ".");  //TODO Adjust message if SelectorImplementation should become an interface in the future.
		}
	}
}
