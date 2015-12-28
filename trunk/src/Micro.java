import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;
import java.io.*;

import java.lang.String;
import org.antlr.stringtemplate.*;
import java.awt.BorderLayout;
import javax.swing.*;
//A real application might have multiple copies of the grammar[49] for multiple passes such as symbol definition, symbol resolution, semantic analysis, and finally code generation. In the next chapter, we'll discover how to emit text source code via StringTemplate templates in order to build code generators.

public class Micro {
	public static final ArrayList<String> IROpcodes = new ArrayList<String>();
	public static final String[] IROpcodesString =  new String[] {
		"RET", "STOREI", "WRITER", "WRITES", "STOREF", "POP", "JSR", "PUSH", "READR", "ADDI", "ADDF", "SUBI", "SUBF", "MULR"
	};
	private static HashMap<String, FunctionTree> funcTrees = new HashMap<String, FunctionTree>();

	public static SymbolTable symTable = new SymbolTable();

	private static ProgramStorageTable allTables = new ProgramStorageTable();

	static List<CommonTree> children;
	static int temperaryNum = 1;
	static LinkedList<ThreeOpCode> ircode = new LinkedList<ThreeOpCode>();
	static LinkedList<String> tinycode = new LinkedList<String>();
	static LinkedList<CFGraph> cfgraphs = new LinkedList<CFGraph>();
	static Integer labelNum = 0;
	static File tinyfile;
	static final boolean writetofile = true;

	/**
	 * @param args
	 */
	public static int td = 0;

	public static void main(String args[]) throws Exception {

		boolean liveOption = false;


		/* File output for testing */
//		String filepath = args[0];
//		String filename = "";
//		int dircnt = 0;
//		int dotloc = 0;
//		for (int i = filepath.length()-1; i >= 0; i--){
//			if (filepath.charAt(i) == '.'){
//				dotloc = i;
//				//System.out.println(filepath.substring(i));
//			}else if (filepath.charAt(i) == '/'){
//				if (dircnt == 0){
//					filename = filepath.substring(i+1, filepath.length());
//					filepath = filepath.substring(0,i+1);
//
//				}else if (dircnt == 1){
//					filepath = filepath.substring(0,i).concat("/output/");
//					for (int k = 0; k < filename.length(); k++){
//						if (filename.charAt(k) == '.'){
//							filepath = filepath.concat(filename.substring(0,k)).concat(".tiny");
//						}
//					}
//					break;
//				}
//				dircnt++;
//
//			}
//
//		}
//		tinyfile = new File(filepath);

		/****/


		if (args.length == 2 && args[1].equals("-live".trim())){
			liveOption = true;
		}

		LittleLexer lex = new LittleLexer(new ANTLRFileStream(args[0]));
		CommonTokenStream tokens = new CommonTokenStream(lex);

		LittleParser p = new LittleParser(tokens);
		p.setTreeAdaptor(new myTreeAdapter());

		//Begin parsing, returns AST of type program_return which is usually CommonTree//
		LittleParser.program_return root = p.program();
		CommonTree tree = (CommonTree)root.getTree(); // get tree from parser 
		//printTree(tree);

		//buildSymTable(tree);
		//symTable.printSymbolTable();

		// walker is the tree parser, equivalent to p above 
		LittleTreeParser walker = new LittleTreeParser(new CommonTreeNodeStream(tree));
		walker.setTreeAdaptor(new myTreeAdapter());
		walker.program(allTables);
		//allTables.print();
		//graphTree(tree);

		generateIR(root.tree);

		printIRCode();
		generateLiveness();
		//printLiveness();

		generateTiny(liveOption);

		printTinyCode();

		Runtime.getRuntime().exec("pwd > test");

	}

	public static void generateLiveness(){
		boolean fLabel = false;
		LinkedList<ThreeOpCode> fCode = new LinkedList<ThreeOpCode>();
		String name = null;
		for (int i=0; i<ircode.size(); i++){
			// beginning of a function, save name for cfgraph and start collecting lines of IR
			if (!fLabel && ircode.get(i).opcode.equals("LABEL")){
				fLabel = true;
				name = ircode.get(i).op1;
			} // end of function, create cfgraph with function name and set fLabel to false
			else if (fLabel && ircode.get(i).opcode.equals("RET")){
				fLabel = false;
				cfgraphs.add(new CFGraph(name, fCode, allTables.getSymbolTable("Global")));
				fCode.clear();
			}
			else if (fLabel){
				fCode.add(ircode.get(i));
			}
		}
	}

	public static void printLiveness(){
		for (int i=0; i<cfgraphs.size(); i++){
			cfgraphs.get(i).printLiveness();
		}
	}

