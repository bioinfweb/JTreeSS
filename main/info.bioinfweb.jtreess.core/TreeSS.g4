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
    package info.bioinfweb.jtreess.reader.parser;
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
	PERCENT : '%';
	fragment INT: [0-9]+;
	fragment DOUBLE: [0-9]* DOT [0-9]+;
	fragment E : 'e'|'E';
	
	
//	NCNAME : [:a-zA-Z_]+ [:a-zA-Z_\-.0-9];
	DECVALUE : ((INT E MINUS? INT)|(DOUBLE E MINUS? INT)|INT|DOUBLE);
	IDENTIFIER : [\-]? [a-zA-Z\_] [a-zA-Z\-\_0-9]*;
	STRING : '"' .*? '"';
	HEXDIGIT : [a-fA-F0-9];
	COLOR : HASH (HEXDIGIT HEXDIGIT HEXDIGIT| HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT);

	unitValue : (MINUS|PLUS)? DECVALUE (IDENTIFIER|PERCENT)?;
	
	expression : 
	(LPARAN expression RPARAN)
	|expression (DIVIDE | STAR) expression
	|expression (PLUS | MINUS) expression
	|value;
	
	paramList : 
	(pseudoSelector|expression) (COMMA (pseudoSelector|expression))*;
	
	function : IDENTIFIER LPARAN (paramList)? RPARAN;
	
	value : 
	unitValue
	|function
	|STRING
	|IDENTIFIER
	|COLOR;
 
 	values : value (COMMA value)*; 
	
	property : IDENTIFIER; 
	
	propertyRule : property COLON values SEMICOLON; 
	
	pseudoFunction : COLON function; 
	
	pseudoClass : COLON IDENTIFIER; 
	
	pseudoSelector : 
	pseudoClass
	|pseudoFunction; 
	
	universalSelector : STAR;
	
	simpleSelector : IDENTIFIER;
	
	idSelector : HASH IDENTIFIER; 
	
	basicSelector : 
	simpleSelector
	|idSelector
	|universalSelector;
	
	selector : 
	basicSelector(pseudoSelector)*
	|pseudoSelector; 
	
	selectorRule : selector (COMMA selector)* LBRACE propertyRule* RBRACE;
	
	atRule : AT IDENTIFIER; /* TODO Extend expression to allow parameters or property list */
	
	document: (selectorRule | atRule)*;
