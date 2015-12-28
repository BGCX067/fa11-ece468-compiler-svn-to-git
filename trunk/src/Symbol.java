
public class Symbol {
	private String name;
	private String type;
	private String string_var;
	
	// Default Constructor
	public Symbol(){
		name = "a";
		type = "INT";
		string_var = "";
	}
	// Int or Float variable
	public Symbol(String n, String t){
		name = n;
		type = t;
		string_var = "";
	}
	// String variable
	public Symbol(String n, String t, String sv){
		name = n;
		type = t;
		string_var = sv;
	}
	
	public String getSymbol(){
		if (string_var.length() <= 0) {
			return "\tname:\t\t" + name + "\t\t\ttype:\t\t" + type;
		}
		else {
			return "name:\t" + name + "\ttype: " + type + " value: " + string_var;
		}
	}
	public String getString(){
		return string_var;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String t){
		type = t;
	}
}
