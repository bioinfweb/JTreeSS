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
package info.bioinfweb.jtreess.reader;


import java.util.Stack;

import info.bioinfweb.jtreess.document.Document;
import info.bioinfweb.jtreess.document.DocumentElement;
import info.bioinfweb.jtreess.document.Function;
import info.bioinfweb.jtreess.document.PropertyRule;
import info.bioinfweb.jtreess.document.SelectorRule;
import info.bioinfweb.jtreess.document.expression.Addition;
import info.bioinfweb.jtreess.document.expression.Division;
import info.bioinfweb.jtreess.document.expression.Expression;
import info.bioinfweb.jtreess.document.expression.Multiplication;
import info.bioinfweb.jtreess.document.expression.ParanExpression;
import info.bioinfweb.jtreess.document.expression.Subtraction;
import info.bioinfweb.jtreess.document.selector.ConcreteSelector;
import info.bioinfweb.jtreess.document.selector.PseudoFunction;
import info.bioinfweb.jtreess.document.selector.SelectorType;
import info.bioinfweb.jtreess.document.value.ColorValue;
import info.bioinfweb.jtreess.document.value.UnitValue;
import info.bioinfweb.jtreess.document.value.Value;
import info.bioinfweb.jtreess.reader.parser.TreeSSBaseListener;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser.DocumentContext;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser.SelectorRuleContext;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser.SimpleSelectorContext;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser.UnitValueContext;



public class SyntaxTreeListener extends TreeSSBaseListener {
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


	@Override 
	public void enterSelectorRule(TreeSSParser.SelectorRuleContext ctx) {
		SelectorRule rule = new SelectorRule(parents.peek());
		getDocument().getSelectorRules().add(rule);
		parents.push(rule);
	}
	

	@Override
	public void exitSelectorRule(SelectorRuleContext ctx) {
		parents.pop();
	}


