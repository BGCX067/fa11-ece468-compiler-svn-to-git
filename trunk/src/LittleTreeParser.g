tree grammar LittleTreeParser;
options{
	tokenVocab=Little;
	ASTLabelType = AKNode;
	output=AST;
}

@rulecatch { }

@header { import java.util.HashMap; 
          import java.util.LinkedList;} 

@members { 
    ProgramStorageTable symTable;
    String curr_fname = "Global";
    }

program[ProgramStorageTable symTable]
@init{this.symTable = symTable;
      }
  : 
    ^(r=PROG_HDR /*{System.out.println("Root: " + $r.getText());}*/ i=IDENTIFIER pgm_body)
    
  ;

pgm_body
  : decl func_declarations
  ;
  
decl
  : (var_decl_list | string_decl_list)*
  ;
  
/* Global String Declaration */
string_decl_list
  : (string_decl)
  ;
string_decl
  :  ^(t='STRING' id=IDENTIFIER sv=STRINGLITERAL)
      {symTable.addSymbolByFunctionName(curr_fname, new Symbol($id.getText(), $t.getText(),$sv.getText()));}
  ;
/* Variable Declaration */

var_decl_list
  : (var_decl)//it's not a repeated node on this side
  ;
  
var_decl
  : 
    ^(DECL t=('FLOAT' | 'INT') id=IDENTIFIER)
    {symTable.addSymbolByFunctionName(curr_fname, new Symbol($id.getText(), $t.getText())); }
  ;
/* Function Declarations */

func_declarations
  : (func_decl)*
  ;
func_decl
  :
    ^(FUNC_HDR id=IDENTIFIER {curr_fname = $id.getText();symTable.addSymbolTable(new SymbolTable(curr_fname));} param_decl_list? func_body)
    
    
  ;
//  finally {
//    
//  }
  
/* Function Parameter List */
param_decl_list
  : ^(PARAMS param_decl*)
  ;
param_decl
  : ^(t=('FLOAT' | 'INT') id=IDENTIFIER)
  {symTable.addSymbolByFunctionName(curr_fname, new Symbol($id.getText(), "PARAM"));}//$t.getText()));}
  ;
  
    
func_body
  : decl stmt_list
  ;
call_expr
  :  ^(FUNC_CALL IDENTIFIER expr*)
  ;

/* Statement List */
stmt_list
  : (stmt)*
  ;
stmt
  : assign_stmt | write_stmt | do_stmt | read_stmt | if_stmt | return_stmt
  ;
  
/* Basic Statements */

assign_stmt
  : ^(r=ASSIGN i=IDENTIFIER expr)
 // {System.out.println(r.getText() + " to " + i.getText());}
  ;
  
read_stmt
  : ^(READ IDENTIFIER)
  ; 
write_stmt
  : ^(WRITE id=IDENTIFIER)

  ;
return_stmt
  : ^(RETURN expr)
  ;

/* Expressions */

expr 
  : (^(f=('+' | '-' | '*' | '/') expr expr) 
  | f=IDENTIFIER  
  | f=INTLITERAL  
  | f=FLOATLITERAL)
  | call_expr
  //{System.out.println(f.getText());}
  ;
//expr returns [BigInteger value]
//  : ^('+' a=expr b=expr)  { $value = $a.value.add($b.value); }
//  | ^('-' a=expr b=expr)  { $value = $a.value.subtract($b.value); }
//  | ^('*' a=expr b=expr)  { $value = $a.value.multiply($b.value); }
//  | ^('/' a=expr b=expr)  { $value = $a.value.divide($b.value); }
//  | f=IDENTIFIER  
//  | f=INTLITERAL  
//  | f=FLOATLITERAL
//  
//  ;



/* Complex Statements and Condition */
// 'WRITE' '(' IDENTIFIER (',' IDENTIFIER)* ')' ';'  -> ^(WRITE IDENTIFIER)+
if_stmt
  : ^(IF cond stmt_list else_expr?)
  ;
else_expr
  : ^(ELSE stmt_list)
  ;
cond_expr
  : ^(CONDITION cond)
  ;
cond
  : ^(('<' | '>' | '=' | '!=') expr expr)
  ;

do_stmt
  : ^(DOWHILE cond_expr stmt_list)
  ;
  