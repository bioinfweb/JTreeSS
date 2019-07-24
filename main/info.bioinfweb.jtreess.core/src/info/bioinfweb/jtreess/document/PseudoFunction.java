package info.bioinfweb.jtreess.document;



public class PseudoFunction extends Selector {
	private Function function; 
	private Selector.SelectorType type;
	
	
	public PseudoFunction(DocumentElement parent, SelectorType type) {
		super(parent, type);
	}
}
