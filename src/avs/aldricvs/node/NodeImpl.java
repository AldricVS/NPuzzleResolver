package avs.aldricvs.node;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.state.State;

public class NodeImpl implements Node {

	private State state;

	private int heuristic;
	
	private HeuristicCalculator heuristicCalculator;

	private Node parent;

	public NodeImpl(State state, HeuristicCalculator heuristicCalculator, Node parent) {
		super();
		this.state = Objects.requireNonNull(state);
		this.heuristicCalculator = Objects.requireNonNull(heuristicCalculator);
		this.parent = Objects.requireNonNull(parent);
		this.heuristic = heuristicCalculator.calculateHeuristic(state);
	}

	@Override
	public List<Node> generateChilds() {
		return Arrays.asList();
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public int getHeuristic() {
		return heuristic;
	}

	@Override
	public Node getParent() {
		return parent;
	}
}
