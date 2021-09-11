package avs.aldricvs.resolver;

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
	
	private int step = 0;
	
//	private HeuristicCalculator heuristicCalculator;

	public ResolverImpl(State initialState, HeuristicCalculator heuristicCalculator) {
		super();
		this.rootNode = new NodeImpl(initialState, heuristicCalculator, null);
		this.currentNode = this.rootNode;
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
				.min(heuristicComparator)
				.orElseThrow(NoNodeFoundException::new);
		this.currentNode = cheapestChild;
		step++;
	}
	
	private static final Comparator<Node> heuristicComparator = (n1, n2) -> n1.getHeuristic() - n2.getHeuristic();
	
	@Override
	public Node getCurrentNode() {
		return currentNode;
	}
	
	@Override
	public int getCurrentStepCount() {
		return step;
	}
}
