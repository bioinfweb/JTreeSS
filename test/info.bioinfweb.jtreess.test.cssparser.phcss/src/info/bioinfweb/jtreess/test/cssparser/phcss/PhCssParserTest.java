package info.bioinfweb.jtreess.test.cssparser.phcss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import com.helger.css.ECSSVersion;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.reader.CSSReader;


public class PhCssParserTest {
	
	protected static PhCssParserTest oParser;

    public static void main(String[] args) {

            oParser = new PhCssParserTest();
//            oParser.readCSS30(new File("data\\Test.treess"));

            if (oParser.readCSS30(new File("data\\Test.treess")) != null) {

                System.out.println("Parsing completed OK");

            } else {

                System.out.println("Unable to parse CSS");

            }   
    }


    public static CascadingStyleSheet readCSS30 (final File aFile)
    {
      // UTF-8 is the fallback if neither a BOM nor @charset rule is present
      final CascadingStyleSheet aCSS = CSSReader.readFromFile (aFile, StandardCharsets.UTF_8, ECSSVersion.CSS30);
      if (aCSS == null)
      {
        // Most probably a syntax error
        System.out.println ("Failed to read CSS - please see previous logging entries!");
        return null;
      }
      return aCSS;
    }
    
    

}


