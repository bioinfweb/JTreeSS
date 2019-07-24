package info.bioinfweb.jtreess.document.selector;

import info.bioinfweb.jtreess.document.DocumentElement;

public class SimpleSelector extends Selector {
	private String name; 

	
	public SimpleSelector(DocumentElement parent, String name) {
		super(parent, Selector.SelectorType.SIMPLE_SELECTOR);
		this.name = name; 
	}


	public String getName() {
		return name;
	}
}


