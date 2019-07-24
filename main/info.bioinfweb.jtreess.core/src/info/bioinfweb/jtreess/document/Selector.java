package info.bioinfweb.jtreess.document;



public class Selector extends AbstractDocumentElement {
	public enum SelectorType {
		SIMPLE_SELECTOR, 
		UNIVERSAL_SELECTOR,
		ID_SELECTOR, 
		PSEUDOCLASS, 
		PSEUDOFUNCTION
	}	
	

	private SelectorType type;


	public Selector(DocumentElement parent, SelectorType type) {
		super(parent);
		this.type = type;
	}


	public SelectorType getType() {
		return type;
	}
}
