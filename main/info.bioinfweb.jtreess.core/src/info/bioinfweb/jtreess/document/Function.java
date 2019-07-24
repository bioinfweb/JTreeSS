package info.bioinfweb.jtreess.document;

import java.util.ArrayList;
import java.util.List;

public class Function extends AbstractDocumentElement{
	private Identifier identifier; 
	private ParamList paramList; 
	
	
	public Function(DocumentElement parent) {
		super(parent);
	}
}
