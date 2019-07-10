package info.bioinfweb.jtreess.test.cssparser.jcss;


import java.io.InputStream;
import java.util.List;

import self.philbrown.cssparser.CSSHandler;
import self.philbrown.cssparser.Declaration;
import self.philbrown.cssparser.FontFace;
import self.philbrown.cssparser.KeyFrame;
import self.philbrown.cssparser.RuleSet;
import self.philbrown.cssparser.Token;
import self.philbrown.cssparser.TokenSequence;



public class TestCSSHandler implements CSSHandler{

	@Override
	public void handleError(String arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleFontFace(FontFace arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream handleImport(TokenSequence arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleKeyframes(String arg0, List<KeyFrame> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleNamespace(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleNewCharset(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePage(TokenSequence arg0, List<Declaration> arg1) {
		// TODO Auto-generated method stub
		
	}

	
	private void printTokenSet(TokenSequence sequence) {
		for (Token token : sequence) {
			System.out.print("\"" + token.toString() + "\", ");
		}
	}
	
	@Override
	public void handleRuleSet(RuleSet ruleSet) {
		System.out.print("Selector: ");
		printTokenSet(ruleSet.getSelector());
		System.out.println();
		for (Declaration declaration : ruleSet.getDeclarationBlock()) {
			System.out.print("  property: ");
			printTokenSet(declaration.getProperty());
			System.out.println();
			
			System.out.print("  value: ");
			printTokenSet(declaration.getValue());
			System.out.println();
		}
	}

	@Override
	public boolean queryDocument(TokenSequence[] arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryMedia(TokenSequence[] arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supports(TokenSequence arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