	public static void buildSymTable(CommonTree tree) {
		//For type checking, so the warnings go away//
		ListIterator<?> tmp = tree.getChildren().listIterator();
		ArrayList<CommonTree> elements = new ArrayList<CommonTree>();
		while (tmp.hasNext())
			elements.add((CommonTree) tmp.next()); /* Any type errors found here! */
		ListIterator<CommonTree> iter = elements.listIterator();//= tree.getChildren().listIterator();
		//end type checking//


		while(iter.hasNext()) {
			CommonTree element = iter.next();//this does go here 

			if (element.getChildCount() == 2 && element.getText().equalsIgnoreCase("DECL")){
				symTable.insertSymbol(new Symbol(element.getChild(1).getText(),element.getChild(0).getText()));
				//System.out.println("Left: " + element.getChild(0).getText());
				//System.out.println("Right: " + element.getChild(1).getText());
			}
			else if (element.getChildCount() == 1){
				//System.out.println("Dest: " + element.getChild(0).getText());

			}
			else if(element.getChildCount() == 0){
				continue;
			}
			else{
				buildSymTable(element);
			}
		}

	}

	public static void generateIR(CommonTree tree){
		ListIterator<?> tmp = tree.getChildren().listIterator();
		ArrayList<CommonTree> elements = new ArrayList<CommonTree>();
		while (tmp.hasNext())
			elements.add((CommonTree) tmp.next()); /* Any type errors found here! */

		ListIterator<CommonTree> iter = elements.listIterator();
		//System.out.println(tree.getText());
		while(iter.hasNext()) {
			CommonTree element = iter.next();
			if (element.getType() == LittleParser.FUNC_HDR){ // print "Label function_name"
				String name = element.getChild(0).getText();
				allTables.pushScope(name);
				ircode.add(new ThreeOpCode("LABEL",name));
				ircode.add(new ThreeOpCode("LINK"));
				generateIR(element);

				//Change 2, fix the printing of the RETURN, also added the ircode in the return section
				//below, 
				//TODO: handle return statements with expressions in them
				if (element.getChild(element.getChildCount()-1).getType() != LittleParser.RETURN){
					ircode.add(new ThreeOpCode("RET"));
				}
				allTables.popScope();

				CommonTree temptree = element;
				FunctionTree ftree = new FunctionTree(temptree);
				//System.out.println("FLAG LSDKJFLSDKJFL:SJKDF:");
				//System.out.println(name);
				ftree.setSymbolTables(allTables.getSymbolTable(name),allTables.getSymbolTable("Global"));
				funcTrees.put(name, ftree);

			}
			else if (element.getType() == LittleParser.RETURN){
				if (element.getChild(0).getType() == LittleParser.INTLITERAL){
					ircode.add(new ThreeOpCode("STOREI", element.getChild(0).getText(), "$R"));
				}
				else if (element.getChild(0).getType() == LittleParser.FLOATLITERAL){
					ircode.add(new ThreeOpCode("STOREF", element.getChild(0).getText(), "$R"));
				}
				else if (element.getChild(0).getType() == LittleParser.STRINGLITERAL){
					ircode.add(new ThreeOpCode("STORES", element.getChild(0).getText(), "$R"));
				}
				else if (element.getChild(0).getType() == LittleParser.IDENTIFIER){
					if (allTables.lookupType(element.getChild(0).getText()) == LittleParser.INTLITERAL){
						ircode.add(new ThreeOpCode("STOREI", element.getChild(0).getText(), "$R"));
					}
					else if (allTables.lookupType(element.getChild(0).getText()) == LittleParser.FLOATLITERAL){
						ircode.add(new ThreeOpCode("STOREF", element.getChild(0).getText(), "$R"));
					}
					else if (allTables.lookupType(element.getChild(0).getText()) == LittleParser.STRINGLITERAL){
						ircode.add(new ThreeOpCode("STORES", element.getChild(0).getText(), "$R"));
					}
				}
				ircode.add(new ThreeOpCode("RET"));
			} // probably will not be recognized, function calls most likely to be handled in
			// an assignment statement -> evalExpression -> evalFunction
			// any function evaluated here has no return statement
			else if (element.getType() == LittleParser.FUNC_CALL){
				for (int k=1; k<element.getChildCount(); k++){
					System.out.println("SDFJSDF:LJSDL:FJSDF");
					Temp temp = evalExpression((CommonTree) element.getChild(k));
					//					ircode.add(new ThreeOpCode())
				}
				ircode.add(new ThreeOpCode("PUSH"));
				// push the parameters on to the stack
				for (int i=1; i < element.getChildCount(); i++){
					ircode.add(new ThreeOpCode("PUSH", element.getChild(i).getText()));
				}
				// add JSR command
				ircode.add(new ThreeOpCode("JSR", element.getChild(0).getText()));
				// pop parameters off the stack
				for (int i=1; i < element.getChildCount(); i++){
					ircode.add(new ThreeOpCode("POP"));
				}

			}
			else if (element.getType() == LittleParser.WRITE){
				// Write function, determine if integer or float
				if (element.getChild(0).getType() == LittleParser.INTLITERAL){
					ircode.add(new ThreeOpCode("WRITEI",element.getChild(0).getText()));
				}
				else if (element.getChild(0).getType() == LittleParser.FLOATLITERAL){
					ircode.add(new ThreeOpCode("WRITER",element.getChild(0).getText()));
				}
				else{ //variable
					int t = allTables.lookupType(element.getChild(0).getText());
					//System.out.println("WRITE: " + element.getChild(0).getText() + " " + t);
					if (t==LittleParser.INTLITERAL){
						ircode.add(new ThreeOpCode("WRITEI",element.getChild(0).getText()));
					}
					else if (t == LittleParser.STRINGLITERAL){
						ircode.add(new ThreeOpCode("WRITES", element.getChild(0).getText()));
					}
					else{
						ircode.add(new ThreeOpCode("WRITER",element.getChild(0).getText()));
					}
				}
			}
			else if (element.getType() == LittleParser.READ){
				// Read function, determine if integer or float
				if (allTables.lookupType(element.getChild(0).getText()) == LittleParser.INTLITERAL | allTables.lookupType(element.getChild(0).getText()) == LittleParser.IDENTIFIER){
					ircode.add(new ThreeOpCode("READI",element.getChild(0).getText()));
				}
				else{
					ircode.add(new ThreeOpCode("READR",element.getChild(0).getText()));
				}
			}
			else if (element.getType() == LittleParser.ASSIGN){

				/*Allen's Bad Code*/				
				if (element.getChild(1).getType() != LittleParser.IDENTIFIER){/*If child(1) is not an identifier, eval the expression*/
					
					
					if (allTables.isGlobal(element.getChild(0).getText())){
						if (element.getChild(1).getType() == LittleParser.INTLITERAL){
							ircode.add(new ThreeOpCode("STOREI", element.getChild(1).getText(),element.getChild(0).getText()));
						}else if (element.getChild(1).getType() == LittleParser.FLOATLITERAL){
							ircode.add(new ThreeOpCode("STOREF", element.getChild(1).getText(),element.getChild(0).getText()));
						}
					}
					else{
						Temp temp = evalExpression((CommonTree) element.getChild(1));
						if (temp.getType() == LittleParser.INTLITERAL){
							ircode.add(new ThreeOpCode("STOREI",temp.getName(),element.getChild(0).getText()));
						}
						else if (allTables.lookupType(temp.name) == LittleParser.INTLITERAL){

						}
						else{
							//System.out.println("STOREF " + temp.getName() + " " + element.getChild(0).getText());
							ircode.add(new ThreeOpCode("STOREF",temp.getName(),element.getChild(0).getText()));
						}
					}
				}
				else {/*Else, cheat.*/

					if (symTable.lookupType(element.getChild(1).getText().trim()) == LittleParser.INTLITERAL){
						Temp temp = new Temp(temperaryNum++,LittleParser.INTLITERAL,element.getChild(1).getText());
						ircode.add(new ThreeOpCode("STOREI", element.getChild(1).getText(), temp.getName()));
						ircode.add(new ThreeOpCode("STOREI",temp.getName(),element.getChild(0).getText()));
					}
					else{
						Temp temp = new Temp(temperaryNum++,LittleParser.FLOATLITERAL,element.getChild(1).getText());
						ircode.add(new ThreeOpCode("STOREF", element.getChild(1).getText(), temp.getName()));
						ircode.add(new ThreeOpCode("STOREF",temp.getName(),element.getChild(0).getText()));
					}
					/*
					 * if the right hand side is a variable, it should already have a temporary assigned to it
					 * so look it up, grab the temp number and use that temporary instead
					 */
					//System.out.println(symTable.lookupType(element.getChild(1).getText().trim()));
				}

			}
			else if (element.getType() == LittleParser.DOWHILE){
				// label for the beginning of the loop
				ircode.add(new ThreeOpCode("LABEL","label".concat(labelNum.toString())));
				Integer lblnum = labelNum++;
				// remove condition for test at end
				CommonTree cond = (CommonTree) element.getChild(0).getChild(0);
				element.deleteChild(0);
				generateIR(element);
				Temp left = evalExpression((CommonTree) cond.getChild(0));
				Temp right = evalExpression((CommonTree) cond.getChild(1));
				// test for which condition
				//System.out.println("left: " + left.getName() + " right: " + right.getName());
				if (cond.getText().contains("<")){
					ircode.add(new ThreeOpCode("LE",
							left.getName(),
							right.getName(),
							"label".concat(lblnum.toString())));

				}
				else if (cond.getText().contains(">")){
					ircode.add(new ThreeOpCode("GE",
							left.getName(),
							right.getName(),
							"label".concat(lblnum.toString())));
				}
				else if (cond.getText().contains("!=")){
					ircode.add(new ThreeOpCode("NE",
							left.getName(),
							right.getName(),
							"label".concat(lblnum.toString())));
				}

			}
			else if (element.getType() == LittleParser.IF){
				// remove condition for test at end
				CommonTree cond = (CommonTree) element.getChild(0);
				element.deleteChild(0);
				//generateIR(element);
				Temp left = evalExpression((CommonTree) cond.getChild(0));
				Temp right = evalExpression((CommonTree) cond.getChild(1));
				// test for which condition
				if (cond.getText().contains("<")){
					ircode.add(new ThreeOpCode("GE",
							left.getName(),
							right.getName(),
							"label".concat(labelNum.toString())));

				}
				else if (cond.getText().contains(">")){
					ircode.add(new ThreeOpCode("LE",
							left.getName(),
							right.getName(),
							"label".concat(labelNum.toString())));
				}
				else if (cond.getText().contains("!=")){
					ircode.add(new ThreeOpCode("EQ",
							left.getName(),
							right.getName(),
							"label".concat(labelNum.toString())));
				}
				else if (cond.getText().equals("=")){
					ircode.add(new ThreeOpCode("NE",
							left.getName(),
							right.getName(),
							"label".concat(labelNum.toString())));
				}
				CommonTree elseStmt = null;
				// test for else statement, assign its statements to a temperary variable
				if (element.getChild(element.getChildCount()-1).getText().contains("ELSE")){
					//System.out.println("else found");
					elseStmt = (CommonTree) element.getChild(element.getChildCount()-1);
					element.deleteChild(element.getChildCount()-1);
					//System.out.println("elseStmt: " + element.getChild(element.getChildCount()-1));
					labelNum++;
				}
				Integer lblnum = labelNum++;
				generateIR(element);
				// an else statement was found earlier
				if (elseStmt != null){
					ircode.add(new ThreeOpCode("JUMP","label".concat(lblnum.toString())));
					lblnum--;
					ircode.add(new ThreeOpCode("LABEL", "label".concat(lblnum.toString())));
					lblnum++;
					generateIR(elseStmt);
				}

				ircode.add(new ThreeOpCode("LABEL", "label".concat(lblnum.toString())));


			}
			else if (element.getChildCount() == 0){
				continue;
			}
			else{
				generateIR(element);
			}
		}
	}

