package info.bioinfweb.jtreess.document;

import java.util.ArrayList;
import java.util.List;

public class ParamList extends AbstractDocumentElement{
	private List <Expression> expressions = new ArrayList<>();

	
	public ParamList(DocumentElement parent) {
		super(parent);
	}
	
	
	public List<Expression> getExpressions() {
		return expressions;
	}
}
