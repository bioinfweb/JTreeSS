package info.bioinfweb.jtreess.reader;


import static org.junit.Assert.*;


import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import info.bioinfweb.jtreess.document.Document;
import info.bioinfweb.jtreess.document.SelectorRule;
import info.bioinfweb.jtreess.document.selector.ConcreteSelector;
import info.bioinfweb.jtreess.document.selector.Selector;
import info.bioinfweb.jtreess.document.selector.SelectorType;
import info.bioinfweb.jtreess.reader.parser.TreeSSLexer;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser;



public class SyntaxTreeListenerTest {
	private Document readDocument(String fileName) throws IOException {
		TreeSSLexer lexer = new TreeSSLexer(CharStreams.fromFileName("data\\" + fileName + ".treess"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ParseTree tree = new TreeSSParser(tokens).document(); 
		ParseTreeWalker walker = new ParseTreeWalker();
		SyntaxTreeListener listener = new SyntaxTreeListener();
		walker.walk(listener, tree);
		return listener.getDocument();
	}
	
	
	private void assertSelector(Selector selector, SelectorType type, String name) {
		assertNotNull(selector);
		assertEquals(type, selector.getType());
		assertEquals(name, selector.getName());
	}
	
	
	@Test
	public void testReadingBasicExample() throws Exception {
		Document document = readDocument("BasicExample");
		
		assertNotNull(document);
//		assertEquals(8, document.getSelectorRules().size());
		
		SelectorRule selectorRule = document.getSelectorRules().get(0);
//		assertSelector(selectorRule.getSelector(), SelectorType.SIMPLE_SELECTOR, "canvas");
		
		assertNotNull(selectorRule.getPropertyRules());

		//...
		
		selectorRule = document.getSelectorRules().get(1);
//		assertNotNull(selectorRule.getSelector());

	}
}
