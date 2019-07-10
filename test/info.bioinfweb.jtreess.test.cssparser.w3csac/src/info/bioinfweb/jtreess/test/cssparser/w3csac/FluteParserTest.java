package info.bioinfweb.jtreess.test.cssparser.w3csac;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.w3c.css.sac.CSSException;
import org.w3c.flute.parser.Parser;


public class FluteParserTest {
	
	protected static FluteParserTest fluteParser;
	
	public static void main(String[] args) {
	
		Parser parse = new Parser();
		try {
			parse.parseStyleSheet("http://bioinfweb.info/Code/sventon/repos/JTreeSS/show/trunk/test/info.bioinfweb.jtreess.test.main/data/Test.treess?revision=HEAD&format=raw");
		} catch (CSSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


}
