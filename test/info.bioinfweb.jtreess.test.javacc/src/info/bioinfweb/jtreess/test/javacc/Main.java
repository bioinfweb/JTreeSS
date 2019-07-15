package info.bioinfweb.jtreess.test.javacc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import info.bioinfweb.jtreess.test.javacc.parser.TestFileParser;

public class Main {
	public static void main(String[] args) {
		try {
			TestFileParser parser = new TestFileParser(new FileInputStream("data\\Test.treess"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
