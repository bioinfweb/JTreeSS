package info.bioinfweb.jtreess.document;

public class Value extends AbstractDocumentElement{
	private UnitValue unitValue; 
	private ValueFunction valueFunction; 
	private String string; 
	private Identifier identifier; 
//	private DecValue decValue; 
//	private HexValue hexValue; 

	public Value(DocumentElement parent) {
		super(parent);
	}
	public static void testMethod() {
		System.out.println("This is an important test.");
	}
}
