package info.bioinfweb.jtreess.document;

public class IdSelector extends Selector {
	private Identifier identifier; 
	private Selector.SelectorType type;
	
	
	public IdSelector(DocumentElement parent, SelectorType type) {
		super(parent, type);
	} 

}
