package info.bioinfweb.jtreess.test.cssparser.jstyleparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import cz.vutbr.web.*;
import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.csskit.antlr4.CSSInputStream;

public class JStyleParserTest {
	
    public static void main(String[] args) throws CSSException, IOException {

//        CSSFactory.getUsedStyles("data\\Test.treess", base, media);
//        CSSInputStream css = CSSInputStream.stringStream("data\\Test.treess");       
    	
    	String css = "data\\Test.treess";
    	System.out.println(css);
    	//parse the style sheet
    	StyleSheet ss = CSSFactory.parse(css, "UTF-8");
    	if (ss.isEmpty()) System.out.println("StyleSheet is void");
    	
//    	StyleSheet sheet = CSSFactory.parse(css);
//    	Analyzer analyzer = new Analyzer(sheet);
//    	StyleMap map = analyzer.evaluateDOM(doc, "all", true);
    	
    	//access the rules and declarations
    	RuleSet rule = (RuleSet) ss.get(0);       //get the first rule
    	CombinedSelector selector = rule.getSelectors()[0]; //read the 'div' selector
    	Declaration bgDecl = rule.get(0);            //read the 'background-color' declaration
    	String bgProperty = bgDecl.getProperty();    //read the property name
    	TermColor color = (TermColor) bgDecl.get(0); //read the color

    	//print the results
    	System.out.println(selector);   //prints 'div'
    	System.out.println(bgProperty); //prints 'background-color'
    	System.out.println(color);      //prints '#ff0000'

    	//or even print the entire style sheet (formatted)
    	System.out.println(ss);

    }


}
