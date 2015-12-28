import java.util.LinkedList;


public class RegisterStack {
	private LinkedList<String> stack = new LinkedList<String>();
	
	public RegisterStack(){
		
	}
	
	public void push(LinkedList<String> regs){
		// add regs to stack
		for (int i=0; i<regs.size(); i++){
			stack.addFirst(regs.get(i));
		}
	}
	
	public LinkedList<String> pop(){
		LinkedList<String> popvals = new LinkedList<String>();
		if (stack.size() >= 4){
			for (int i=0; i<4; i++){
				popvals.addFirst(stack.get(0));
				stack.remove();
			}
		}
		return popvals;
	}
}
