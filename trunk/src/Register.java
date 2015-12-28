
public class Register {
	public boolean dirty;
	public boolean free;
	public String variableName;
	
	public Register(){
		variableName = "";
		dirty = false;
		free = true;
	}
	public Register(String varname){
		variableName = varname;
		dirty = false;
		free = false;
	}
	
	public void setVarName(String varname){
		variableName = varname;
	}
	
	public void allocateRegister(String varname){
		free = false;
		variableName = varname;
		dirty = false;
	}
	
	public void freeRegister(){
		variableName = "";
		free = true;
		dirty = false;
	}
	
	public String toString(){
		String s = new String("[" + variableName + ", " + free +", " + dirty+"]");
		return s;
	}
}
