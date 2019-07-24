package info.bioinfweb.jtreess.document;



public class PseudoClass extends Selector{
	private Identifier identifier; 
	private Selector.SelectorType type;
	
	
	public PseudoClass(DocumentElement parent, SelectorType type) {
		super(parent, type);
	} 	
}