	// if function call is called in an assign statement evalExpression of the call
	// will be evaluated by this function
	public static Temp evalFunction(CommonTree tree){
		LinkedList<Temp> temps = new LinkedList<Temp>();
		for (int k=1; k<tree.getChildCount(); k++){
			//System.out.println("SDFJSDF:LJSDL:FJSDF");
			temps.add(evalExpression((CommonTree) tree.getChild(k)));
			//			ircode.add(new ThreeOpCode())
		}
		ircode.add(new ThreeOpCode("PUSH"));
		// push the parameters on to the stack
		for (int i=1; i < tree.getChildCount(); i++){
			ircode.add(new ThreeOpCode("PUSH", temps.get(i-1).name));
		}
		// add JSR command
		ircode.add(new ThreeOpCode("JSR", tree.getChild(0).getText()));
		// pop parameters off the stack
		for (int i=1; i < tree.getChildCount(); i++){
			ircode.add(new ThreeOpCode("POP"));
		}
		// create temperary for return value
		Temp temp = new Temp(temperaryNum++,tree.getType(),tree.getText());
		// pop the return value
		ircode.add(new ThreeOpCode("POP", temp.name));
		return temp;
	}

	// Recursive function to evaluate an expression, returns temporary variable
	public static Temp evalExpression(CommonTree tree){
		// leaf node, int/float/id

		if (tree.getChildCount() == 0){
			Temp temp = new Temp(temperaryNum++,tree.getType(),tree.getText());
			if (tree.getType() == LittleParser.INTLITERAL){
				ircode.add(new ThreeOpCode("STOREI",tree.getText(),temp.getName()));
			}
			else if(tree.getType() == LittleParser.FLOATLITERAL){
				ircode.add(new ThreeOpCode("STOREF",tree.getText(),temp.getName()));
			}
			else{ // look up the variable in the symbol table to set type, use var_name instead of $Tx and recycle $Tx
				temp.setType(allTables.lookupType(temp.getValue().trim()));
				temp.setName(tree.getText());
				temperaryNum--;
			}
			return temp;
		}
		// Not a leaf node, evaluate children expressions
		else{
			// test for function call
			if (tree.getType() == LittleParser.FUNC_CALL){
				// add IR for evaluating the function and return temperary for return value
				// after it has been popped off the stack
				return evalFunction(tree);
			}
			//			if (tree.getChild(0).getChildCount() == 0 && tree.getChild(1).getChildCount() == 0){
			//				if ()
			//			}
			Temp left = evalExpression((CommonTree) tree.getChild(0));
			Temp right = evalExpression((CommonTree) tree.getChild(1));

			Temp temp = new Temp(temperaryNum++,left.getType(), "EXP");
			if (tree.getText().contains("+")){ // add
				if (left.getType() == LittleParser.INTLITERAL && right.getType() == LittleParser.INTLITERAL){
					ircode.add(new ThreeOpCode("ADDI",left.getName(),right.getName(),temp.getName()));
				}
				else if (left.getType() == LittleParser.FLOATLITERAL || right.getType() == LittleParser.FLOATLITERAL){
					ircode.add(new ThreeOpCode("ADDR",left.getName(),right.getName(),temp.getName()));
				} //both parameters
				else if (left.getType() == -1 && right.getType() == -1){
					//System.out.println(left.getName() + ": " + left.getType() + " " + right.getName() + ": " + right.getType());
					// need to lookup symbol in main and then global to find type
					int ltype = allTables.lookupType(left.name);
					int rtype = allTables.lookupType(right.name);
					if (ltype == LittleParser.INTLITERAL && rtype == LittleParser.INTLITERAL){
						ircode.add(new ThreeOpCode("ADDI", left.getName(),right.getName(),temp.getName()));
					}
					else{
						ircode.add(new ThreeOpCode("ADDR", left.getName(),right.getName(),temp.getName()));
					}
				}
				else{
					ircode.add(new ThreeOpCode("ADDI",left.getName(),right.getName(),temp.getName()));
					temp.setType(LittleParser.INTLITERAL);
				}
			}
			else if (tree.getText().contains("-")){ // subtract
				if (left.getType() == LittleParser.INTLITERAL && right.getType() == LittleParser.INTLITERAL){
					ircode.add(new ThreeOpCode("SUBI",left.getName(),right.getName(),temp.getName()));
				}
				else if (left.getType() == LittleParser.FLOATLITERAL || right.getType() == LittleParser.FLOATLITERAL){
					ircode.add(new ThreeOpCode("SUBR",left.getName(),right.getName(),temp.getName()));
				}
				else if (left.getType() == -1 && right.getType() == -1){
					//System.out.println(left.getName() + ": " + left.getType() + " " + right.getName() + ": " + right.getType());
					// need to lookup symbol in main and then global to find type
					int ltype = allTables.lookupType(left.name);
					int rtype = allTables.lookupType(right.name);
					if (ltype == LittleParser.INTLITERAL && rtype == LittleParser.INTLITERAL){
						ircode.add(new ThreeOpCode("SUBI", left.getName(),right.getName(),temp.getName()));
					}
					else{
						ircode.add(new ThreeOpCode("SUBR", left.getName(),right.getName(),temp.getName()));
					}
				}
				else{
					ircode.add(new ThreeOpCode("SUBI",left.getName(),right.getName(),temp.getName()));
					temp.setType(LittleParser.INTLITERAL);
				}
			}
			else if (tree.getText().contains("*")){ // multiply
				if (left.getType() == LittleParser.INTLITERAL && right.getType() == LittleParser.INTLITERAL){
					//System.out.println("MULTI " + left.getName() + " " + right.getName() + " "+temp.getName());
					ircode.add(new ThreeOpCode("MULI",left.getName(),right.getName(),temp.getName()));
				}
				else if (left.getType() == LittleParser.FLOATLITERAL || right.getType() == LittleParser.FLOATLITERAL){
					//System.out.println("MULTF " + left.getName() + " " + right.getName() + " "+temp.getName());
					ircode.add(new ThreeOpCode("MULR",left.getName(),right.getName(),temp.getName()));
				}
				else if (left.getType() == -1 && right.getType() == -1){
					//System.out.println(left.getName() + ": " + left.getType() + " " + right.getName() + ": " + right.getType());
					// need to lookup symbol in main and then global to find type
					int ltype = allTables.lookupType(left.name);
					int rtype = allTables.lookupType(right.name);
					if (ltype == LittleParser.INTLITERAL && rtype == LittleParser.INTLITERAL){
						ircode.add(new ThreeOpCode("MULI", left.getName(),right.getName(),temp.getName()));
					}
					else{
						ircode.add(new ThreeOpCode("MULR", left.getName(),right.getName(),temp.getName()));
					}
				}
				else{
					//System.out.println("MULTI " + left.getName() + " " + right.getName() + " "+temp.getName());
					ircode.add(new ThreeOpCode("MULI",left.getName(),right.getName(),temp.getName()));
					temp.setType(LittleParser.INTLITERAL);
				}
			}
			else{ // divide
				if (right.getType() == LittleParser.INTLITERAL && left.getType() == LittleParser.FLOATLITERAL){
					//System.out.println("DIVI " + left.getName() + " " + right.getName() + " "+temp.getName());
					ircode.add(new ThreeOpCode("DIVI",left.getName(),right.getName(),temp.getName()));
				}
				else if (right.getType() == LittleParser.FLOATLITERAL || left.getType() == LittleParser.FLOATLITERAL){
					//System.out.println("DIVF " + left.getName() + " " + right.getName() + " "+temp.getName());
					ircode.add(new ThreeOpCode("DIVR",left.getName(),right.getName(),temp.getName()));
				}
				else if (left.getType() == -1 && right.getType() == -1){
					//System.out.println(left.getName() + ": " + left.getType() + " " + right.getName() + ": " + right.getType());
					// need to lookup symbol in main and then global to find type
					int ltype = allTables.lookupType(left.name);
					int rtype = allTables.lookupType(right.name);
					if (ltype == LittleParser.INTLITERAL && rtype == LittleParser.INTLITERAL){
						ircode.add(new ThreeOpCode("DIVI", left.getName(),right.getName(),temp.getName()));
					}
					else{
						ircode.add(new ThreeOpCode("DIVR", left.getName(),right.getName(),temp.getName()));
					}
				}
				else{
					//System.out.println("DIVI " + left.getName() + " " + right.getName() + " "+temp.getName());
					ircode.add(new ThreeOpCode("DIVI",left.getName(),right.getName(),temp.getName()));
					temp.setType(LittleParser.INTLITERAL);

				}
			}
			return temp;
		}

	}

