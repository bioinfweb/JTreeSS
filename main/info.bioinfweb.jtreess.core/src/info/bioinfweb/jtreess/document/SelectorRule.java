package info.bioinfweb.jtreess.document;


import java.util.ArrayList;
import java.util.List;

import info.bioinfweb.jtreess.document.selector.Selector;



public class SelectorRule extends  AbstractDocumentElement{
	private Selector selector;	
	private List <PropertyRule> propertyRules = new ArrayList<>();
	
	
	public SelectorRule(DocumentElement parent) {
		super(parent);
	}

	
	public Selector getSelector() {
		return selector;
	}
	
	
	public void setSelector(Selector selector) {
		this.selector = selector;
	}
	
	
	public List<PropertyRule> getPropertyRules() {
		return propertyRules;
	}	
}
