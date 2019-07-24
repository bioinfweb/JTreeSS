package info.bioinfweb.jtreess.document;


import java.util.ArrayList;
import java.util.List;



public class SelectorRule {
	private Selector selector;	
	private List <PropertyRule> propertyRules = new ArrayList<>();
	
	
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
