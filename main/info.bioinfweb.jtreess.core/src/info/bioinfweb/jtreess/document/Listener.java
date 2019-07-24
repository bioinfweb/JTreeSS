package info.bioinfweb.jtreess.document;



import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import info.bioinfweb.jtreess.document.expression.Expression;
import info.bioinfweb.jtreess.document.expression.Multiplication;
import info.bioinfweb.jtreess.document.selector.IdSelector;
import info.bioinfweb.jtreess.document.selector.PseudoClass;
import info.bioinfweb.jtreess.document.selector.Selector;
import info.bioinfweb.jtreess.document.selector.SimpleSelector;
import info.bioinfweb.jtreess.document.selector.UniversalSelector;
import info.bioinfweb.jtreess.parser.TreeSSBaseListener;
import info.bioinfweb.jtreess.parser.TreeSSParser;
import info.bioinfweb.jtreess.parser.TreeSSParser.DocumentContext;
import info.bioinfweb.jtreess.parser.TreeSSParser.SelectorRuleContext;
import info.bioinfweb.jtreess.parser.TreeSSParser.SimpleSelectorContext;


public class Listener extends TreeSSBaseListener {
	private Document document;
	private Stack<DocumentElement> parents = new Stack<DocumentElement>();
	
	
	public Document getDocument() {
		return document;
	}

	
	@Override
	public void enterDocument(TreeSSParser.DocumentContext ctx) {
		document = new Document();
		parents.push(getDocument());
	}
	
	
	@Override
	public void exitDocument(DocumentContext ctx) {
		parents.pop();
	}


	@Override public void enterSelectorRule(TreeSSParser.SelectorRuleContext ctx) {
		SelectorRule rule = new SelectorRule(parents.peek());
		getDocument().getSelectorRules().add(rule);
	}
	

	@Override
	public void exitSelectorRule(SelectorRuleContext ctx) {
		parents.pop();
	}


	@Override
	public void enterSimpleSelector(TreeSSParser.SimpleSelectorContext ctx) {
		SelectorRule rule = (SelectorRule)parents.peek();
		SimpleSelector simpleSelector = new SimpleSelector(rule, ctx.IDENTIFIER().getText());
		rule.setSelector(simpleSelector);
		parents.push(simpleSelector);
	}
	
	
	@Override
	public void exitSimpleSelector(SimpleSelectorContext ctx) {
		parents.pop();
	}

	
	@Override
	public void enterUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) {
		SelectorRule rule = (SelectorRule)parents.peek();
		UniversalSelector universalSelector = new UniversalSelector(rule, Selector.SelectorType.UNIVERSAL_SELECTOR);
		rule.setSelector(universalSelector);
		parents.push(universalSelector); 
	}
	
	@Override
	public void exitUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) {
		parents.pop();
	}
	
	@Override public void enterIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		SelectorRule rule = (SelectorRule)parents.peek();
		IdSelector idSelector = new IdSelector(rule, Selector.SelectorType.ID_SELECTOR);
		rule.setSelector(idSelector);
		parents.push(idSelector); 
	}
	
	@Override public void exitIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		parents.pop();
	}


	@Override public void enterPseudoClass(TreeSSParser.PseudoClassContext ctx) { 
		SelectorRule rule = (SelectorRule)parents.peek();
		PseudoClass pseudoClass = new PseudoClass(rule, Selector.SelectorType.PSEUDOCLASS);
		rule.setSelector(pseudoClass);
		parents.push(pseudoClass); 
	}

	@Override public void exitPseudoClass(TreeSSParser.PseudoClassContext ctx) {
		parents.pop();
	}
	
	@Override public void enterUnitValue(TreeSSParser.UnitValueContext ctx) { }



	@Override public void enterExpression(TreeSSParser.ExpressionContext ctx) { 
		if (ctx.STAR() != null) {
			DocumentElement parent = parents.peek();
			Multiplication multiplication = new Multiplication(parent);
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(multiplication);
			}
			else if (parent instanceof ParamList) {
				((ParamList)parent).getExpressions().add(multiplication);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(multiplication);
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
  	}

	@Override public void exitPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) { }


	@Override public void enterPseudoSelector(TreeSSParser.PseudoSelectorContext ctx) { }

	@Override public void exitPseudoSelector(TreeSSParser.PseudoSelectorContext ctx) { }



	@Override public void enterBasicSelector(TreeSSParser.BasicSelectorContext ctx) { }

	@Override public void exitBasicSelector(TreeSSParser.BasicSelectorContext ctx) { }

	





	@Override public void enterRules(TreeSSParser.RulesContext ctx) { }
	
	@Override public void exitRules(TreeSSParser.RulesContext ctx) { }


	@Override public void enterEveryRule(ParserRuleContext ctx) { }

	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	
	@Override public void visitTerminal(TerminalNode node) { }

	@Override public void visitErrorNode(ErrorNode node) { }
}
