grammar T;


/*Program*/
program 
	:	'PROGRAM' id 'BEGIN' pgm_body 'END'
    ;

id
	:	IDENTIFIER
	;

pgm_body
	:	decl
	;
	
decl
	:	var_decl_list (decl)?
	;
	
/* Global String Declaration */

/* Variable Declaration */

var_decl_list
	:	var_decl (var_decl_tail)?
	;
	
var_decl
	:	(var_type id_list ';')?
	;
	
var_type
	:	'FLOAT' | 'INT'
	;
	
id_list
	:	id id_tail
	;
	
id_tail
	:	(',' id id_tail)?
	;
	
var_decl_tail
	:	var_decl (var_decl_tail)?

/* Function Parameter List */

/* Function Declarations */

/* Statement List */

/* Basic Statements */

assign_stmt
	:	assign_expr ';'
	;

assign_expr
	:	IDENTIFIER ':=' expr
	;
/* Expressions */

expr
	:	INTLITERAL+
	;

/*Lexer Rules*/
KEYWORD

	:	'PROGRAM'|'BEGIN'|'END'|'PROTO'|'FUNCTION'|'READ'|'WRITE'|
		'IF'|'THEN'|'ELSE'|'ENDIF'|'RETURN'|'CASE'|'ENDCASE'|
		'BREAK'|'DEFAULT'|'DO'|'WHILE'|
		'FLOAT'|'INT'|'VOID'|'STRING'
	;

IDENTIFIER

	:	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*

	;
WS  : (' '|'\r'|'\n')+ {$channel = HIDDEN;} ;
STRINGLITERAL
	:	'"' ( ~('--'|'"') )* '"'
	;
	
FLOATLITERAL
	:	('0'..'9')?'.'('0'..'9')+
	;
	
INTLITERAL
	:	('0'..'9')+
	;
	
COMMENT
	:	'--' ( ~('--'|'"') )* '\n'
	;