package info.bioinfweb.jtreess.document;

import java.util.ArrayList;
import java.util.List;

import info.bioinfweb.jtreess.document.expression.Expression;

public class ParamList extends AbstractDocumentElement{
	private List <Expression> expressions = new ArrayList<>();

	
	public ParamList(DocumentElement parent) {
		super(parent);
	}
	
	
	public List<Expression> getExpressions() {
		return expressions;
	}
}
