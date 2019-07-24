package info.bioinfweb.jtreess.document;



public class SimpleSelector extends Selector {
	private Identifier identifier;

	
	public SimpleSelector(DocumentElement parent, SelectorType type) {
		super(parent, type);
	}


	public Identifier getIdentifier() {
		return identifier;
	}

	
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	} 
}
