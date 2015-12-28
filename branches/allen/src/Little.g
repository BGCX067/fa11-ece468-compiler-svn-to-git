grammar Little;
options{
	backtrack=true;
	memoize=true;
	k=2;
}

@rulecatch { }

/*Program*/
program 
	:	'PROGRAM' id 'BEGIN' pgm_body 'END'
    ;

id
	:	IDENTIFIER
	;

pgm_body
	:	decl func_declarations
	;
	
decl
	:	(var_decl_list | string_decl_list)*
	;
	
/* Global String Declaration */
string_decl_list
	:	(string_decl)+
	;
string_decl
	:	'STRING' id ':=' STRINGLITERAL ';'
	;
/* Variable Declaration */

var_decl_list
	:	(var_decl)+
	;
	
var_decl
	:	var_type id_list ';'
	;
	
var_type
	:	'FLOAT' | 'INT'
	;

any_type
	:	
		var_type | 'VOID'
	;	
	
id_list
	:	id (',' id)*
	;
	

/* Function Parameter List */
param_decl_list
	:	var_type id (',' var_type id)*
	;
	
/* Function Declarations */
func_declarations
	:	(func_decl)*
	;
func_decl
	:	'FUNCTION' any_type id '(' (param_decl_list)? ')' 'BEGIN' func_body 'END' 
	;
func_body
	:	decl stmt_list
	;

/* Statement List */
stmt_list
	:	(stmt)*
	;
stmt
	:	read_stmt | write_stmt | return_stmt | assign_stmt | if_stmt | do_stmt
	;
	
/* Basic Statements */
assign_stmt
	:	id ':=' expr ';'
	;
read_stmt
	:	'READ' '(' id_list ')' ';'
	;
write_stmt
	:	'WRITE' '(' id_list ')' ';'
	;
return_stmt
	:	'RETURN' expr ';'
	;

/* Expressions */
expr
	:	factor (addop factor)*
	;
factor
	:	postfix_expr (mulop postfix_expr)*
	;
postfix_expr
	:	primary | call_expr
	;
call_expr
	:	id '(' (expr_list)? ')'
	;
expr_list
	:	expr (',' expr)*
	;
primary
	:	('(' expr ')') | id | INTLITERAL | FLOATLITERAL
	;
addop
	:	'+' | '-'
	;
mulop
	: '*' | '/'
	;
	
/* Complex Statements and Condition */
if_stmt
	:	'IF' '(' cond ')' 'THEN' stmt_list ('ELSE' stmt_list)? 'ENDIF'
	;
cond
	:	expr ('<' | '>' | '=' | '!=') expr
	;
do_stmt
	:	'DO' stmt_list 'WHILE' '(' cond ')' ';'
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
	:	'--' ( ~('--'|'"'|'\n') )* {$channel = HIDDEN;}
	;
	
WS  : (' '|'\r'|'\n'|'\t')+ {$channel = HIDDEN;} 
	;