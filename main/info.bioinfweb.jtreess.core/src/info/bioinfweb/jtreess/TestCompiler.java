package info.bioinfweb.jtreess;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import info.bioinfweb.jtreess.document.Listener;
import info.bioinfweb.jtreess.document.SelectorRule;
import info.bioinfweb.jtreess.parser.TreeSSLexer;
import info.bioinfweb.jtreess.parser.TreeSSParser;



public class TestCompiler {

	
	public static void main( String[] args) throws Exception {

		TreeSSLexer lexer = new TreeSSLexer(CharStreams.fromFileName("data\\Test.treess"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		TreeSSParser parser = new TreeSSParser(tokens);
//		TreeSSParser.DocumentContext tree = parser.document(); // begin parsing at rule 'r'
//		System.out.println(tree.toStringTree(parser)); 
//		parser.addParseListener(new Listener());
		
//		List <? extends Token> tokenList = new ArrayList<>();
//		tokenList = (List<? extends Token>) lexer.getAllTokens();
//		 
//		for (Token token : tokenList) {
//			System.out.println(lexer.getVocabulary().getSymbolicName(token.getType()));
//		}
		
//		
		ParseTree tree1 = parser.document(); 
		ParseTreeWalker walker = new ParseTreeWalker();
		
		Listener listener = new Listener(); 
		
		walker.walk(listener, tree1);
		
		for (SelectorRule rule : listener.getDocument().getSelectorRules()) {
			if (rule.getSelector() != null) {
				System.out.println(rule.getSelector().getType());
			}
			else {
				System.out.println("other selector");
			}
		}
//	
//		System.out.println(tree.getStart().getType());
//		System.out.println (tree.getChild(5).getText()); 
	}
	

}
