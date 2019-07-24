package info.bioinfweb.jtreess.document;

public class Selector {
	public enum SelectorType {
		SIMPLE_SELECTOR, 
		UNIVERSAL_SELECTOR,
		ID_SELECTOR, 
		PSEUDOCLASS, 
		PSEUDOFUNCTION, 
		BASIC_PSEUDO_SELECTOR
	}	
	

	private SelectorType type;


	public Selector(SelectorType type) {
		super();
		this.type = type;
	}


	public SelectorType getType() {
		return type;
	}
}
