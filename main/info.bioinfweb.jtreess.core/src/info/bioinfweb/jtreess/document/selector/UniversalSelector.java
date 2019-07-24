package info.bioinfweb.jtreess.document.selector;

import info.bioinfweb.jtreess.document.DocumentElement;

public class UniversalSelector extends Selector{
	private Selector.SelectorType type; 
	
	
	public UniversalSelector(DocumentElement parent, SelectorType type) {
		super(parent, type);
	}

}
