import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;


public class AKNode extends CommonTree {
	/*Our fields*/
	Temp t = null;
	String[] tokenNames = LittleParser.tokenNames;
	//So what things could be usefule here?
	
	public AKNode(){
		//System.out.println("Zero Arg Constructor");
	}
	public AKNode(Token t) {
		token=t;
		//print();
	}                
	public AKNode(AKNode node) {
		super(node);
	}            
	public Tree dupNode() { 
		return new AKNode(this); 
	}


	public String toString() {
		return token.getText()+"<AKNode>";
		
	
	}

	//for example, every expr() will get a new temporary, but the AST isn't really set up for that as is :-(
	public void print(){

		//operators indicate subexpressions, grammar contains no unary operators
		//    	for (int i = 0; i < tokenNames.length; i++){
		//    		if (tokenNames[i].equalsIgnoreCase("!=") || ){
		//    			
		//    		}
		//    	}

		try {
			System.out.println("(Token t) Constructor\t" + LittleParser.tokenNames[this.getType()] + "\t" + token.getText());
		} catch (NullPointerException e) {

			// TODO: handle exception
		}
	}
}