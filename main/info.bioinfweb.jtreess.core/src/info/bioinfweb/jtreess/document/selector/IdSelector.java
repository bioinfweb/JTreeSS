package info.bioinfweb.jtreess.document.selector;

import info.bioinfweb.jtreess.document.DocumentElement;

public class IdSelector extends Selector {
	private Selector.SelectorType type;
	
	
	public IdSelector(DocumentElement parent, SelectorType type) {
		super(parent, type);
	} 

}
