import java.util.ArrayList;


public class CFGNode {
	private static int numofnodes = 0;
	//	private ArrayList<String> livebefore = new ArrayList<String>();
	private ArrayList<String> liveafter = new ArrayList<String>();
	private ArrayList<Integer> edges = new ArrayList<Integer>();
	private boolean hasedge = false;
	private int nodeId = 0;

	public CFGNode(){
		nodeId = numofnodes++;
	}
	public void addEdge(int edge){
		hasedge = true;
		edges.add(edge);
	}

	public boolean hasEdge(){
		return hasedge;
	}

	public ArrayList<Integer> getEdges(){
		return edges;
	}
	
	public char[] print() {
		// TODO Auto-generated method stub

		for (int i = 0; i < edges.size(); i++){
			System.out.println("From: " + nodeId + " To : " + edges.get(i));
		}
		return null;
	}

	public ArrayList<String> getLiveAfter(){
		return (liveafter);
	}

	// kill the variable from the live set
	public void killVariable(String var){
		liveafter.remove(var);
	}

	// set the variable as live after the function
	public void makeLive(String var){
		
		// if the variable is already live delete it and move to front (used to find furthest use)
		if (liveafter.contains(var)){
			liveafter.remove(var);
		}
		liveafter.add(0, var);
	}

	// used for adding live variables on branches
	public void addLiveVariables(ArrayList<String> vars){
		for (int i = 0; i < vars.size(); i++){
			if (!liveafter.contains(vars.get(i))){
				liveafter.add(vars.get(i));
			}
		}
	}

}
