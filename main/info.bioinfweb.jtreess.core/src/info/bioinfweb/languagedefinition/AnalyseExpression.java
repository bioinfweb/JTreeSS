/*
 * JTreeSS - A Java library for reading and evaluating TreeSS documents
 * Copyright (C) 2019 Ben Stöver, Charlotte Schmitt
 * <http://bioinfweb.info/JTreeSS>
 * 
 * This file is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This file is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package info.bioinfweb.languagedefinition;

import info.bioinfweb.jtreess.document.expression.Expression;
import info.bioinfweb.jtreess.document.expression.Expression.ExpressionType;
import info.bioinfweb.jtreess.document.value.UnitValue;
import info.bioinfweb.jtreess.document.value.Value;
import info.bioinfweb.jtreess.document.value.Value.ValueType;

public class AnalyseExpression{
	private boolean isUnitValue; 
	private double unitValueResult; 
	private String stringResult; 
	
	
	public void proveExpression(Expression expression) {
		if ((expression.getChildren().get(0) instanceof Value) && (expression.getChildren().get(1) instanceof Value)) {
			Value firstValue= (Value)expression.getChildren().get(0);
			Value secondValue= (Value)expression.getChildren().get(1);
			if (firstValue.getType() == ValueType.UNIT_VALUE) {
				firstValue = (UnitValue)firstValue; 
				secondValue = (UnitValue)secondValue; 
				if (expression.getType() == ExpressionType.MINUS) {
//					result = firstValue.getNumber() - secondValue.getNumber(); 
				}
				else if (expression.getType() == ExpressionType.PLUS) {
//					result = firstValue.getNumber() + secondValue.getNumber(); 
				}
				else if (expression.getType() == ExpressionType.MULTIPLY) {
//					result = firstValue.getNumber() * secondValue.getNumber(); 
				}
				else if (expression.getType() == ExpressionType.DIVIDE) {
//					result = firstValue.getNumber() / secondValue.getNumber(); 
				}
			}
			else if ((firstValue.getType() == ValueType.STRING) || (secondValue.getType() == ValueType.STRING)){
				if (expression.getType() == ExpressionType.MINUS) {
					System.out.println("A String cannot be substracted.");
				}
				else if (expression.getType() == ExpressionType.PLUS) {
					stringResult = firstValue.getText() + secondValue.getText();
							
				}
				else if (expression.getType() == ExpressionType.MULTIPLY) {
					System.out.println("A String cannot be multiplied.");
				}
				else if (expression.getType() == ExpressionType.DIVIDE) {
					System.out.println("A String cannot be divided.");
				} 
			}
		}
	}
}
