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



/**
 * Instances of this class store an interval of software versions to be used with {@link SupportedSoftwareEntry}.
 * <p>
 * The end may be {@code null} of the support is ongoing. Note that only the last entry in a list may have no defined end. 
 * 
 * @author Ben Stouml;ver
 * @author Charlotte Schmitt
 */
public class SoftwareVersionInterval {
	private String start; 
	private String end;
	
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param start the start version of the interval
	 * @param end the end version of the interval
	 */
	public SoftwareVersionInterval(String start, String end) {
		super();
		setStart(start);
		setEnd(end);
	}


	/**
	 * Creates a new instance of this class with no defined end.
	 * 
	 * @param start the start version of the interval
	 */
	public SoftwareVersionInterval(String start) {
		super();
		setStart(start);
		setEnd(null);
	}


	public String getStart() {
		return start;
	}
	
	
	/**
	 * Sets the start version of this interval.
	 * 
	 * @param start the new start version
	 * @throws IllegalArgumentException if {@code null} or an empty string was specified
	 */
	public void setStart(String start) {
		if (start == null) {
			throw new IllegalArgumentException("The start version must not be null.");
		}
		else if ("".equals(start)) {
			throw new IllegalArgumentException("The start version must not be an empty string.");
		}
		else {
			this.start = start;
		}
	}
	
	
	public String getEnd() {
		return end;
	}
	
	
	/**
	 * Sets the end version of this interval.
	 * 
	 * @param end the new end version (May be {@code null}.)
	 * @throws IllegalArgumentException if an empty string was specified
	 */
	public void setEnd(String end) {
		if ("".equals(end)) {
			throw new IllegalArgumentException("The end version must not be an empty string. Specify null if no end is defined.");
		}
		else {
			this.end = end;
		}
	}
	
	
	/**
	 * Determines whether this interval has an defined end.
	 * <p>
	 * If this method returns {@code false}, {@link #getEnd()} will return {@code null}.
	 * 
	 * @return {@code true} if a defined end version is stored or {@code false} otherwise.
	 */
	public boolean hasKnownEnd() {
		return end != null;
	}
}
