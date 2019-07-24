package info.bioinfweb.jtreess.document;

import java.util.ArrayList;
import java.util.List;

public class PropertyRule extends AbstractDocumentElement{
		private Property property; 
		private List <Value> values = new ArrayList<>();
		
		public PropertyRule(DocumentElement parent) {
		super(parent);
	}
}
