package info.bioinfweb.jtreess.language.io;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.io.File;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import info.bioinfweb.jtreess.language.LanguageDefinitions;
import info.bioinfweb.jtreess.language.model.EnumerationTypeInformation;
import info.bioinfweb.jtreess.language.model.FunctionPseudoFunctionInformation;
import info.bioinfweb.jtreess.language.model.PropertyInformation;
import info.bioinfweb.jtreess.language.model.SimpleSelectorInformation;
import info.bioinfweb.jtreess.language.model.SoftwareInformation;
import info.bioinfweb.jtreess.language.model.UnitInformation;
import info.bioinfweb.jtreess.language.model.ValueConstantInformation;



public class LanguageDefinitionReaderTest {
	private <I> void assertMap(Class<I> infoClass, Map<String, I> map, String... expectedNames) {
		assertEquals(expectedNames.length, map.size());
		
		for (String name : expectedNames) {
			assertTrue(map.containsKey(name));
			assertTrue(infoClass.isInstance(map.get(name)));
		}
	}
	
	
	@Test
	public void test_readDefinitions() throws JAXBException {
		LanguageDefinitions definitions = new LanguageDefinitionReader().readDefinitions(new File("data/language"));
		
		assertMap(ValueConstantInformation.class, definitions.getConstantsInformation(), "blue");
		assertMap(FunctionPseudoFunctionInformation.class, definitions.getFunctionInformation(), "matches");
		assertMap(PropertyInformation.class, definitions.getPropertyInformation(), "font-size");
		assertMap(SimpleSelectorInformation.class, definitions.getSimpleSelectorInformation(), "node");
		assertMap(SoftwareInformation.class, definitions.getSoftwareInformation(), "TreeGraph_2");
		assertMap(EnumerationTypeInformation.class, definitions.getEnumTypeInformation(), "text-align");
		assertMap(UnitInformation.class, definitions.getUnitInformation(), "blu", "pt");
	}
}
