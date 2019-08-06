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

import info.bioinfweb.jtreess.document.value.ColorValue;
import info.bioinfweb.jtreess.document.value.Value;



public class AnalyseConstant {
	public static List<ColorConstant> validColorNames = new ArrayList<ColorConstant>();
	public static List<Constant> validConstants = new ArrayList<Constant>();
	public static void analysisOfConstant(Value constant) {
		if (validColorNames.contains(constant.getText())) {
			ColorValue colorValue = new ColorValue(constant.getParent(), constant.getText());
		}
		else if (validConstants.contains(constant.getText())) {
		}
		else {
			System.out.println("The given constant is neither a color nor a usable constant.");
		}
	}
}