	// prints IR code generated
	public static void printIRCode(){
		for (int i=0; i < ircode.size(); i++){
			System.out.println(";"+ircode.get(i).printCode());
		}
	}


	public static void generateTiny(boolean regalloc){
		// initialize Register Handler if Register Allocation is chosen
		RegisterHandler regHand = null;
		if (regalloc){
			regHand = new RegisterHandler(4, allTables);
		}else{
			regHand = new RegisterHandler(0, allTables);
		}
		// int to keep track of liveness line within function
		int liveLine = -1;
		// cfgraph of the function in use
		CFGraph liveness = null;
		// linked list for tiny code generated by the Register Handler
		LinkedList<String> regcode = null;
		String fname = "Global";
		regHand.setFunctionName(fname);
		// reserve variables
		SymbolTable globTable = allTables.getSymbolTable("Global");
		ArrayList<Symbol> symbols = globTable.getSymbols();
		for (int i=0; i<symbols.size();i++){
			if (symbols.get(i).getType().equalsIgnoreCase("string")){
				tinycode.add("str " + symbols.get(i).getName() + " " + symbols.get(i).getString());
			}else{
				tinycode.add("var "+symbols.get(i).getName());
			}
		}

		// push registers on to stack
		tinycode.add("push");
		tinycode.add("push r0");
		tinycode.add("push r1");
		tinycode.add("push r2");
		tinycode.add("push r3");
		tinycode.add("jsr main");
		tinycode.add("sys halt");
		// ArrayList to keep track of the temporaries in use
		ArrayList<String> temps = new ArrayList<String>();

		// Loop through IR code generating tiny code
		ListIterator<ThreeOpCode> iter = ircode.listIterator();

		while (iter.hasNext()){
			ThreeOpCode topcode = iter.next();
			//System.out.println(topcode.opcode + topcode.op1);
			if (topcode.opcode.equals("RET")){ // Function definition (Main only for this step)
				regHand.setMyRegistersFree();
				tinycode.add("unlnk");
				tinycode.add("ret"); //create function label
				// reset the liveness line variable
				liveLine = -1;
			}
			else if (topcode.opcode.equals("POP")){
				if (topcode.op1 != null){
					tinycode.add("pop r" + regHand.ensure(topcode.op1));
				}
				else {
					tinycode.add("pop");
				}
			}
			else if (topcode.opcode.equals("PUSH")){
				if (topcode.op1 != null){
					tinycode.add("push " + allTables.getTinyVar(fname, topcode.op1));
				}else {
					tinycode.add("push");
				}
			} // catch store instructions, map temperatires to registers or copy op
			else if (topcode.opcode.equals("READR") || topcode.opcode.equals("READI")){
				tinycode.add("sys " + topcode.opcode.toLowerCase() + " " + allTables.getTinyVar(fname,topcode.op1));
			} 
			else if (topcode.opcode.contains("JSR")){
				// jsr from main, add function calls generated by FunctionTree(main)
				if (!topcode.op1.contains("main")){
					//System.out.println("jsr: " + topcode.op1+" "+ funcTrees.containsKey(topcode.op1.trim()));
					regHand.setMyRegistersFree();


					ArrayList<String>fcall = funcTrees.get("main").generateTinyFunctionCall(funcTrees.get(topcode.op1), funcTrees.get(fname));


					for (int i=0; i<fcall.size(); i++){
						tinycode.add(fcall.get(i));
					}
					//					if (fcall.get(fcall.size()-1) != null){
					//						int r = regHand.ensure(funcTrees.get(topcode.op1).getReturnValue());
					//						//tinycode.add(fcall.get(fcall.size()-1).concat(Integer.toString(temps.size())));
					//						tinycode.add(fcall.get(fcall.size()-1).concat(Integer.toString(r)));
					//					}
					//temps.add(funcTrees.get(topcode.op1).getReturnValue());
				}

			}// test for conditional branch
			else if (topcode.opcode.equals("STOREI") || topcode.opcode.equals("STOREF")){


				//only one op is memory or stack variable

				if (topcode.op1.contains("$")){
					tinycode.add("move r" + regHand.ensure(topcode.op1) + " " + allTables.getTinyVar(fname, topcode.op2));
				}else if (!topcode.op2.equals("$R")){

					if (allTables.isGlobal(topcode.op2)){
						tinycode.add("move " + topcode.op1 + " " + topcode.op2);
					}else {
						tinycode.add("move " + topcode.op1 + " r" + regHand.ensure(topcode.op2));
					}

				}
				//System.out.println(regHand.ensure(topcode.op1));
				//System.out.println(allTables.getTinyVar(fname, topcode.op2));
				if (topcode.op2.equals("$R")){
					if (!allTables.getTinyVar(fname, topcode.op1).equals("0") ){
						tinycode.add("move " + allTables.getTinyVar(fname, topcode.op1) + " r" + regHand.ensure("$S"));
					}else{
						tinycode.add("move " + topcode.op1 + " r" + regHand.ensure("$S"));
					}
					tinycode.add("move r" + regHand.ensure("$S") + " $" + Integer.toString(6+allTables.getNumberOfParameters(fname)));
				}

				/*String s ="move ";
				if (!topcode.op1.contains("$")){
					LinkedList<String> scode = regHand.ensureStore(topcode.op1);
					if (scode != null){
						// tiny code has been generated to mov op1 into register
						for (int h=0; h<scode.size(); h++){
							tinycode.add(scode.get(h));
						}
					}
				}

				s = s.concat("r"+Integer.toString(regHand.ensure(topcode.op1)) + " ");
				// if second op is a temp (already in a register) set its register to op1s value
				if (topcode.op2.contains("$")){
					regHand.setRegisterVarName(regHand.ensure(topcode.op1), topcode.op2);
					s = s.concat("r"+ regHand.ensure(topcode.op2));
				}
				else{

				}*/
				//				if (topcode.op1.contains("$")){
				////					if (temps.indexOf(topcode.op1) == -1){ // same temperary that is in use
				////						temps.add(topcode.op1);
				////					}
				////					s = s.concat("r"+ temps.indexOf(topcode.op1) +" ");//could mess up if regnum is currently 0
				//					LinkedList<String> scode = regHand.ensureStore(topcode.op1);
				//					if (scode != null){
				//						// tiny code has been generated to mov op1 into register
				//						for (int h=0; h<scode.size(); h++){
				//							tinycode.add(scode.get(h));
				//						}
				//					}
				//					s = s.concat("r"+Integer.toString(regHand.ensure(topcode.op1)) + " ");
				//				} // didn't find it in symbol tables, number
				//				else if (allTables.getTinyVar(fname,topcode.op1) == "0"){
				//					s = s.concat(topcode.op1 + " ");
				//				}
				//				else{ // string or global variable matched 
				//					LinkedList<String> scode = regHand.ensureStore(topcode.op1);
				//					if (scode != null){
				//						// tiny code has been generated to mov op1 into register
				//						for (int h=0; h<scode.size(); h++){
				//							tinycode.add(scode.get(h));
				//						}
				//					}
				//					s = s.concat(allTables.getTinyVar(fname,topcode.op1) + " ");
				//				}
				//				// op2
				//				if (topcode.op2.contains("$")){ 
				//					//System.out.println(topcode.op2 +"regnum: " + Integer.toString(regnum));
				////					if (temps.indexOf(topcode.op2) == -1){ // same temperary that is in use
				////						temps.add(topcode.op2);
				////					}	
				////					s = s.concat("r"+ temps.indexOf(topcode.op2));
				//					LinkedList<String> scode = regHand.ensureStore(topcode.op2);
				//					if (scode != null){
				//						// tiny code has been generated to mov op1 into register
				//						for (int h=0; h<scode.size(); h++){
				//							tinycode.add(scode.get(h));
				//						}
				//					}
				//					s = s.concat("r"+Integer.toString(regHand.ensure(topcode.op2)));
				//				}else{ //variable
				//					LinkedList<String> scode = regHand.ensureStore(topcode.op2);
				//					if (scode != null){
				//						// tiny code has been generated to mov op1 into register
				//						for (int h=0; h<scode.size(); h++){
				//							tinycode.add(scode.get(h));
				//						}
				//					}
				//					s = s.concat("r"+Integer.toString(regHand.ensure(topcode.op2)));
				//					// s = s.concat(allTables.getTinyVar(fname,topcode.op2));
				//				}

			} // Catch Write statements
			else if (topcode.opcode.equals("WRITEI") || topcode.opcode.equals("WRITER")|| topcode.opcode.equals("WRITES")){
				tinycode.add("sys " + topcode.opcode.toLowerCase() + " " + allTables.getTinyVar(fname,topcode.op1));
				//System.out.println(topcode.op1 + ": "+allTables.getTinyVar(fname,topcode.op1));
			}// catch add statements
			else if (topcode.opcode.contains("ADD") || topcode.opcode.contains("MUL") || topcode.opcode.contains("DIV") || topcode.opcode.contains("SUB")){
				// where it was
				boolean temp1 = topcode.op1.contains("$");
				boolean temp2 = topcode.op2.contains("$");

				String s = "";

				//reg
				s = topcode.opcode.toLowerCase() + " r" + Integer.toString(regHand.ensure(topcode.op2)) + " r" + Integer.toString(regHand.ensure(topcode.op1));

				tinycode.add(s);

				tinycode.add("move r" + Integer.toString(regHand.ensure(topcode.op1)) + " r" + Integer.toString(regHand.ensure(topcode.op3)));
			} 
			else if (topcode.opcode.contains("LABEL")){
				tinycode.add("label " + topcode.op1);
			}
			else if (topcode.opcode.contains("LINK")){
				fname = ircode.get(iter.previousIndex()-1).op1;
				regHand.setFunctionName(fname);
				tinycode.add("link " + Integer.toString(funcTrees.get(fname).getLink()));
				//set liveness for the new function
				// search for function in CFGraphs and set liveness to that function's
				for (int g = 0; g < cfgraphs.size(); g++){
					// found it, set liveness to match the function's liveness
					if (cfgraphs.get(g).getFname().equals(fname)){
						liveness = cfgraphs.get(g);
					}
				}
			}// test for conditional branch
			else if (topcode.opcode.contains("NE") || topcode.opcode.contains("EQ") || topcode.opcode.contains("GE") || topcode.opcode.contains("LE")){
				// compare incremented variable (op1) to the register of op2
				tinycode.add("cmpi " + allTables.getTinyVar(fname,topcode.op1) + " r" + regHand.ensure(topcode.op2));
				tinycode.add("j".concat(topcode.opcode.toLowerCase()) + " label".concat(topcode.op3.substring(5)));
			}
			//Allen TODO unconditional jumps weren't accounted for, but they directly translate
			else if (topcode.opcode.contains("JUMP")){
				tinycode.add("jmp " + topcode.op1);
			}
			// if register allocation is enabled update temps to reflect variables in registers
			if (regalloc && (liveLine > -1) && (liveLine < liveness.getSize())){
				// process the next instruction, if register allocation code is needed add to tinycode
				//System.out.println(topcode.opcode + " "+ircode.get(iter.nextIndex()).opcode);

				regcode = regHand.processThreeOpCode(ircode.get(iter.nextIndex()),liveness.getLiveness(liveLine));
				//System.out.println("regcode: " + regcode);
				// add the register code to tinycode list, add the return value to list of vals in registers

				if (regcode != null){
					for (int t=0; t<regcode.size()-1; t++){
						tinycode.add(regcode.get(t));
					}
					// index in temps is equal to the register the value is assigned to, put the return
					// in the proper place in temps
					//temps.set(Integer.parseInt(regcode.get(regcode.size()-1)), ircode.get(iter.nextIndex()).op3);
				}
				//				temps.clear();
				//				temps = regHand.getTemps();z
			}

			liveLine++;

		}

		// add the final return code for the program
		tinycode.add("unlnk");
		tinycode.add("ret");
		tinycode.add("end");
		//tinycode.add("");
	}

