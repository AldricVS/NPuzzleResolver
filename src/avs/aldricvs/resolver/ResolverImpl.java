package avs.aldricvs.resolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.Node;
import avs.aldricvs.node.NodeImpl;
import avs.aldricvs.node.state.State;
import avs.aldricvs.resolver.exceptions.NoNodeFoundException;

public class ResolverImpl implements Resolver {

	private Node rootNode;

	private Node currentNode;

	private List<Node> openList = new ArrayList<>();

	private List<Node> closedList = new ArrayList<>();

//	private HeuristicCalculator heuristicCalculator;

	public ResolverImpl(State initialState, HeuristicCalculator heuristicCalculator) {
		super();
		this.rootNode = new NodeImpl(initialState, heuristicCalculator, null);
		this.currentNode = this.rootNode;
		this.openList.add(currentNode);
//		this.heuristicCalculator = heuristicCalculator;
	}

	public Optional<Node> findBestPath() {
		while (!openList.isEmpty()) {
			System.out.println(openList.size());
			Node cheapestNode = openList.stream()
			        .min(heuristicComparator)
			        .orElseThrow(RuntimeException::new);

			if (cheapestNode.isEndState()) {
				return Optional.of(cheapestNode);
			}

			openList.remove(cheapestNode);
			closedList.add(cheapestNode);

			List<Node> childs = cheapestNode.generateChilds();
			childs.stream()
			        .filter(this::hasAlreadyEncounteredThisState)
			        .forEach(openList::add);
		}
		// no solution found
		return Optional.empty();
	}

	private static final Comparator<Node> heuristicComparator = Comparator.comparingInt(Node::getHeuristic);

	private boolean hasAlreadyEncounteredThisState(Node child) {
		return closedList.stream()
		        .map(Node::getState)
		        .noneMatch(n -> n.areSameState(child.getState()));
	}
}
