

import org.antlr.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class TestCompiler {

	
	public static void main( String[] args) throws Exception {

		TreeSSLexer lexer = new TreeSSLexer(CharStreams.fromFileName("data\\Test.treess"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		TreeSSParser parser = new TreeSSParser(tokens);
		TreeSSParser.DocumentContext tree = parser.document(); // begin parsing at rule 'r'
//		for (int i = tree.getStart().getTokenIndex(); i < tree.getEn().getTokenIndex(); i++) {
//			//tree.to
//		}
//		System.out.println(tree.getStart().getType());
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
//		System.out.println (tree.getChild(5).getText()); 
	}
}
