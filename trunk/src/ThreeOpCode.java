public class ThreeOpCode {
	public String opcode;
	public String op1;
	public String op2;
	public String op3;
	
	
	public ThreeOpCode(String code, String one){
		opcode = code.trim();
		op1 = one.trim();
		op2=null;
		op3=null;
		
	}
	public ThreeOpCode(String code){
		opcode = code.trim();
		op1 = null;
		op2 = null;
		op3 = null;
	}
	public ThreeOpCode(String code, String one, String two){
		opcode = code.trim();
		op1 = one.trim();
		op2=two.trim();
		op3=null;
		
	}
	public ThreeOpCode(String code, String one, String two, String three){
		opcode = code.trim();
		op1 = one.trim();
		op2=two.trim();
		op3=three.trim();
		
	}
	public String getTemp(int op) {
		String return_val = new String();
		String tempPrefix = "[$]{1}[T]{1}";
		String ws = "[ ]+";
		String look = new String();
		switch (op) {
        	case 0: return null; //this will never have a temp thing
        	case 1: look = op1; break;
        	case 2: look = op2; break;
        	case 3: look = op3; break;
		}
	    String[] result = look.split(tempPrefix);
	    
	    Integer j;
	    for(int i=0; i < result.length; i++){
	    	try {
	    		if (result[i].split(ws).length > 1){
	    			j = new Integer(result[i].split(ws)[0].trim());
	    		}
	    		else{
	    			j = new Integer(result[i].trim());
	    		}
	    			return_val = String.valueOf(j);
	    			
	    			//System.out.println("location = " + i + "\tTempnum = " + j.toString());
	    	}
	    	catch (java.lang.NumberFormatException e) {
	    		
	    	}
	    	
	    }
	    return return_val;
	}
	public String printCode(){
		String s = opcode;
		if (op1 != null){
			s = s + " " + op1;
		}
		if (op2 != null){
			s = s + " " + op2;
		}
		if (op3 != null){
			s = s + " " + op3;
		}
		return s;
	}

	
	

}
