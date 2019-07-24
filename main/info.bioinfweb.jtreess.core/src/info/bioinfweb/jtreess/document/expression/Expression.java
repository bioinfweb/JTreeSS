package info.bioinfweb.jtreess.document.expression;


import java.util.ArrayList;
import java.util.List;

import info.bioinfweb.jtreess.document.AbstractDocumentElement;
import info.bioinfweb.jtreess.document.DocumentElement;



public abstract class Expression extends AbstractDocumentElement{
	public static enum ExpressionType {
		PLUS, 
		MINUS, 
		DIVIDE, 
		MULTIPLY, 
		PARAN;
	}
	
	
	private List<DocumentElement> children = new ArrayList<DocumentElement>(); 
	
	
	public Expression(DocumentElement parent) {
		super(parent);
	}
	
	
	public abstract ExpressionType getType();


	public List<DocumentElement> getChildren() {
		return children;
	}
}
