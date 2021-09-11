package avs.aldricvs.resolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.Node;
import avs.aldricvs.node.NodeImpl;
import avs.aldricvs.node.state.State;
import avs.aldricvs.resolver.exceptions.NoNodeFoundException;

public class ResolverImpl implements Resolver {
	
	private Node rootNode;
	
	private Node currentNode;
	
	private List<Node> encounteredNodes = new ArrayList<>();
	
	private int step = 0;
	
//	private HeuristicCalculator heuristicCalculator;

	public ResolverImpl(State initialState, HeuristicCalculator heuristicCalculator) {
		super();
		this.rootNode = new NodeImpl(initialState, heuristicCalculator, null);
		this.currentNode = this.rootNode;
		encounteredNodes.add(currentNode);
//		this.heuristicCalculator = heuristicCalculator;
	}

	@Override
	public boolean isEndStep() {
		return currentNode.isEndState();
	}

	@Override
	public void nextStep() {
		List<Node> childs = currentNode.generateChilds();
		
		Node cheapestChild = childs.stream()
				.filter(this::hasAlreadyEncounteredThisState)
				.min(heuristicComparator)
				.orElseThrow(NoNodeFoundException::new);
		this.currentNode = cheapestChild;
		encounteredNodes.add(currentNode);
		step++;
	}
	
	private static final Comparator<Node> heuristicComparator = (n1, n2) -> n1.getHeuristic() - n2.getHeuristic();
	
	private boolean hasAlreadyEncounteredThisState(Node child) {
		return encounteredNodes.stream()
			.map(Node::getState)
			.noneMatch(n -> n.areSameState(child.getState()));
	}
	
	@Override
	public Node getCurrentNode() {
		return currentNode;
	}
	
	@Override
	public int getCurrentStepCount() {
		return step;
	}
}
