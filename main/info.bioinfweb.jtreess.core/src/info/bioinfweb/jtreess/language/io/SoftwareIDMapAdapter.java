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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;



public class SoftwareIDMapAdapter extends XmlAdapter<SoftwareIDMapAdapter.IDList, Map<String, String>>{
	public static class ID {
		@XmlAttribute
		public String type;
		
		@XmlValue
		public String id;

		private ID() {  // For JAXB.
			super();
		}
		
		public ID(String type, String id) {
			super();
			this.type = type;
			this.id = id;
		}
	}
	
	
	public static class IDList {
		@XmlElement(name = "id")
		public List<ID> ids = new ArrayList<SoftwareIDMapAdapter.ID>();
	}


	@Override
	public IDList marshal(Map<String, String> map) throws Exception {
		IDList result = new IDList();
		for (String type : map.keySet()) {
			result.ids.add(new ID(type, map.get(type)));
		}
		return result;
	}


	@Override
	public Map<String, String> unmarshal(IDList list) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		for (ID entry : list.ids) {
			result.put(entry.type, entry.id);
		}
		return result;
	}
}
