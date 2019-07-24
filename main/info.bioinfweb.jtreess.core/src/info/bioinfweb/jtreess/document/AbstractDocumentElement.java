package info.bioinfweb.jtreess.document;


import java.util.ArrayList;
import java.util.List;



public abstract class AbstractDocumentElement implements DocumentElement {
	private DocumentElement parent; 
	private List <DocumentElement> children = new ArrayList<>(); 
	
	
	public AbstractDocumentElement(DocumentElement parent) {
		super();
		this.parent = parent;
	}
	
	
	@Override
	public DocumentElement getParent() {
		return parent;
	}

	
	@Override
	public List<DocumentElement> getChildren() {
		return children;
	}
}
