package info.bioinfweb.jtreess.document;


import java.util.List;



public interface DocumentElement {
	public DocumentElement getParent();
	
	public List<DocumentElement> getChildren(); 
}
