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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;



/**
 * Adapter used to read and write enumeration constants and their descriptions.
 * <p>
 * The sole purpose of this class is to represent the {@link Map} in XML using different tag names then the default ones {@code entry}, 
 * {@code key} and {@code value}. (Otherwise the automatic JAXB transformation of {@link Map} would have been sufficient.)
 * 
 * @author Ben St&ouml;ver
 */
public class EnumerationConstantsMapAdapter extends XmlAdapter<EnumerationConstantsMapAdapter.Constants, Map<String, String>> {
	public static class Constant {
		public String name;
		public String description;
		
		private Constant() {  // For JAXB.
			super();
		}
		
		public Constant(String name, String description) {
			super();
			this.name = name;
			this.description = description;
		}
	}
	
	
	public static class Constants {
		public List<Constant> constant = new ArrayList<EnumerationConstantsMapAdapter.Constant>();
	}
	
	
	@Override
	public Constants marshal(Map<String, String> map) throws Exception {
		Constants result = new Constants();
		for (String constant : map.keySet()) {
			result.constant.add(new Constant(constant, map.get(constant)));
		}
		return result;
	}
	

	@Override
	public Map<String, String> unmarshal(Constants list) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		for (Constant entry : list.constant) {
			result.put(entry.name, entry.description);
		}
		return result;
	}
}
