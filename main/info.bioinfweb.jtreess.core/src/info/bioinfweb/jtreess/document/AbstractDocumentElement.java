package info.bioinfweb.jtreess.document;



public abstract class AbstractDocumentElement implements DocumentElement {
	private DocumentElement parent; 
	
	
	public AbstractDocumentElement(DocumentElement parent) {
		super();
		this.parent = parent;
	}
	
	
	@Override
	public DocumentElement getParent() {
		return parent;
	}
}
