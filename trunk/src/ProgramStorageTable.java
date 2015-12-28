import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class ProgramStorageTable {
	private static HashMap<String, SymbolTable> ScopeSymTable = new HashMap<String, SymbolTable>();
	private static HashMap<String, String[]> Memory = new HashMap<String, String[]>();//currently unused
	private Stack<String> currentScope = new Stack<String>();

	//Constructors
	ProgramStorageTable(){
		currentScope.add("Global");
		this.ScopeSymTable.put("Global", new SymbolTable("Global"));
	}
	//Methods
	public SymbolTable getSymbolTable(String name){
		return ScopeSymTable.get(name);
	}

	/*adds a new SymbolTable to the ScopeSymTable
	 * which keeps a table for each function declared within the program
	 * and it always has a global table
	 */
	public void addSymbolTable(SymbolTable table){
		if (ScopeSymTable.containsKey(table.getTableName())){
			System.out.println("Duplicately defined function: " + table.getTableName());
			System.exit(73);
		}else{
			ScopeSymTable.put(table.getTableName(), table);
		}
	}
	/*Adds a scope name denoted by 'String s' to the
	 * arrayList  'currentScope' then returns an array containing all
	 * scopes currently available
	 */
//	public String[] addScopeToCurrentScope(String s){
//		currentScope.add(s);
//		String[] ret = new String[currentScope.size()];
//
//		ListIterator<String> iter = currentScope.listIterator();
//		int i=0;
//		while(iter.hasNext()) {
//			ret[i] = iter.next();
//			i++;
//		}
//		return (ret);
//	}
	
	//When walking the tree, use this method to change the effective scope
	//of lookupType
	public void pushScope(String scope){
		currentScope.push(scope);
	}
	
	//when you return from a function, pop it's name off the scope stack 
	public String popScope(){
		return currentScope.pop();
	}

	/* Returns true if successful, false otherwise
	 */
	public void addSymbolByFunctionName(String fname, Symbol sym){
		if (exists(fname)){
			ScopeSymTable.get(fname).insertSymbol(sym);
		} else {
			System.err.println("You're trying to add a symbol to a table that doesn't exist");
			System.exit(-12);
		}
		
	}
	//lookupType in both the Global and local scope
	//local scope is the scope on top of the currentScope stack
	public int lookupType(String s){
		int glob = ScopeSymTable.get(currentScope.firstElement()).lookupType(s);
		int local = ScopeSymTable.get(currentScope.peek()).lookupType(s); //lookup in the
		if (glob == -1 && local == -1){
			return -1;
		}else if(glob != -1){
			return glob;
		}else{
			return local;
		}
	}
	public int getNumberOfParameters(String fname){
		ArrayList<Symbol> symbols = this.getSymbolTable(fname).getSymbols();
		int np = 0;
		for (int i=0; i<symbols.size(); i++){
			if (symbols.get(i).getType().equals("PARAM"))
				np++;
		}
		return np;
	}
	public String getIndex(String fname, String varname){
		int index=0;
		//System.out.println(fname);
		Iterator<Symbol> iter = this.getSymbolTable(fname).getSymbols().iterator();
//		if (!iter.hasNext()){
//			System.exit(-1);
//		}
		
		Symbol currSymbol;
		int nParam = 0;
		
		while(iter.hasNext()){
			currSymbol = iter.next();
			if(currSymbol.getName().equalsIgnoreCase(varname)){
				if (currSymbol.getType().equals("PARAM")){
					
					index = 6+this.getSymbolTable(fname).getSymbols().indexOf(currSymbol);
					return "$"+Integer.toString(index);
				}
				else if (currSymbol.getType().contains("STRING")){
					return currSymbol.getName();
				}
				else{
					index = (-1)*(this.getSymbolTable(fname).getSymbols().indexOf(currSymbol)+1-nParam);
					return "$"+Integer.toString(index);
				}
			}
			if (currSymbol.getType().equals("PARAM")){
				nParam++;
			}
		} // need to return negative offsets from main
		iter = this.getSymbolTable("Global").getSymbols().iterator();
		while(iter.hasNext()){
			currSymbol = iter.next();
			if(currSymbol.getName().equalsIgnoreCase(varname)){
				return currSymbol.getName();
				
			}
		}
		//System.out.println(varname + " : " + index);
		// if it gets this far there is no variable found
		//return varname;
		return Integer.toString(index).trim();
	}
	
	public String getTinyVar(String fname, String varname){
		String index = getIndex(fname, varname);
		return index;
	}
	
	public void replaceVariableNames(){
		
	}
	
	public void print(){
		Iterator<String> k = ScopeSymTable.keySet().iterator();
		while(k.hasNext()){
			ScopeSymTable.get(k.next()).printSymbolTable();
		}
	}

	public boolean isGlobal(String varname){
		if (getSymbolTable("Global").containsSymbol(varname)){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean exists(String fn){
		return this.ScopeSymTable.containsKey(fn);
	}
}
