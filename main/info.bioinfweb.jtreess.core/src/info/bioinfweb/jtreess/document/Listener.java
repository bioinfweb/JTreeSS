/*
 * JTreeSS - A Java library for reading and evaluating TreeSS documents
 * Copyright (C) 2019 Ben Stöver, Charlotte Schmitt
 * <http://bioinfweb.info/JTreeSS>
 * 
 * This file is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This file is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package info.bioinfweb.jtreess.document;



import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import info.bioinfweb.jtreess.document.expression.Addition;
import info.bioinfweb.jtreess.document.expression.Division;
import info.bioinfweb.jtreess.document.expression.Expression;
import info.bioinfweb.jtreess.document.expression.Multiplication;
import info.bioinfweb.jtreess.document.expression.ParanExpression;
import info.bioinfweb.jtreess.document.expression.Subtraction;
import info.bioinfweb.jtreess.document.selector.IdSelector;
import info.bioinfweb.jtreess.document.selector.PseudoClass;
import info.bioinfweb.jtreess.document.selector.PseudoFunction;
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
		System.out.println("enterDocument: " + parents.size());
		document = new Document();
		parents.push(getDocument());
	}
	
	
	@Override
	public void exitDocument(DocumentContext ctx) {
		System.out.println("exitDocument " + parents.size());
		parents.pop();
	}


	@Override 
	public void enterSelectorRule(TreeSSParser.SelectorRuleContext ctx) {
		System.out.println("enterSelectorRule " + parents.size());
		SelectorRule rule = new SelectorRule(parents.peek());
		getDocument().getSelectorRules().add(rule);
		parents.push(rule);
	}
	

	@Override
	public void exitSelectorRule(SelectorRuleContext ctx) {
		System.out.println("exitSelectorRule " + parents.size());
		parents.pop();
	}


	@Override
	public void enterSimpleSelector(TreeSSParser.SimpleSelectorContext ctx) {
		System.out.println("enterSimpleSelector " + parents.size());
		SelectorRule rule = (SelectorRule)parents.peek();
		SimpleSelector simpleSelector = new SimpleSelector(rule, ctx.IDENTIFIER().getText());
		rule.setSelector(simpleSelector);
		parents.push(simpleSelector);
	}
	
	
	@Override
	public void exitSimpleSelector(SimpleSelectorContext ctx) {
		System.out.println("exitSimpleSelector " + parents.size());
		parents.pop();
	}

	
	@Override
	public void enterUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) {
		System.out.println("enterUniversalSelector: " + parents.size());
		SelectorRule rule = (SelectorRule)parents.peek();
		UniversalSelector universalSelector = new UniversalSelector(rule, Selector.SelectorType.UNIVERSAL_SELECTOR);
		rule.setSelector(universalSelector);
		parents.push(universalSelector); 
	}
	
	@Override
	public void exitUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) {
		System.out.println("exitUniversalSelector: " + parents.size());
		parents.pop();
	}
	
	@Override public void enterIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		System.out.println("enterIdSelector: " + parents.size());
		SelectorRule rule = (SelectorRule)parents.peek();
		IdSelector idSelector = new IdSelector(rule, Selector.SelectorType.ID_SELECTOR);
		rule.setSelector(idSelector);
		parents.push(idSelector); 
	}
	
	@Override 
	public void exitIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		System.out.println("exitIdSelector: " + parents.size());
		parents.pop();
	}


	@Override 
	public void enterPseudoClass(TreeSSParser.PseudoClassContext ctx) { 
		System.out.println("enterPseudoClass: " + parents.size());
		DocumentElement parent = parents.peek();
		PseudoClass pseudoClass = new PseudoClass(parent, Selector.SelectorType.PSEUDOCLASS);
		if (parent instanceof SelectorRule) {				
			SelectorRule rule = (SelectorRule)parents.peek();
			rule.setSelector(pseudoClass);
			
		}
		else if (parent instanceof Expression) { 
			Expression rule = (Expression)parents.peek();
			((Expression)parent).getChildren().add(pseudoClass);
		}
		else if (parent instanceof ParamList) {
			ParamList rule = (ParamList)parents.peek();
			((ParamList)parent).getChildren().add(pseudoClass);
		}
		else {
			throw new IllegalStateException("Found parent element " + 
					parent.getClass().getCanonicalName() + " , but expected either " + 
					Expression.class.getCanonicalName() + " or " + SelectorRule.class.getCanonicalName() 
					+ ".");
		}
		parents.push(pseudoClass); 
	}

	@Override 
	public void exitPseudoClass(TreeSSParser.PseudoClassContext ctx) {
		System.out.println("exitPseudoClass: " + parents.size());
		parents.pop();
	}
	
	@Override 
	public void enterPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) {
		System.out.println("enterPseudoFunction: " + parents.size());
		SelectorRule rule = (SelectorRule)parents.peek();
		PseudoFunction pseudoFunction = new PseudoFunction(rule, Selector.SelectorType.PSEUDOFUNCTION);
		rule.setSelector(pseudoFunction);
		parents.push(pseudoFunction); 
	}

	@Override 
	public void exitPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) { 
		System.out.println("exitPseudoFunction: " + parents.size());
		parents.pop();
	}
	
	@Override 
	public void enterPropertyRule(TreeSSParser.PropertyRuleContext ctx) { 
		System.out.println("enterPropertyRule: " + parents.size());
		SelectorRule rule = (SelectorRule)parents.peek();
		PropertyRule propertyRule = new PropertyRule(rule); /*Needs a correction*/
		rule.getPropertyRules().add(propertyRule);
		parents.push(propertyRule);
	}

	@Override 
	public void exitPropertyRule(TreeSSParser.PropertyRuleContext ctx) {
		System.out.println("exitPropertyRule: " + parents.size());
		parents.pop();
	}
	
	@Override 
	public void enterProperty(TreeSSParser.PropertyContext ctx) {
		System.out.println("enterProperty: " + parents.size());
		PropertyRule rule = (PropertyRule)parents.peek();
		Property property = new Property(rule);
		rule.setProperty(property);
		parents.push(property);
	}

	@Override 
	public void exitProperty(TreeSSParser.PropertyContext ctx) {
		System.out.println("exitProperty: " + parents.size());
		parents.pop();
	}
	
	@Override 
	public void enterParamList(TreeSSParser.ParamListContext ctx) {
		System.out.println("enterParamList: " + parents.size());
		Function rule = (Function)parents.peek();
		ParamList paramList = new ParamList(rule);
		rule.setParamList(paramList);
		parents.push(paramList); 
	}

	@Override 
	public void exitParamList(TreeSSParser.ParamListContext ctx) {
		System.out.println("exitParamList: " + parents.size());
		parents.pop();
	}

	@Override 
	public void enterExpression(TreeSSParser.ExpressionContext ctx) { 
		System.out.println("enterExpression: " + parents.size());
		if (ctx.STAR() != null) {
			System.out.println("  STAR: " + parents.size());
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
		else if (ctx.DIVIDE() != null) {
			System.out.println("  DIVIDE: " + parents.size());
			DocumentElement parent = parents.peek();
			Division division = new Division(parent);
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(division);
			}
			else if (parent instanceof ParamList) {
				((ParamList)parent).getExpressions().add(division);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(division);
		}	
		else if (ctx.PLUS() != null) {
			System.out.println("  PLUS: " + parents.size());
			DocumentElement parent = parents.peek();
			Addition addition = new Addition(parent);
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(addition);
			}
			else if (parent instanceof ParamList) {
				((ParamList)parent).getExpressions().add(addition);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(addition);
		}
		else if (ctx.MINUS() != null) {
			System.out.println("  MINUS: " + parents.size());
			DocumentElement parent = parents.peek();
			Subtraction subtraction = new Subtraction(parent);
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(subtraction);
			}
			else if (parent instanceof ParamList) {
				((ParamList)parent).getExpressions().add(subtraction);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(subtraction);
		}
		else if ((ctx.LPARAN() != null) && (ctx.RPARAN() != null)) {
			System.out.println("  PARAN: " + parents.size());
			DocumentElement parent = parents.peek();
			ParanExpression paranExpression = new ParanExpression(parent);
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(paranExpression);
			}
			else if (parent instanceof ParamList) {
				((ParamList)parent).getExpressions().add(paranExpression);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(paranExpression);
		}
	}

	
	@Override 
	public void exitExpression(TreeSSParser.ExpressionContext ctx) { 
		System.out.println("exitExpression: " + parents.size());
	  if ((ctx.function() != null) | (ctx.unitValue() != null) |(ctx.pseudoClass() != null) 
	  				| (ctx.pseudoFunction() != null) | (ctx.STRING() != null) | (ctx.HEXVALUE() != null)
	  				| (ctx.IDENTIFIER() != null)) {
			return;
		}
	  else {
	  	parents.pop();
	  }
		
	}

	
	@Override 
	public void enterValueFunction(TreeSSParser.ValueFunctionContext ctx) {
		System.out.println("Before If ctx.function (enterValueFunction)" + parents.size());
		if (ctx.function() != null) {
			System.out.println("If ctx.function (enterValueFunction)" + parents.size());
			DocumentElement parent = parents.peek();
			Function valueFunction = new Function(parent);
			if (parent instanceof Function) {
				((Function)parent).getChildren().add(valueFunction);
			}
			else if (parent instanceof Value) {
				((Value)parent).getChildren().add(valueFunction);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(valueFunction);
		}
	}
	

	@Override 
	public void exitValueFunction(TreeSSParser.ValueFunctionContext ctx) {
		System.out.println("exitValueFunction: " + parents.size());
		parents.pop();
	}

	
	@Override 
	public void enterFunction(TreeSSParser.FunctionContext ctx) {
		System.out.println("enterFunction: " + parents.size());
		DocumentElement parent = parents.peek();
		Function function = new Function(parent);
		if (parent instanceof Function) {				
			((Function)parent).getChildren().add(function);
		}
		else if (parent instanceof Value) { /*ValueFunction is actually no class, just a method in Value. */
			((Value)parent).getChildren().add(function);
		}
		else if (parent instanceof Expression) { 
			((Expression)parent).getChildren().add(function);
		}
		else if (parent instanceof PseudoFunction) { 
			((PseudoFunction)parent).getChildren().add(function);
		}
		else {
			throw new IllegalStateException("Found parent element " + 
					parent.getClass().getCanonicalName() + " , but expected either " + 
					Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
					+ ".");
		}
		parents.push(function);
	}

	
	@Override 
	public void exitFunction(TreeSSParser.FunctionContext ctx) { 
		System.out.println("exitFunction: " + parents.size());
		if (!parents.isEmpty()) {
			parents.pop();
		}
	}

	
	@Override 
	public void enterValue(TreeSSParser.ValueContext ctx) {
		System.out.println("enterValue: " + parents.size());
		if (ctx.unitValue() != null) {
			DocumentElement parent = parents.peek();
			Value unitValue = new Value(parent);
			if (parent instanceof Value) {
				((Value)parent).getChildren().add(unitValue);
				Value.unitValue(ctx.IDENTIFIER().getText()); 
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(unitValue);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(unitValue);
		}
		else if (ctx.valueFunction() != null) {
			DocumentElement parent = parents.peek();
			Value valueFunction = new Value(parent);
			if (parent instanceof Value) {
				((Value)parent).getChildren().add(valueFunction);
				Value.valueFunction(ctx.IDENTIFIER().getText()); 
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(valueFunction);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(valueFunction);
		}
		else if (ctx.STRING() != null) {
			DocumentElement parent = parents.peek();
			Value stringValue = new Value(parent);
			if (parent instanceof Value) {
				((Value)parent).getChildren().add(stringValue);
				Value.stringValue(ctx.IDENTIFIER().getText());
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(stringValue);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(stringValue);
		}
		else if (ctx.IDENTIFIER() != null) { /*Is this the correct token or ANTRL IDENTIFIER()?*/
			DocumentElement parent = parents.peek();
			Value identifier = new Value(parent);
			if (parent instanceof Value) {
				((Value)parent).getChildren().add(identifier);
				Value.identifier(ctx.IDENTIFIER().getText());
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(identifier);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(identifier);
		}
		else if (ctx.DECVALUE()!= null) {
			DocumentElement parent = parents.peek();
			Value decValue = new Value(parent);
			if (parent instanceof Value) {
				((Value)parent).getChildren().add(decValue);
				Value.decValue(ctx.IDENTIFIER().getText()); 
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(decValue);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(decValue);
		}
		else if (ctx.HEXVALUE() != null) {
			DocumentElement parent = parents.peek();
			Value hexValue = new Value(parent);
			if (parent instanceof Value) {
				((Value)parent).getChildren().add(hexValue);
				Value.hexValue(ctx.IDENTIFIER().getText());
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(hexValue);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + ParamList.class.getCanonicalName() 
						+ ".");
			}
			parents.push(hexValue);
		}
	}

	@Override 
	public void exitValue(TreeSSParser.ValueContext ctx) { 
		System.out.println("exitValue: " + parents.size());
		parents.pop();
	}

	
	@Override 
	public void enterRules(TreeSSParser.RulesContext ctx) { }
	
	@Override 
	public void exitRules(TreeSSParser.RulesContext ctx) { }

}
