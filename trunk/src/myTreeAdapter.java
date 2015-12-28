import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.*;


public class myTreeAdapter extends CommonTreeAdaptor {


	public Object create(Token token){
		return new AKNode(token);
	}
	public Object dupNode(Object t) {
		if (t == null){
			return t;
		}
		return create(((AKNode)t).token);
	}
//	public Object rulePostProcessing(Object root) {
//		if (!((AKNode)root).isNil())
//			System.out.println("rulePostProcessing: " + ((AKNode)root).toString());
//		Tree r = (Tree)root;
//		if ( r!=null && r.isNil() ) {
//			if ( r.getChildCount()==0 ) {
//				r = null;
//			}
//			else if ( r.getChildCount()==1 ) {
//				r = (Tree)r.getChild(0);
//				// whoever invokes rule will set parent and child index
//				r.setParent(null);
//				r.setChildIndex(-1);
//			}
//		}
//		return r;
//	}

	//	public Object errorNode(TokenStream input, Token start, Token stop,
	//			RecognitionException e)
	//	{
	//		AKErrorNode t = new AKErrorNode(input, start, stop, e);
	//		return t;
	//	}


	//	@Override
	//	public void addChild(Object arg0, Object arg1) {
	//		// TODO Auto-generated method stub
	//
	//	}

	//	@Override
	//	public Object becomeRoot(Object arg0, Object arg1) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object becomeRoot(Token arg0, Object arg1) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object create(Token arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object create(int arg0, Token arg1) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object create(int arg0, String arg1) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object create(int arg0, Token arg1, String arg2) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object deleteChild(Object arg0, int arg1) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object dupNode(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object dupTree(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object errorNode(TokenStream arg0, Token arg1, Token arg2,
	//			RecognitionException arg3) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Object getChild(Object arg0, int arg1) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public int getChildCount(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	@Override
	//	public int getChildIndex(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	@Override
	//	public Object getParent(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public String getText(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public Token getToken(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public int getTokenStartIndex(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	@Override
	//	public int getTokenStopIndex(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	@Override
	//	public int getType(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	@Override
	//	public int getUniqueID(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	@Override
	//	public boolean isNil(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return false;
	//	}
	//
	//	@Override
	//	public Object nil() {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public void replaceChildren(Object arg0, int arg1, int arg2, Object arg3) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	@Override
	//	public Object rulePostProcessing(Object arg0) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//	@Override
	//	public void setChild(Object arg0, int arg1, Object arg2) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	@Override
	//	public void setChildIndex(Object arg0, int arg1) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	@Override
	//	public void setParent(Object arg0, Object arg1) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	@Override
	//	public void setText(Object arg0, String arg1) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	@Override
	//	public void setTokenBoundaries(Object arg0, Token arg1, Token arg2) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	@Override
	//	public void setType(Object arg0, int arg1) {
	//		// TODO Auto-generated method stub
	//
	//	}

}
