package info.bioinfweb.jtreess.test;


import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import info.bioinfweb.jtreess.language.model.Example;
import info.bioinfweb.jtreess.language.model.SimpleSelectorInformation;
import info.bioinfweb.jtreess.language.model.SoftwareVersionInterval;
import info.bioinfweb.jtreess.language.model.SupportedSoftwareEntry;



public class MarshallerTest {
	public static void main(String[] args) throws JAXBException, MalformedURLException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SimpleSelectorInformation.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		 
		SimpleSelectorInformation selector = new SimpleSelectorInformation();
		selector.setIntroductoryVersion("1.2");
		selector.setRemovingVersion("2.1");
		selector.setDescription("Some description");
		
		Example example = new Example();
		example.setCode("node {\n  text-height: 0.8em;\n}");
		example.setDescription("Sets the font size of all nodes in a tree to 80 % of the default font size of the document.");
		example.setTree(new URL("http://example.com/Tree.nexml"));
		selector.getExamples().add(example);
		
		SupportedSoftwareEntry entry = new SupportedSoftwareEntry();
		entry.setName("TreeGraph 2");
		entry.getSupportedVersions().add(new SoftwareVersionInterval("1.2", "2.1"));
		entry.getSupportedVersions().add(new SoftwareVersionInterval("2.5"));
		selector.getSupportedSoftware().add(entry);
		 
		jaxbMarshaller.marshal(selector, System.out);
	}
}
