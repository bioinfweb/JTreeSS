package info.bioinfweb.jtreess.reader;


import static org.junit.Assert.*;


import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import info.bioinfweb.jtreess.document.Document;
import info.bioinfweb.jtreess.document.Function;
import info.bioinfweb.jtreess.document.PropertyRule;
import info.bioinfweb.jtreess.document.SelectorRule;
import info.bioinfweb.jtreess.document.expression.Expression;
import info.bioinfweb.jtreess.document.expression.Expression.ExpressionType;
import info.bioinfweb.jtreess.document.selector.ConcreteSelector;
import info.bioinfweb.jtreess.document.selector.NonPseudoSelector;
import info.bioinfweb.jtreess.document.selector.PseudoClass;
import info.bioinfweb.jtreess.document.selector.PseudoFunction;
import info.bioinfweb.jtreess.document.selector.Selector;
import info.bioinfweb.jtreess.document.selector.SelectorType;
import info.bioinfweb.jtreess.document.value.ColorValue;
import info.bioinfweb.jtreess.document.value.UnitValue;
import info.bioinfweb.jtreess.document.value.Value;
import info.bioinfweb.jtreess.document.value.Value.ValueType;
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
	
	
	private void assertSelector(NonPseudoSelector selector, SelectorType type, String name) {
		assertNotNull(selector);
		assertEquals(type, selector.getType());
		assertEquals(name, selector.getName());
	}
	
	
	private void assertProperty(PropertyRule property, String name) {
		assertNotNull(property);
		assertEquals(name, property.getPropertyName());
	}
	
	
	@Test
	public void testReadingBasicExample() throws Exception {
		Document document = readDocument("BasicExample");
		
		assertNotNull(document);
		assertEquals(8, document.getSelectorRules().size());
		
		//Selector 1
		
		SelectorRule selectorRule = document.getSelectorRules().get(0);
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.SIMPLE_SELECTOR, "canvas");
		assertEquals(1, selectorRule.getSelectors().size());
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		PropertyRule propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"background-color"); 
		
		assertNotNull(propertyRule.getValues());
		assertEquals(1, propertyRule.getValues().size());
		Value value = (Value) propertyRule.getValues().get(0); 
		assertEquals(propertyRule, value.getParent());
		assertEquals("none", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType());
		
		//Selector 2
		
		selectorRule = document.getSelectorRules().get(1);
		assertEquals(1, selectorRule.getSelectors().size());
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.SIMPLE_SELECTOR, "tree");
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"layout"); 
		
		assertNotNull(propertyRule.getValues());
		assertEquals(1, propertyRule.getValues().size());
		value = (Value) propertyRule.getValues().get(0); 
		assertEquals(propertyRule, value.getParent());
		assertEquals("rectangular", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType());
		
		//Selector 3
		
		selectorRule = document.getSelectorRules().get(2);
		assertEquals(1, selectorRule.getSelectors().size());
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.ID_SELECTOR, "un6gt");
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(3, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"font-family"); 
		
		assertNotNull(propertyRule.getValues());
		assertEquals(2, propertyRule.getValues().size());
		value = (Value) propertyRule.getValues().get(0); 
		assertEquals(propertyRule, value.getParent());
		assertEquals("\"Arial\"", value.getText());
		assertEquals(ValueType.STRING, value.getType());
		value = (Value) propertyRule.getValues().get(1); 
		assertEquals(propertyRule, value.getParent());
		assertEquals("sans-serif", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType());
		
		propertyRule = selectorRule.getPropertyRules().get(1);
		assertEquals(1, propertyRule.getValues().size());
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"font-size"); 
		value = (Value) propertyRule.getValues().get(0); 
		assertEquals(propertyRule, value.getParent());
		assertEquals("8mm", value.getText());
		assertEquals(ValueType.UNIT_VALUE, value.getType());
		
		propertyRule = selectorRule.getPropertyRules().get(2);
		assertEquals(1, propertyRule.getValues().size());
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"font-weight"); 
		value = (Value) propertyRule.getValues().get(0); 
		assertEquals(propertyRule, value.getParent());
		assertEquals("bold", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType());
		
		//Selector 4

		selectorRule = document.getSelectorRules().get(3);
		assertEquals(1, selectorRule.getSelectors().size());
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.SIMPLE_SELECTOR, "node");
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"font-size"); 
		
		assertNotNull(propertyRule.getValues());
		assertEquals(1, propertyRule.getValues().size());
		Function function = (Function) propertyRule.getValues().get(0);
		assertEquals(propertyRule, function.getParent());
		assertEquals( "calc", function.getName());
		assertEquals(1, function.getParameters().size()); 
		assertFalse(function.isPseudofunction());
		
		Expression expression = (Expression) function.getParameters().get(0);
		assertEquals(function, expression.getParent());
		assertEquals(ExpressionType.PLUS, expression.getType()); 
		value = (Value)expression.getChildren().get(0); 
		assertEquals(2, expression.getChildren().size());
		assertEquals(expression, value.getParent());
		assertEquals(ValueType.UNIT_VALUE, value.getType());
		assertEquals("0.7em", value.getText());
		
		Expression subExpression = (Expression) expression.getChildren().get(1);
		assertEquals(expression, subExpression.getParent());
		assertEquals(2, expression.getChildren().size());
		assertEquals(ExpressionType.MULTIPLY, subExpression.getType());
		
		function = (Function)subExpression.getChildren().get(0); 
		assertEquals(subExpression, function.getParent());
		assertFalse(function.isPseudofunction());
		assertEquals("metadataValue", function.getName());
		assertEquals(3, function.getParameters().size()); 
		
		value = (Value)function.getParameters().get(0); 
		assertEquals(function, value.getParent());
		assertEquals("currentNode", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType()); 
		
		value = (Value)function.getParameters().get(1); 
		assertEquals(function, value.getParent());
		assertEquals("\"a:flowerSize\"", value.getText());
		assertEquals(ValueType.STRING, value.getType()); 
		
		value = (Value)function.getParameters().get(2); 
		assertEquals(function, value.getParent());
		assertEquals("\"a:averageSize\"", value.getText());
		assertEquals(ValueType.STRING, value.getType());
		
		
		//Selector 5

		selectorRule = document.getSelectorRules().get(4);
		assertEquals(1, selectorRule.getSelectors().size());
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.UNIVERSAL_SELECTOR, "*");
		assertEquals(selectorRule, selectorRule.getSelectors().get(0).getParent());
		assertEquals(1, selectorRule.getSelectors().get(0).getPseudoSelectors().size());
		assertEquals( "terminal", selectorRule.getSelectors().get(0).getPseudoSelectors().get(0).getName());
		assertEquals(SelectorType.PSEUDO_CLASS, selectorRule.getSelectors().get(0).getPseudoSelectors().get(0).getType());
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"color"); 
		
		value = (Value) propertyRule.getValues().get(0);
		assertEquals(1, propertyRule.getValues().size());
		assertEquals(propertyRule, value.getParent());
		assertEquals("blue", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType());
		
		
		//Selector 6

		selectorRule = document.getSelectorRules().get(5);
		assertEquals(2, selectorRule.getSelectors().size());
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.SIMPLE_SELECTOR, "node");
		assertEquals(selectorRule, selectorRule.getSelectors().get(0).getParent());
		
		assertEquals(2, selectorRule.getSelectors().get(0).getPseudoSelectors().size());
		assertEquals("first-child", selectorRule.getSelectors().get(0).getPseudoSelectors().get(0).getName());
		assertEquals(SelectorType.PSEUDO_CLASS, selectorRule.getSelectors().get(0).getPseudoSelectors().get(0).getType());
		assertEquals("last-child", selectorRule.getSelectors().get(0).getPseudoSelectors().get(1).getName());
		assertEquals(SelectorType.PSEUDO_CLASS, selectorRule.getSelectors().get(0).getPseudoSelectors().get(1).getType());
		
		assertSelector(selectorRule.getSelectors().get(1), SelectorType.SIMPLE_SELECTOR, "branch");
		assertEquals(selectorRule, selectorRule.getSelectors().get(1).getParent());
		
		assertEquals(1, selectorRule.getSelectors().get(1).getPseudoSelectors().size());
		assertEquals("terminal", selectorRule.getSelectors().get(1).getPseudoSelectors().get(0).getName());
		assertEquals(SelectorType.PSEUDO_CLASS, selectorRule.getSelectors().get(1).getPseudoSelectors().get(0).getType());
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"color"); 
		
		ColorValue colorValue = (ColorValue) propertyRule.getValues().get(0); 
		assertEquals(1, propertyRule.getValues().size());
		assertEquals(propertyRule, colorValue.getParent());
		assertEquals("#FF0000", colorValue.getText());
		assertEquals(ValueType.COLOR, colorValue.getType());
		
		
		//Selector 7

		selectorRule = document.getSelectorRules().get(6);
		assertEquals(1, selectorRule.getSelectors().size());
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.SIMPLE_SELECTOR, "node");
		assertEquals(selectorRule, selectorRule.getSelectors().get(0).getParent());
		
		assertEquals(1, selectorRule.getSelectors().get(0).getPseudoSelectors().size());
		assertEquals("not", selectorRule.getSelectors().get(0).getPseudoSelectors().get(0).getName());
		assertEquals(SelectorType.PSEUDO_FUNCTION, selectorRule.getSelectors().get(0).getPseudoSelectors().get(0).getType());
		
		PseudoFunction pseudoFunction = (PseudoFunction) selectorRule.getSelectors().get(0).getPseudoSelectors().get(0);
		assertEquals(1, pseudoFunction.getParameters().size());
		PseudoFunction innerFunction = (PseudoFunction) pseudoFunction.getParameters().get(0);
		assertEquals(2, innerFunction.getParameters().size());
		assertEquals("matches", innerFunction.getName());
		assertEquals(SelectorType.PSEUDO_FUNCTION, innerFunction.getType()); 
		PseudoClass innerFunctionFirstParameter = (PseudoClass)innerFunction.getParameters().get(0);
		PseudoClass innerFunctionSecondParameter = (PseudoClass)innerFunction.getParameters().get(1);
		assertEquals("first-child", innerFunctionFirstParameter.getName()); 
		assertEquals(SelectorType.PSEUDO_CLASS, innerFunctionFirstParameter.getType()); 
		assertEquals("last-child", innerFunctionSecondParameter.getName()); 
		assertEquals(SelectorType.PSEUDO_CLASS, innerFunctionSecondParameter.getType()); 

		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"color"); 
		
		colorValue = (ColorValue) propertyRule.getValues().get(0); 
		assertEquals(1, propertyRule.getValues().size());
		assertEquals(propertyRule, colorValue.getParent());
		assertEquals("#0F0", colorValue.getText());
		assertEquals(ValueType.COLOR, colorValue.getType());	
		
		
		//Selector 8
		
		selectorRule = document.getSelectorRules().get(7);
		assertEquals(document, selectorRule.getParent());
		assertSelector(selectorRule.getSelectors().get(0), SelectorType.SIMPLE_SELECTOR, "scale");
		assertEquals(1, selectorRule.getSelectors().size());
		
		assertNotNull(selectorRule.getPropertyRules());
		assertEquals(1, selectorRule.getPropertyRules().size());
		propertyRule = selectorRule.getPropertyRules().get(0);
		assertEquals(selectorRule, propertyRule.getParent());
		assertProperty(propertyRule,"visible"); 
		
		assertNotNull(propertyRule.getValues());
		value = (Value) propertyRule.getValues().get(0); 
		assertEquals(1, propertyRule.getValues().size());
		assertEquals(propertyRule, value.getParent());
		assertEquals("true", value.getText());
		assertEquals(ValueType.IDENTIFIER, value.getType());
	}
}
