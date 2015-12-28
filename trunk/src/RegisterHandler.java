import java.util.ArrayList;
import java.util.LinkedList;


public class RegisterHandler {
	private LinkedList<Register> registers = new LinkedList<Register>();
	private LinkedList<String> tinycode = new LinkedList<String>();
	private ArrayList<String> liveness = new ArrayList<String>();
	private RegisterStack stack = new RegisterStack();
	private ProgramStorageTable symTable = null;
	private String functionName = null;
	private int numregs = 0;
	private ThreeOpCode code = null;

	public RegisterHandler(){
		for (int i=0; i<4; i++){
			registers.add(new Register());
		}
	}

	public RegisterHandler(int n, ProgramStorageTable table){
		symTable = table;
		for (int i=0; i<n; i++){
			registers.add(new Register());
		}
		numregs=n;
	}

	public void setFunctionName(String name){
		functionName = name;
	}
	// return the temps (used in Micro.generateTiny() to get register numbers)
	public ArrayList<String> getTemps(){
		ArrayList<String> regvals = new ArrayList<String>();
		for (int i=0; i<registers.size(); i++){
			regvals.add(registers.get(i).variableName);
		}
		return regvals;

	}

	// processes three op code with register allocation
	public LinkedList<String> processThreeOpCode(ThreeOpCode c, ArrayList<String> livevars){
		this.code = c;
		
		tinycode.clear();

		liveness = livevars;

		//this.print(code);

		// JSR opcode pushes registers on to stack
		if (code.opcode.contains("JSR")){
			LinkedList<String> regvals = new LinkedList<String>();
			for (int j=0; j<registers.size(); j++){
				regvals.add(registers.get(j).variableName);
				registers.get(j).variableName = "";
			}
			stack.push(regvals);
			return null;
		}
		else if (code.opcode.contains("POP") && code.op1 == null){
			LinkedList<String> regvals = stack.pop();
			for (int k=0; k<regvals.size(); k++){
				if (regvals.get(k) != ""){
					registers.get(k).allocateRegister(regvals.get(k));
				}
			}
			return null;
		} // for store instructions 
		//		else if (code.opcode.contains("STORE")){
		//			
		//		}
		else if (code.op3 == null || code.op2 == null || code.op1 == null){
			
			return null;
		}

		// ensure that both ops are in registers
		int Rx;
		int Ry;
		// allocate a register for result
		int Rz;

		if (code.op1 == null && code.op2 == null){
			Rz = allocate(code.op1);
			setRegisterVarName(Rz,code.op1);
			
			//int Rz = ensure(code.op2);
			registers.get(Rz).dirty = true;
			// store the result register in to the first position in the tiny code
			tinycode.add(Integer.toString(Rz));
		}
		else if (code.op3 != null && !code.op3.equals("")){
			Rx = ensure(code.op1);
			Ry = ensure(code.op2);
			// if op1 is dead after statement free it from the register
			if (!liveness.contains(code.op1)){
				free(Rx);
			}
			// if op2 is dead after statement free it from the register
//			if (!liveness.contains(code.op2)){
//				free(Ry);
//			}
			
			if (!(code.opcode.contains("NE") || code.opcode.contains("EQ") || code.opcode.contains("GE") || code.opcode.contains("LE"))){
				Rz = allocate(code.op3);
				setRegisterVarName(Rz,code.op3);
				
				//int Rz = ensure(code.op2);
				registers.get(Rz).dirty = true;
				// store the result register in to the first position in the tiny code
				tinycode.add(Integer.toString(Rz));
			}
			

		} else{
			Rx   = ensure(code.op1);
			// if op1 is dead after statement free it from the register
			if (!liveness.contains(code.op1)){
				free(Rx);
			}

			Rz = allocate(code.op2);
			setRegisterVarName(Rz,code.op2);
			
			//int Rz = ensure(code.op2);
			registers.get(Rz).dirty = true;
			// store the result register in to the first position in the tiny code
			tinycode.add(Integer.toString(Rz));
		}


		return tinycode;
	}

