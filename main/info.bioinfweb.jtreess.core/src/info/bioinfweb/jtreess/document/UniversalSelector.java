package info.bioinfweb.jtreess.document;



public class UniversalSelector extends Selector{
	private Selector.SelectorType type; 
	
	
	public UniversalSelector(DocumentElement parent, SelectorType type) {
		super(parent, type);
	}

}
