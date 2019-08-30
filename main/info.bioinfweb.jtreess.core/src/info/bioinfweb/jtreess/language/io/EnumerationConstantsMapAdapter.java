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


import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;



public class EnumerationConstantsMapAdapter extends XmlAdapter<List<EnumerationConstantsMapAdapter.Entry>, Map<String, String>> {
	public static class Entry {
		public String name;
		public String description;
	}
	
	
	@Override
	public List<Entry> marshal(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Map<String, String> unmarshal(List<Entry> list) throws Exception {
		return null;  //TODO Returning a new map does not work. The current map should be edited, since the source of the decorator set "constants" would not be updated when the map is replaced.
	}
}
