/**
 * Define a grammar called Hello
 */
grammar TreeSS;

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
	
	operation : 
	PLUS
	|MINUS
	|DIVIDE
	|STAR;
	
	expression : 
	(LPARAN expression RPARAN)
	|expression (DIVIDE | STAR) expression
	|expression (PLUS | MINUS) expression
	|function
	|unitValue
	|pseudoClass
	|pseudoFunction
	|STRING
	|DECVALUE
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
	
	propertyRule : property COLON values SEMICOLON; /* IS THE ";" KORREKT?*/
	
	pseudoFunction : ':' function; 
	
	pseudoClass : ':' IDENTIFIER; 
	
	pseudoSelector : 
	pseudoClass
	|pseudoFunction; 
	
	simpleSelector : IDENTIFIER {System.out.println("Test: "+$IDENTIFIER.text+";");}; 
	
	idSelector : HASH IDENTIFIER; 
	
	
	basicSelector : 
	simpleSelector
	|idSelector
	|STAR;
	
	selector : 
	basicSelector(pseudoSelector)?
	|pseudoSelector
	 ; 
	
	selectorRule : selector LBRACE propertyRule* RBRACE; /*propertyRule does not exist yet*/
	
	rules : AT function; /* rule seems to have a conflict with target language */
	
	document: (selectorRule|rules)*; 
