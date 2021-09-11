package avs.aldricvs.node;

import java.util.List;
import java.util.Optional;

import avs.aldricvs.node.state.State;

public interface Node {
	
	public State getState();
	
	public int getHeuristic();
	
	public Optional<Node> getParent();
	
	public boolean isEndState();
	
	public List<Node> generateChilds();
}
