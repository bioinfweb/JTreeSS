package info.bioinfweb.jtreess.document;


import java.util.ArrayList;
import java.util.List;



public class Document implements DocumentElement {
	private List <SelectorRule> selectorRules = new ArrayList<>();

	
	@Override
	public DocumentElement getParent() {
		return null;
	}


	public List<SelectorRule> getSelectorRules() {
		return selectorRules;
	} 
}
