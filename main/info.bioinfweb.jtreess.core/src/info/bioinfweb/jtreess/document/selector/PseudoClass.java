package info.bioinfweb.jtreess.document.selector;

import info.bioinfweb.jtreess.document.DocumentElement;

public class PseudoClass extends Selector {
	private Selector.SelectorType type;
	
	
	public PseudoClass(DocumentElement parent, SelectorType type) {
		super(parent, type);
	} 	
}
