import org.antlr.runtime.*;

public class Micro {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception {
        LittleLexer lex = new LittleLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        LittleParser p = new LittleParser(tokens);
        
        
        try {
			p.program();
			System.out.println("Accepted");
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Not Accepted");
		} 
        
    }

}
