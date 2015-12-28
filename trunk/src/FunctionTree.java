import java.util.ArrayList;
import java.util.ListIterator;

import org.antlr.runtime.tree.CommonTree;


public class FunctionTree {
	public String fname = "";
	public ArrayList<String> parameters = new ArrayList<String>();
	public ArrayList<Symbol> localvars = new ArrayList<Symbol>();
	public ArrayList<Symbol> globalvars = new ArrayList<Symbol>();
	public SymbolTable symTable = null;
	public SymbolTable symGlobal = null;
	public String returnvalue = null;
	public CommonTree tree = new CommonTree();
	
	public FunctionTree(CommonTree codetree){
		tree = codetree;
		fname = tree.getChild(0).getText();
		tree.deleteChild(0);
		
		// find any parameters
		CommonTree element = null;
		element = (CommonTree) tree.getChild(0);
		// identify parameters
		if (element.getText().equals("PARAMS")){
			//System.out.println(fname + ": Params found");
			// add all children (parameters) to list of parameters
			for (int i=0; i < element.getChildCount(); i++){
				//System.out.println(element.getChild(0).getText());
				parameters.add(element.getChild(i).getChild(0).getText());
			}
			tree.deleteChild(0);
		}
		
		
		// find return value
		element = (CommonTree) tree.getChild(tree.getChildCount()-1);
		if (element.getText().equals("RETURN")){
			returnvalue = element.getChild(0).getText();
		}
		
	}
	
	public int getLink(){
		return localvars.size();// + globalvars.size() + parameters.size();
	}
	
	public void setReturnVariable(String varname){
		returnvalue = varname;
	}
	
	public String getReturnValue(){
		return returnvalue;
	}
	
	public void setSymbolTables(SymbolTable table, SymbolTable global){
		symTable = table;
		symGlobal = global;
		localvars = table.getSymbols();
		globalvars = global.getSymbols();
	}
	
	public String getVariable(String varname){
		// check for variable in local variables
		for (int i=0; i < localvars.size(); i++){
			if (localvars.get(i).getName().equals(varname)){
				return "$-"+Integer.toString(i+1);
			}
		}
		
		// check for variable in parameters
		for (int i=0; i < parameters.size(); i++){
			if (parameters.get(i).equals(varname)){
				return "$"+Integer.toString(i+6);
			}
		}
		
		// check for variable in global variables
		for (int i=0; i < globalvars.size(); i++){
			if (globalvars.get(i).getName().equals(varname)){
				return globalvars.get(i).getName();
			}
		}
		
		return null;
	}

	public String getFunctionName(){
		return fname;
	}
	
	public ArrayList<String> generateTinyFunctionCall(FunctionTree ftree, FunctionTree caller){
		ArrayList<String> tinycode = new ArrayList<String>();
		// add space for return value
//		if (ftree.returnvalue != null){
//			tinycode.add("push");
//		}
		// push parameters on to stack
		ArrayList<String> params = ftree.parameters;
//		for (int i=0; i<params.size(); i++){
//			System.out.println("Thingy: " + ftree.parameters.get(i));
//			System.out.println(getVariable(params.get(i)));
//			tinycode.add("push " + caller.getVariable(caller.getVariable(ftree.parameters.get(i))));
//		}
		// push registers onto stack
		tinycode.add("push r0");
		tinycode.add("push r1");
		tinycode.add("push r2");
		tinycode.add("push r3");
		// add jsr instruction
		tinycode.add("jsr " + ftree.fname);
		// pop registers
		tinycode.add("pop r3");
		tinycode.add("pop r2");
		tinycode.add("pop r1");
		tinycode.add("pop r0");
		// pop parameters
//		for (int i=0; i<params.size(); i++){
//			tinycode.add("pop");
//		}
		// pop the return valued, the register number will be concatenated when its returned
//		if (ftree.returnvalue != null){
//			//tinycode.add("pop r");
//		}else{
//			tinycode.add(null);
//		}
		return tinycode;
	}
	
}
