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


import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import info.bioinfweb.commons.text.StringUtils;
import info.bioinfweb.jtreess.language.LanguageDefinitions;
import info.bioinfweb.jtreess.language.model.EnumerationTypeInformation;
import info.bioinfweb.jtreess.language.model.FunctionPseudoFunctionInformation;
import info.bioinfweb.jtreess.language.model.PropertyInformation;
import info.bioinfweb.jtreess.language.model.SimpleSelectorInformation;
import info.bioinfweb.jtreess.language.model.SoftwareInformation;
import info.bioinfweb.jtreess.language.model.UnitInformation;
import info.bioinfweb.jtreess.language.model.ValueConstantInformation;



public class LanguageDefinitionReader {
	public static final String LANGUAGE_DEFINITION_FILE_EXTENSION = ".xml";
	
	public static final String SELECTORS_DIRECTORY = "selectors";
	public static final String PROPERTIES_DIRECTORY = "properties";
	public static final String CONSTANTS_DIRECTORY = "constants";
	public static final String FUNCTIONS_DIRECTORY = "functions";
	public static final String TYPES_DIRECTORY = "types";
	public static final String UNITS_DIRECTORY = "units";
	public static final String SOFTWARE_DIRECTORY = "software";
	
	
	public static JAXBContext createJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(SimpleSelectorInformation.class, PropertyInformation.class, ValueConstantInformation.class, 
				EnumerationTypeInformation.class, FunctionPseudoFunctionInformation.class, SoftwareInformation.class, UnitInformation.class);
	}
	
	
	private <I> void readMap(Class<I> infoClass, Map<String, I> map, File rootFolder, String directoryName) throws JAXBException {
		final Unmarshaller unmarshaller = createJAXBContext().createUnmarshaller();
		
		File[] files = new File(rootFolder.getAbsolutePath() + File.separator + directoryName).listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(LANGUAGE_DEFINITION_FILE_EXTENSION);
					}
				});
		
		for (File file : files) {
			map.put(StringUtils.cutEnd(file.getName(), LANGUAGE_DEFINITION_FILE_EXTENSION.length()), 
					unmarshaller.unmarshal(new StreamSource(file), infoClass).getValue());
		}
	}
	
	
	public LanguageDefinitions readDefinitions(File rootFolder) throws JAXBException {
		LanguageDefinitions result = new LanguageDefinitions();
		
		readMap(SimpleSelectorInformation.class, result.getSimpleSelectorInformation(), rootFolder, SELECTORS_DIRECTORY);
		readMap(PropertyInformation.class, result.getPropertyInformation(), rootFolder, PROPERTIES_DIRECTORY);
		readMap(ValueConstantInformation.class, result.getConstantsInformation(), rootFolder, CONSTANTS_DIRECTORY);
		readMap(FunctionPseudoFunctionInformation.class, result.getFunctionInformation(), rootFolder, FUNCTIONS_DIRECTORY);
		readMap(EnumerationTypeInformation.class, result.getEnumTypeInformation(), rootFolder, TYPES_DIRECTORY);
		readMap(UnitInformation.class, result.getUnitInformation(), rootFolder, UNITS_DIRECTORY);
		readMap(SoftwareInformation.class, result.getSoftwareInformation(), rootFolder, SOFTWARE_DIRECTORY);
		
		return result;
	}
}
