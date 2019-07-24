package info.bioinfweb.jtreess.document.selector;

import info.bioinfweb.jtreess.document.AbstractDocumentElement;
import info.bioinfweb.jtreess.document.DocumentElement;

public abstract class Selector extends AbstractDocumentElement {
	public static enum SelectorType {
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
