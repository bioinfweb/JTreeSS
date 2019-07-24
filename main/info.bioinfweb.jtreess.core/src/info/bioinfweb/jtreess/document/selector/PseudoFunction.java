package info.bioinfweb.jtreess.document.selector;

import info.bioinfweb.jtreess.document.DocumentElement;
import info.bioinfweb.jtreess.document.Function;

public class PseudoFunction extends Selector {
	private Function function; 
	private Selector.SelectorType type;
	
	
	public PseudoFunction(DocumentElement parent, SelectorType type) {
		super(parent, type);
	}
}
