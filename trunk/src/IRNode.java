

public class IRNode {
	public String vtype = null;
	public String opcode = null;
	public String value = null;
	public IRNode leftChild = null;
	public IRNode rightChild = null;
	public static int td = 0;
	public int level = td;
	
	public IRNode(String op, String type, IRNode left, IRNode right){
		opcode = op;
		vtype = type;
		leftChild=left;
		rightChild=right;
		
		if (vtype=="int"){
			if (opcode=="add"){
				opcode = "addi";
			}
			else if (opcode=="sub"){
				opcode = "subi";
			}
			else if (opcode=="mul"){
				opcode = "multi";
			}
			else if (opcode=="div"){
				opcode = "divi";
			}
		}
		else if (vtype=="float"){
			if (opcode=="add"){
				opcode = "addf";
			}
			else if (opcode=="sub"){
				opcode = "subf";
			}
			else if (opcode=="mul"){
				opcode = "multf";
			}
			else if (opcode=="div"){
				opcode = "divf";
			}
		}
	}
	
	public IRNode(String type, String val){
		vtype = type;
		value = val;
	}
	
	public String getType(){
		return vtype;
	}
	
	public String getValue(){
		return value;
	}
	
	public IRNode getRightChild(){
		return rightChild;
	}
	public IRNode getLeftChild(){
		return leftChild;
	}

	//Allen's Stuff
	public void printTree(){
		//System.out.println("PRINTING");

		
		for (int i = td; i > 0; i--){
			if (i<0)
				break;
			System.out.printf("\t");
		}
		
		td++;
		
		
		
		if (leftChild != null){
			//System.out.println("lefts");
			leftChild.printTree();
		}
		//Print operation
				if (opcode != null){
					System.out.println(opcode);
				}
		if (rightChild != null){
			//System.out.println("right");
			rightChild.printTree();
		}
		
		if (opcode == null){ //variable
			System.out.println(vtype + ": " + value);
		}
		else{
			//System.out.println(opcode);
		}
		td--;
	}

	/*public void printTree(){
		//System.out.println("PRINTING");
		td++;
		for (int i = td; i <= 6; i++){
			if (i>6)
				break;
			System.out.printf("\t");
		}
		if (opcode != null){
			System.out.println(opcode);
		}
		if (leftChild != null){
			//System.out.println("left");
			leftChild.printTree();
		}
		
		if (rightChild != null){
			//System.out.println("right");
			rightChild.printTree();
		}
		
		if (opcode == null){ //variable
			System.out.println(vtype + ": " + value);
		}
		else{
			//System.out.println(opcode);
		}
		td--;
	}*/
}