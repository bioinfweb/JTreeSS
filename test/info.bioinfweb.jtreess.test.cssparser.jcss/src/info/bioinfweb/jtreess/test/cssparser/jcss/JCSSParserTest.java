package info.bioinfweb.jtreess.test.cssparser.jcss;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import self.philbrown.cssparser.*;
public class JCSSParserTest {
		public static void main(String[] args) {
			try
			{
			    //First get the input stream. For example, from a file
			    InputStream is = new FileInputStream(new File("data\\Test.treess"));

			    //Then parse        
			    CSSParser parser = new CSSParser(is, new TestCSSHandler());
			    parser.parse();
			}
			catch (Throwable t)
			{
			    t.printStackTrace();
			}
			
		}
		
}
