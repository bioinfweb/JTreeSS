package info.bioinfweb.jtreess.language.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.bioinfweb.jtreess.test.JTreeSSTestTools;



public class SoftwareInformationTest {
	@Test
	public void test_Unmashalling() throws JAXBException {
		SoftwareInformation info = JTreeSSTestTools.createJAXBContext().createUnmarshaller().unmarshal(
				new StreamSource(new File("data/language/software/TreeGraph_2.xml")), SoftwareInformation.class).getValue();
		
		assertEquals("http://treegraph.bioinfweb.info/", info.getUrl());
		
		assertEquals(1, info.getIds().size());
		assertTrue(info.getIds().containsKey("bio.tools"));
		assertEquals("treegraph_2", info.getIds().get("bio.tools"));
	}
}
