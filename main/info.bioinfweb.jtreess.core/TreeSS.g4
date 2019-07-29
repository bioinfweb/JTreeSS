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
grammar TreeSS;

@header {
    package info.bioinfweb.jtreess.parser;
}
/*
@lexer::header {
	package info.bioinfweb.jtreess.antlr;
} */

	WS : [ \t\r\n]+ -> skip ;
	COMMENT : [/] [*] .*? [*][/] -> skip;
//	E : 'e';
	POW : '^';
	LBRACE : '{';
	RBRACE : '}';
	DASHMATCH : '|=';
	INCLUDES : '~=';
	EQUAL : '=';
	COMMA : ','; 
	SEMICOLON : ';';
	PRECEDES : '>';
	LBRACKET : '[';
	RBRACKET : ']';
	DOT : '.';
	LPARAN : '(';
	RPARAN : ')';
	COLON : ':';
	HASH : '#';
	AT : '@';
	PLUS : '+';
	MINUS : '-';
	STAR : '*';
	DIVIDE : '/';
	fragment INT: [0-9]+;
	fragment DOUBLE: [0-9]* DOT [0-9]+;
	fragment E : 'e'|'E';
	
	
//	NCNAME : [:a-zA-Z_]+ [:a-zA-Z_\-.0-9];
	IDENTIFIER : [a-zA-Z\-] [a-zA-Z\-0-9]*;
	STRING : '"' .*? '"';	 
	DECVALUE : MINUS? ((INT E MINUS? INT)|(DOUBLE E MINUS? INT)|INT|DOUBLE);
	HEXDIGIT : [a-fA-F0-9];
	HEXVALUE : HASH (HEXDIGIT HEXDIGIT HEXDIGIT| HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT);

	
	unitValue : DECVALUE IDENTIFIER?;
	
	expression : 
	(LPARAN expression RPARAN)
	|expression (DIVIDE | STAR) expression
	|expression (PLUS | MINUS) expression
	|function
	|unitValue
	|pseudoClass
	|pseudoFunction
	|STRING
	|HEXVALUE
	|IDENTIFIER
	;
	
	paramList : expression (COMMA expression)*;
	
	function : IDENTIFIER LPARAN (paramList)? RPARAN;
	
	valueFunction : function;
	
	value : 
	unitValue
	|valueFunction
	|STRING
	|IDENTIFIER
	|DECVALUE
	|HEXVALUE;  
	
	values : value (COMMA value)*;
	
	property : IDENTIFIER; 
	
	propertyRule : property COLON values SEMICOLON; 
	
	pseudoFunction : COLON function; 
	
	pseudoClass : COLON IDENTIFIER; 
	
	pseudoSelector : 
	pseudoClass
	|pseudoFunction; 
	
	universalSelector : STAR;
	
	simpleSelector : IDENTIFIER /*{System.out.println("Test: "+$IDENTIFIER.text+";");}*/;
	
	idSelector : HASH IDENTIFIER; 
	
	basicSelector : 
	simpleSelector
	|idSelector
	|universalSelector;
	
	selector : 
	basicSelector(pseudoSelector)?
	|pseudoSelector
	 ; 
	
	selectorRule : selector LBRACE propertyRule* RBRACE; /*propertyRule does not exist yet*/
	
	rules : AT function; /* rule seems to have a conflict with target language */
	
	document: (selectorRule|rules)*; 