	public int ensure(String varname){
		// if it is already in register return the register number
		for (int i=0; i<registers.size(); i++){
			if (registers.get(i).variableName.equals(varname) && !registers.get(i).free){
				return i;
			}
		}
		// else, allocate opr, generate load from opr into r
		int r = allocate(varname);
		tinycode.add("move " + symTable.getTinyVar(functionName, varname) + " r" + Integer.toString(r));
		return r;
	}

	public void setMyRegistersFree(){
		tinycode.clear();
		for (int i=0; i < registers.size(); i++){
			registers.get(i).freeRegister();
		}
	}

	public LinkedList<String> ensureStore(String varname){
		tinycode.clear();
		// if it is already in register return null
		for (int i=0; i<registers.size(); i++){
			if (registers.get(i).variableName.equals(varname) && !registers.get(i).free){
				registers.get(i).dirty = false;
				return null;
			}
		}
		// else, allocate opr, generate load from opr into r
		int r = allocate(varname);
		tinycode.add("move " + varname + " r" + Integer.toString(r));
		return tinycode;
	}

	public int allocate(String varname){
		//System.out.println("Adding: " + varname);
		// test for free register (if numregs > 0) and return one if found
		if (varname == null){
			System.out.println("This is wrong");
			System.exit(-1);
		}
		if (numregs > 0){
			for (int i=0; i<registers.size(); i++){
				if (registers.get(i).free){
					registers.get(i).allocateRegister(varname);
					return i;
				}
			}
			// if free register is not found, spill most distant use
			int maxreg = 0;
			// find the register with most distant use
			for (int j=0; j<registers.size(); j++){
				if (registers.get(j).variableName.equals(code.op1)|| 
						registers.get(j).variableName.equals(code.op2)||
						registers.get(j).variableName.equals(code.op3)){
					continue;
				}
				if (liveness.indexOf(registers.get(j).variableName) == -1){
					maxreg = j;
					break;
				}
				if (liveness.indexOf(registers.get(j).variableName) > liveness.indexOf(registers.get(maxreg).variableName)){
					maxreg = j;
				}
			}
			// free the register with most distant use
			//System.out.println("Spill register: " + maxreg);
			free(maxreg);
			registers.get(maxreg).allocateRegister(varname);
			return maxreg;
		}// unlimited number of registers registers
		else{
			registers.add(new Register(varname));
			return registers.size()-1;
		}
	}

	public void free(int maxreg){
		//System.out.println("Freeing: " + maxreg);

		// if the register is marked dirty generate a store
		if (registers.get(maxreg).dirty){
			if (registers.get(maxreg).variableName.contains("$")){
				tinycode.add("move r" + Integer.toString(maxreg) + " " + symTable.getTinyVar(functionName, registers.get(maxreg).variableName));
			}else{
			// move the register to the variable id
			tinycode.add("move r" + Integer.toString(maxreg) + " " + registers.get(maxreg).variableName);
			}
		}
		// mark register as free
		registers.get(maxreg).freeRegister();
	}

	public void setRegisterVarName(int regnum, String name){
		registers.get(regnum).setVarName(name);
	}

	public void print(ThreeOpCode code){
		System.out.println(code.printCode());
		System.out.print("[");
		for (int i = 0; i < this.registers.size()-1; i++){
			System.out.print("(" + i + ", " + registers.get(i).toString() + "), \n");
		}
		System.out.print("(" + (this.registers.size()-1) + ", " + registers.get(this.registers.size()-1).toString() + ") ] \n");


		System.out.println(liveness);
	}

	public int whichRegister(String varname) {


		for (int i=0; i<registers.size(); i++){
			if (registers.get(i).variableName.equals(varname) && !registers.get(i).free){
				return i;
			}
			
		}
		return -1;
	}
}
