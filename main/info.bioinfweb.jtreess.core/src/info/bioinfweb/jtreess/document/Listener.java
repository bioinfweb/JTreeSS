package info.bioinfweb.jtreess.document;



import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import info.bioinfweb.jtreess.parser.TreeSSBaseListener;
import info.bioinfweb.jtreess.parser.TreeSSParser;


public class Listener extends TreeSSBaseListener {
	private Document document;
	
	public Document getDocument() {
		return document;
	}
	
	

	@Override public void enterUnitValue(TreeSSParser.UnitValueContext ctx) { }

	@Override public void exitUnitValue(TreeSSParser.UnitValueContext ctx) { }

	@Override public void enterExpression(TreeSSParser.ExpressionContext ctx) { 
		if (ctx.DIVIDE() != null) {
			//new Expression(ParamList.); 
		}
//		System.out.println(BufferedTokenStream.consume()); 
//		TestCompiler.lexer.nextToken();
//		CommonTokenStream.
//		if(ParamList.getExpressions().get(-1) != null  && lastToken = OPERATOR)
//		if (nextToken() == OPERATOR) {
//			if (OPERATOR == MINUS) {
//				substractExpression(ctx.IDENTIFIER(),ctx.IDENTIFIER().getNext()); 
//			}
//			if (OPERATOR == PLUS) {
//				addExpression(ctx,getNextExpression()); 
//			}
//			if (OPERATOR == DIVIDE) {
//				divideExpression(ctx,getNextExpression()); 
//			}
//			if (OPERATOR == STAR) {
//				multiplyExpression(ctx,getNextExpression()); 
//			}
//		}	
	}

	@Override public void exitExpression(TreeSSParser.ExpressionContext ctx) { }

	@Override public void enterParamList(TreeSSParser.ParamListContext ctx) { }

	@Override public void exitParamList(TreeSSParser.ParamListContext ctx) { }

	@Override public void enterFunction(TreeSSParser.FunctionContext ctx) { }

	@Override public void exitFunction(TreeSSParser.FunctionContext ctx) { }

	@Override public void enterValueFunction(TreeSSParser.ValueFunctionContext ctx) { }

	@Override public void exitValueFunction(TreeSSParser.ValueFunctionContext ctx) { }

	@Override public void enterValue(TreeSSParser.ValueContext ctx) {
		Value.testMethod(); 
		System.out.println ("enterValue: "+ (ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "null"));
	}

	@Override public void exitValue(TreeSSParser.ValueContext ctx) { 
	}

	@Override public void enterValues(TreeSSParser.ValuesContext ctx) { }

	@Override public void exitValues(TreeSSParser.ValuesContext ctx) { }

	@Override public void enterProperty(TreeSSParser.PropertyContext ctx) { }

	@Override public void exitProperty(TreeSSParser.PropertyContext ctx) { }

	@Override public void enterPropertyRule(TreeSSParser.PropertyRuleContext ctx) { }

	@Override public void exitPropertyRule(TreeSSParser.PropertyRuleContext ctx) { }

	@Override public void enterPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) { 
		getDocument().getSelectorRules().get(getDocument().getSelectorRules().size() - 1).setSelector(new Selector(Selector.SelectorType.PSEUDOFUNCTION));
  	}

	@Override public void exitPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) { }

	@Override public void enterPseudoClass(TreeSSParser.PseudoClassContext ctx) { 
		System.out.println("enterPseudoClass: " + (ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "null"));
		getDocument().getSelectorRules().get(getDocument().getSelectorRules().size() - 1).setSelector(new Selector(Selector.SelectorType.PSEUDOCLASS));
	}

	@Override public void exitPseudoClass(TreeSSParser.PseudoClassContext ctx) { }

	@Override public void enterPseudoSelector(TreeSSParser.PseudoSelectorContext ctx) { }

	@Override public void exitPseudoSelector(TreeSSParser.PseudoSelectorContext ctx) { }

	@Override public void enterUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) {
		getDocument().getSelectorRules().get(getDocument().getSelectorRules().size() - 1).setSelector(new Selector(Selector.SelectorType.UNIVERSAL_SELECTOR));
	}

	@Override public void exitUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) { }

	@Override public void enterSimpleSelector(TreeSSParser.SimpleSelectorContext ctx) {
		System.out.println("enterSimpleSelector: " + (ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "null"));
		getDocument().getSelectorRules().get(getDocument().getSelectorRules().size() - 1).setSelector(new Selector(Selector.SelectorType.SIMPLE_SELECTOR));
	}

	@Override public void exitSimpleSelector(TreeSSParser.SimpleSelectorContext ctx) { 
	}

	@Override public void enterIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		System.out.println("enterIdSelector: " + (ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "null"));
		getDocument().getSelectorRules().get(getDocument().getSelectorRules().size() - 1).setSelector(new Selector(Selector.SelectorType.ID_SELECTOR));
	}
	
	@Override public void exitIdSelector(TreeSSParser.IdSelectorContext ctx) { }

	@Override public void enterBasicSelector(TreeSSParser.BasicSelectorContext ctx) { }

	@Override public void exitBasicSelector(TreeSSParser.BasicSelectorContext ctx) { }

	@Override public void enterSelector(TreeSSParser.SelectorContext ctx) { }

	@Override public void exitSelector(TreeSSParser.SelectorContext ctx) { }

	@Override public void enterSelectorRule(TreeSSParser.SelectorRuleContext ctx) {
		getDocument().getSelectorRules().add(new SelectorRule());
	}

	@Override public void exitSelectorRule(TreeSSParser.SelectorRuleContext ctx) { }

	@Override public void enterRules(TreeSSParser.RulesContext ctx) { }
	
	@Override public void exitRules(TreeSSParser.RulesContext ctx) { }
	
	@Override public void enterDocument(TreeSSParser.DocumentContext ctx) {
		document = new Document();
	}
	
	@Override public void exitDocument(TreeSSParser.DocumentContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }

	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	
	@Override public void visitTerminal(TerminalNode node) { }

	@Override public void visitErrorNode(ErrorNode node) { }
}
