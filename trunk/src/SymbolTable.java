import java.util.ArrayList;

public class SymbolTable {
	private ArrayList<Symbol> symbols = new ArrayList<Symbol>();
	private String func_name;
	private String ret_type;
	private String[] arguments;
	private int argc = 0;
	
	public SymbolTable(){
		func_name = "%glob";
	}
	// New symbol table (name) is the function name
	public SymbolTable(String name){
		func_name = name;
		ret_type = "VOID";
		arguments = null;
	}
	
//	public SymbolTable(String name, String[] argv, String rt){
//		func_name = name;
//		ret_type = new String(rt.toString().trim());
//		arguments = argv;
//	}
	public boolean containsSymbol(String symname){
		for (int i=0; i<symbols.size(); i++){
			if (symbols.get(i).getName().equals(symname))
				return true;
		}
		return false;
	}
	
	public String getTableName(){
		return func_name.trim();
	}
	
	public ArrayList<Symbol> getSymbols(){
		return symbols;
	}
	
	public void insertSymbol(Symbol symb){
		symbols.add(symb);
	}
	
	public void printSymbolTable(){
		symbols.trimToSize();
		if (func_name.equals("%glob")){
			System.out.println("Printing Global Symbol Table");
		}else{
			System.out.println("Printing Symbol Table for " +func_name);
		}
		
		for (int i=0; i<symbols.size(); i++){
			System.out.println(symbols.get(i).getSymbol());
		}
		
		//System.out.println("");
	}
	
	public int lookupType(String s){
		int type = -1;
		for (int i=0; i<symbols.size();i++){
			if (s.equals(symbols.get(i).getName())){
				if (symbols.get(i).getType().equalsIgnoreCase("INT")){
					return LittleParser.INTLITERAL;
				}
				else if (symbols.get(i).getType().equalsIgnoreCase("FLOAT")){
					return LittleParser.FLOATLITERAL;
				}
				else if (symbols.get(i).getType().equalsIgnoreCase("STRING")){
					return LittleParser.STRINGLITERAL;
				}
				break;
			}
		}
		return type;
	}
	
	public void updateType(String s, int t){
		for (int i=0; i<symbols.size();i++){
			if (s.equals(symbols.get(i).getName())){
				if (t == LittleParser.INTLITERAL){
					symbols.get(i).setType("INT");
				}
				else if (t == LittleParser.FLOATLITERAL){
					symbols.get(i).setType("FLOAT");
				}
			}
		}
	}
}
