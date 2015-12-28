import java.util.ArrayList;
import java.util.LinkedList;


public class CFGraph{
	private LinkedList<ThreeOpCode> ircode = new LinkedList<ThreeOpCode>();
	private ArrayList<CFGNode> cfgraph = new ArrayList<CFGNode>();
	private ArrayList<Integer> leaders = new ArrayList<Integer>();
	private ArrayList<Symbol> globalvars = null;
	private String functionName = null;

	public CFGraph(){

	}
	public CFGraph(String fname){
		functionName = fname;
	}

	public String getFname(){
		return functionName;
	}

	public CFGraph(String fname, LinkedList<ThreeOpCode> IRCODE){
		functionName = fname;
		ircode.addAll(IRCODE);
		leaders = generateLeaders();
		generateCFG();
		// run liveness analysis twice to account for backward edges
		generateLiveness();
		generateLiveness();
		//printEdges();
		printLiveness();
	}

	public CFGraph(String fname, LinkedList<ThreeOpCode> IRCODE, SymbolTable globals){
		functionName = fname;
		ircode.addAll(IRCODE);
		leaders = generateLeaders();
		generateCFG();
		globalvars = globals.getSymbols();
		// run liveness analysis twice to account for backward edges
		generateLiveness();
		generateLiveness();
		//printEdges();
		//printLiveness();
	}

	public int getSize(){
		return cfgraph.size();
	}

	private void generateCFG(){
		for (int k = 0; k < ircode.size() + 1; k++){
			cfgraph.add(new CFGNode());
		}

		for (int k = 1; k < ircode.size(); k++){
			if (!ircode.get(k-1).opcode.equals("JUMP")){
				addEdgeToFrom(k, k-1);
			}
		}


		for (int i = 0; i < leaders.size()-1; i++){

			int from_index = leaders.get(i+1) - 1;

			//System.out.println("New Leader: leaders(" + i + ")" + ircode.get(from_index).opcode);
			ThreeOpCode lNode = ircode.get(from_index);
			//System.out.println(lNode.opcode);
			if (lNode.opcode.equals("GE") || lNode.opcode.equals("LE") 
					|| lNode.opcode.equals("NE") || lNode.opcode.equals("JUMP")){
				//branches go to lNode.op3 and the next block

				String op3;
				if (lNode.opcode.equals("JUMP")){
					op3 = lNode.op1;
				}
				else {
					op3 = lNode.op3;
				}


				//linear search for the target in LABEL statement op1
				for(int j = 0; j < ircode.size(); j++){
					//System.out.println(ircode.get(j).opcode);
					if (ircode.get(j).opcode.equals("LABEL") && ircode.get(j).op1.equals(op3)){
						//then there is an edge to j from i
						
						addEdgeToFrom(j, from_index);

					}

				}

				if (!lNode.opcode.equals("JUMP")){
					//then create edge to next block, implicit target of the branch
//Allen TODO when the branch is on the last line of the BB do we simply not add an edge?
					
					if ((from_index+1) < leaders.size()){
						addEdgeToFrom(from_index+1, from_index);
					}
				}

			}

		}
	}

	private ArrayList<Integer> generateLeaders(){
		ArrayList<Integer> leaders = new ArrayList<Integer>();
		String code;
		// add first line in program
		leaders.add(0);
		for (int i = 1; i < ircode.size(); i++){
			code = ircode.get(i).opcode;
			// for conditional statements add the implicit target
			if (code.equals("GE") || code.equals("LE") || code.equals("NE") ){
				leaders.add(i+1);
			}
			// found the explicit target of a conditional branch, add it
			else if (code.equals("LABEL")){
				leaders.add(i);
			}
		}

		return leaders;
	}

	public void printLeaders(){
		System.out.println("LEADERS:");
		for (int i = 0; i < leaders.size(); i++){
			System.out.println(Integer.toString(leaders.get(i)) + ": " + ircode.get(leaders.get(i)).opcode);
		}
	}

	public void printEdges(){
		System.out.println("EDGES: ");

		for (int i = 0; i < ircode.size(); i++){
			if (cfgraph.get(i).hasEdge()){
				cfgraph.get(i).print();
			}
		}
	}

	private void addEdgeToFrom(int to, int from){
		// since the CFG is only being used for liveness only backwards edges are added
		//System.out.println("From: " + from + "\nTo: " + to);

		cfgraph.get(to).addEdge(from);
	}

	public CFGNode get(int index){
		return (this.cfgraph.get(index));
	}

	public void generateLiveness(){
		
		for(int i = cfgraph.size()-2; i>=0; i--){
			// add global variables to liveness
			if (globalvars != null){
				for (int j=0; j<globalvars.size(); j++){
					
					cfgraph.get(i).makeLive(globalvars.get(j).getName());
				}
			}
			if (ircode.get(i).opcode.equals("JUMP") || ircode.get(i).opcode.contains("JSR") ){
				// do nothing
			}
			else if (ircode.get(i).opcode.equals("LABEL")){
				// do nothing
			}
			else if (ircode.get(i).opcode.contains("STORE")){
				// storing keeps the variable live
				try{
					Float.parseFloat(ircode.get(i).op1);
				} catch (Exception e){
					cfgraph.get(i).makeLive(ircode.get(i).op1);
				}
				// kill the variable being stored to
				cfgraph.get(i).killVariable(ircode.get(i).op2);
			}
			else if (ircode.get(i).opcode.contains("WRITE")){
				// writing keeps the variable live
				cfgraph.get(i).makeLive(ircode.get(i).op1);
			} // pushing makes variable live
			else if (ircode.get(i).opcode.contains("PUSH")){
				if (ircode.get(i).op1 != null){
					cfgraph.get(i).makeLive(ircode.get(i).op1);
				}
			} // pop kills the variable
			else if (ircode.get(i).opcode.contains("POP")){
				if (ircode.get(i).op1 != null){
					cfgraph.get(i).killVariable(ircode.get(i).op1);
				}
			}
			else{
				// GE, LE, NE, ADDs, MULTs, SUBs caught
				// first two ops are made live, last is killed
				try{
					Float.parseFloat(ircode.get(i).op1);
				} catch (Exception e){
					cfgraph.get(i).makeLive(ircode.get(i).op1);
				}
				//cfgraph.get(i).makeLive(ircode.get(i).op1);
				cfgraph.get(i).makeLive(ircode.get(i).op2);
				cfgraph.get(i).killVariable(ircode.get(i).op3);
			}

			// add live variables to live sets of branch targets
			ArrayList<Integer> edges = cfgraph.get(i).getEdges();
			for (int e=0; e<edges.size(); e++){
				// for each edge add the live set to the edge's live set
				cfgraph.get(edges.get(e)).addLiveVariables(cfgraph.get(i).getLiveAfter());
			}
		}
	}

	public ArrayList<String> getLiveness(int linenum){
		return cfgraph.get(linenum).getLiveAfter();
	}

	public void printLiveness(){
		if (functionName != null){
			System.out.println(functionName + ":");
		}
		for (int i=0; i<ircode.size(); i++){
			System.out.println(cfgraph.get(i).getLiveAfter());
		}
	}

	public ArrayList<String> getLiveGlobals(int linenum){
		ArrayList<String> r = new ArrayList<String>();
		
		if (globalvars != null){
			for (int j=0; j<globalvars.size(); j++){
				
				r.add(globalvars.get(j).getName());
			}
		}
		return r;
	}
}