	public static void printTinyCode(){
//		if (writetofile){
//			DataOutputStream outStream = null;
//			try {
//				outStream = new DataOutputStream(
//						new FileOutputStream(tinyfile.getAbsoluteFile()));
//			}
//			catch (FileNotFoundException fuckup) {
//				System.err.println("Could not open file in printTiny()");
//				fuckup.printStackTrace();
//			}
//
//			try {
//				for (int i=0; i < ircode.size(); i++){
//					outStream.writeBytes(";"+ircode.get(i).printCode()+"\n");
//				}
//				for (int i=0; i < tinycode.size(); i++){
//					if (tinycode.get(i) != null){
//						outStream.writeBytes(tinycode.get(i)+"\n");
//					}
//				}
//
//				outStream.close();
//			}
//			catch (IOException fuckup) {
//				System.err.println("Writing to the file failed in printTiny printTiny()");
//				fuckup.printStackTrace();
//			}
//		}

		for (int i=0; i < tinycode.size(); i++){
			System.out.println(tinycode.get(i));
		}
	}
	public static void graphTree(CommonTree tree){
		//Tree DOTt = tree;
		DOTTreeGenerator gen = new DOTTreeGenerator(); 
		StringTemplate st = gen.toDOT(tree); 

		String type = "jpg";
		GraphViz gv = new GraphViz();
		gv.addln(st.toString());

		System.out.println("Before");

		ImageIcon img = new ImageIcon(gv.getGraph(gv.getDotSource(), type));

		System.out.println("After");

		int newHeight = 775, newWidth = 1255; 
		/* This is for writing the image to disk*/
		File out = new File("graph." + type);   // Linux
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
		//		if (iw > newWidth){
		//			newHeight = newWidth * ih/iw ;
		//			BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		//			Graphics2D g2 = resizedImg.createGraphics();
		//			
		//			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//			g2.drawImage(img.getImage(), 0, 0, newWidth, newHeight, null);
		//			g2.dispose();
		//			img = new ImageIcon(resizedImg);
		//		}

		JFrame jf = new JFrame("Abstract Syntax Tree");
		jf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		JLabel photographLabel = new JLabel();
		photographLabel.setHorizontalAlignment(JLabel.CENTER);
		//private Image getScaledImage(Image srcImg, int w, int h){


		photographLabel.setIcon(img);
		JScrollPane scrollPane = new JScrollPane(photographLabel);
		jf.add(scrollPane, BorderLayout.CENTER);
		jf.pack();
		jf.setSize(newWidth+50, newHeight+50);
		jf.setResizable(true);
		jf.setVisible(true);
		/*end graphical display*/
	}
	public static void printTree(CommonTree tree) {
		ListIterator<?> tmp = tree.getChildren().listIterator();
		ArrayList<CommonTree> elements = new ArrayList<CommonTree>();
		while (tmp.hasNext())
			elements.add((CommonTree) tmp.next()); /* Any type errors found here! */

		ListIterator<CommonTree> iter = elements.listIterator();//= tree.getChildren().listIterator();
		//System.out.println("This is my token : " + LittleParser.tokenNames[tree.getType()]);
		//System.out.println("as a string" + iter.toString());
		//System.out.println("the class type" + iter.getClass());
		while(iter.hasNext()) {
			CommonTree element = iter.next(); 
			//System.out.println("Operation: " + element.getText() + " ");
			if (element.getChildCount() == 2){
				System.out.println("Left: " + element.getChild(0).getText());
				System.out.println("Right: " + element.getChild(1).getText());
			}
			else if (element.getChildCount() == 1){
				System.out.println("Dest: " + element.getChild(0).getText());

			}
			else if(element.getChildCount() == 0){
				continue;
			}
			else{
				printTree(element);
			}
		}
	}
}





