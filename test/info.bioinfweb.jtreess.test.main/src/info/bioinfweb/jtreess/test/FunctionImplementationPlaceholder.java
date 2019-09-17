package info.bioinfweb.jtreess.test;


import java.util.List;

import info.bioinfweb.jtreess.execute.ApplicationDataProvider;
import info.bioinfweb.jtreess.execute.RuntimeValue;
import info.bioinfweb.jtreess.execute.implementation.FunctionImplementation;



public class FunctionImplementationPlaceholder implements FunctionImplementation {
	@Override
	public <N> RuntimeValue execute(List<RuntimeValue> parameters, N node, List<Integer> nodeIndices, ApplicationDataProvider<N> dataProvider) {
		return null;
	}
}
