package avs.aldricvs.resolver;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.Node;

public class ResolverImpl {
	
	private Node rootNode;
	
	private Node currentNode;
	
	private HeuristicCalculator heuristicCalculator;

	public ResolverImpl(Node rootNode, HeuristicCalculator heuristicCalculator) {
		super();
		this.rootNode = rootNode;
		this.heuristicCalculator = heuristicCalculator;
	}

	public boolean isEndStep() {
		return currentNode.isEndState();
	}
	
	
}
