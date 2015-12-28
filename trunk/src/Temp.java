
public class Temp {
	public String name;		// name of the temperary variable $Tx
	public String value;	// value of the temperary variable (int or float) or variable name
	public int type;		// type of variable (int, float, or id)
	
	public Temp(int num, int t, String val){
		name = "$T".concat(Integer.toString(num));
		type = t;
		value = val;
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getValue(){
		return value;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int t){
		type = t;
	}
	
	public void setName(String n){
		name=n;
	}
}
