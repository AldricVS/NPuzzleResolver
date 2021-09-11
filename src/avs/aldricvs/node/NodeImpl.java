package avs.aldricvs.node;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.state.Direction;
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
		return Arrays.stream(Direction.values())
				.map(state::swapBlankBox)
				.filter(Optional::isPresent) // TODO : is present / get is very ugly
				.map(Optional::get)
				.filter(s -> !state.areSameState(s))
				.map(s -> new NodeImpl(s, heuristicCalculator, this))
				.collect(Collectors.toList());
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

	@Override
	public boolean isEndState() {
		return state.isEndState();
	}
}