	@Override
	public void enterSimpleSelector(TreeSSParser.SimpleSelectorContext ctx) {
		SelectorRule rule = (SelectorRule)parents.peek();
		ConcreteSelector simpleSelector = new ConcreteSelector(rule, SelectorType.SIMPLE_SELECTOR, ctx.IDENTIFIER().getText());
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
		ConcreteSelector universalSelector = new ConcreteSelector(rule, SelectorType.UNIVERSAL_SELECTOR, ctx.STAR().getText());   //TODO Possibly replaced by constant.
		rule.setSelector(universalSelector);
		parents.push(universalSelector); 
	}
	
	
	@Override
	public void exitUniversalSelector(TreeSSParser.UniversalSelectorContext ctx) {
		parents.pop();
	}
	
	
	@Override public void enterIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		SelectorRule rule = (SelectorRule)parents.peek();
		ConcreteSelector idSelector = new ConcreteSelector(rule, SelectorType.ID_SELECTOR, ctx.IDENTIFIER().getText());
		rule.setSelector(idSelector);
		parents.push(idSelector); 
	}
	
	
	@Override 
	public void exitIdSelector(TreeSSParser.IdSelectorContext ctx) { 
		parents.pop();
	}


	@Override 
	public void enterPseudoClass(TreeSSParser.PseudoClassContext ctx) { 
		DocumentElement parent = parents.peek();
		ConcreteSelector pseudoClass = new ConcreteSelector(parent, SelectorType.PSEUDOCLASS, ctx.IDENTIFIER().getText());
		if (parent instanceof SelectorRule) {				
			SelectorRule rule = (SelectorRule)parents.peek();
			rule.setSelector(pseudoClass);			
		}
		else if (parent instanceof Expression) { 
			((Expression)parent).getChildren().add(pseudoClass);
		}
		else if (parent instanceof Function) {
			((Function)parent).getParameters().add(pseudoClass);
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
		parents.pop();
	}
	
	
	@Override 
	public void enterPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) {
		SelectorRule rule = (SelectorRule)parents.peek();
		PseudoFunction pseudoFunction = new PseudoFunction(rule, SelectorType.PSEUDOFUNCTION, ctx.function().getText());
		rule.setSelector(pseudoFunction);
		parents.push(pseudoFunction); 
	}

	
	@Override 
	public void exitPseudoFunction(TreeSSParser.PseudoFunctionContext ctx) { 
		parents.pop();
	}
	
	
	@Override 
	public void enterPropertyRule(TreeSSParser.PropertyRuleContext ctx) { 
		SelectorRule rule = (SelectorRule)parents.peek();
		PropertyRule propertyRule = new PropertyRule(rule); /*Needs a correction*/
		rule.getPropertyRules().add(propertyRule);
		parents.push(propertyRule);
	}
	

	@Override 
	public void exitPropertyRule(TreeSSParser.PropertyRuleContext ctx) {
		parents.pop();
	}
	
	
	@Override 
	public void enterProperty(TreeSSParser.PropertyContext ctx) {
		PropertyRule rule = (PropertyRule)parents.peek();
		rule.setPropertyName(ctx.IDENTIFIER().getText());
		parents.push(rule);
	}

	
	@Override 
	public void exitProperty(TreeSSParser.PropertyContext ctx) {
		parents.pop();
	}
	

	@Override 
	public void enterExpression(TreeSSParser.ExpressionContext ctx) { 
		DocumentElement parent = parents.peek();
		DocumentElement operation = null; 
		if (ctx.STAR() != null) {
			operation = new Multiplication(parent);
		}
		else if (ctx.DIVIDE() != null) {
			operation = new Division(parent);
		}	
		else if (ctx.PLUS() != null) {
			operation = new Addition(parent);
		}
		else if (ctx.MINUS() != null) {
			operation = new Subtraction(parent);
		}
		else if ((ctx.LPARAN() != null) && (ctx.RPARAN() != null)) {
			operation = new ParanExpression(parent);
		}
		
		if (operation != null) {	
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(operation);
			}
			else if (parent instanceof Function) {
				((Function)parent).getParameters().add(operation);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + Function.class.getCanonicalName() 
						+ ".");
			}
			parents.push(operation);
		}
	}

	
	@Override 
	public void exitExpression(TreeSSParser.ExpressionContext ctx) { 
	  if ((ctx.value() == null) && (ctx.pseudoClass() == null) && (ctx.pseudoFunction() == null)) {
	  	parents.pop();
		}
	}

		
	@Override 
	public void enterFunction(TreeSSParser.FunctionContext ctx) {
		DocumentElement parent = parents.peek();
		if (parent instanceof Function) {				
			((Function)parent).setName(ctx.IDENTIFIER().getText());
		}
		else {
			throw new IllegalStateException("Found parent element " + 
					parent.getClass().getCanonicalName() + " , but expected " + 
					Function.class.getCanonicalName() + ".");
		}
	}

	
	@Override 
	public void exitFunction(TreeSSParser.FunctionContext ctx) { 
		if (!parents.isEmpty()) {
			parents.pop();
		}
	}

	
	@Override 
	public void enterValue(TreeSSParser.ValueContext ctx) {
		DocumentElement parent = parents.peek();
		DocumentElement value = null;
		if (ctx.COLOR() != null) {
			value = new ColorValue(parent, ctx.IDENTIFIER().getText());
		}
		else if (ctx.STRING() != null) {
			value = new Value(parent, Value.ValueType.STRING, ctx.IDENTIFIER().getText());
		}
		else if (ctx.IDENTIFIER() != null) { /*Is this the correct token or ANTRL IDENTIFIER()?*/
			value = new Value(parent, Value.ValueType.IDENTIFIER, ctx.IDENTIFIER().getText());
		}
		
		if (value != null) {
			if (parent instanceof Expression) {
				((Expression)parent).getChildren().add(value);
			}
			else if (parent instanceof PropertyRule) {
				((PropertyRule)parent).getValues().add(value);
			}
			else {
				throw new IllegalStateException("Found parent element " + 
						parent.getClass().getCanonicalName() + " , but expected either " + 
						Expression.class.getCanonicalName() + " or " + PropertyRule.class.getCanonicalName() 
						+ ".");
			}
			parents.push(value);
		}
	}

	
	@Override 
	public void exitValue(TreeSSParser.ValueContext ctx) { 
		if ((ctx.COLOR() != null) || (ctx.STRING() != null) || (ctx.IDENTIFIER() != null)) {
			parents.pop();
		}
	}


	@Override
	public void enterUnitValue(UnitValueContext ctx) {
		String unit = null;
		if (ctx.IDENTIFIER() != null) {
			unit = ctx.IDENTIFIER().getText();
		}
		DocumentElement value = new UnitValue(parents.peek(), ctx.getText(), ctx.DECVALUE().getText(), unit);
		parents.push(value);
	}


	@Override
	public void exitUnitValue(UnitValueContext ctx) {
		parents.pop();
	}
}