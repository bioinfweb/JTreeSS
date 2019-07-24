package info.bioinfweb.jtreess.document.expression;


import info.bioinfweb.jtreess.document.DocumentElement;



public class Multiplication extends Expression {
	public Multiplication(DocumentElement parent) {
		super(parent);
	}

	
	@Override
	public ExpressionType getType() {
		return ExpressionType.MULTIPLY;
	}
}
