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
package info.bioinfweb.languagedefinition;


import java.util.ArrayList;
import java.util.List;

import info.bioinfweb.jtreess.document.value.Value;



public class AnalyseConstant {
	
	public static void analyseValueConstant(Value constant) {
		final List<ColorConstant> validColorNames = new ArrayList<ColorConstant>(); 
		// Could be also built with a HashMap consisting out of two strings which will be set here
		if (validColorNames.contains(constant)) {
			// It's a color --> set color for property
		}
//		else if (otherListOfValidNames.contains(constant)) {}
		else {
			System.out.println("The given constant is neither a color nor a usable constant.");
		}
	}
}
