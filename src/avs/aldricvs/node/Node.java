package avs.aldricvs.node;

import java.util.List;

import avs.aldricvs.node.state.State;

public interface Node {
	
	public State getState();
	
	public int getHeuristic();
	
	public Node getParent();
	
	public List<Node> generateChilds();
}
