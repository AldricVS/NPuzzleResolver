package avs.aldricvs.app;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.heuristic.ManhattanDistanceHeuristicCalculator;
import avs.aldricvs.node.Node;
import avs.aldricvs.node.state.State;
import avs.aldricvs.node.state.StateFactory;
import avs.aldricvs.resolver.Resolver;
import avs.aldricvs.resolver.ResolverImpl;

public class CliApp implements App {

	private HeuristicCalculator heuristicCalculator = new ManhattanDistanceHeuristicCalculator();
	
	
	
	@Override
	public void resolve(int[][] initialLayout) {
		State initialState = StateFactory.create(initialLayout);
		Resolver resolver = new ResolverImpl(initialState, heuristicCalculator);
		
		printNodes(resolver.getCurrentNode());
		
		while(!resolver.isEndStep()) {
			resolver.nextStep();
			printNodes(resolver.getCurrentNode());
		}
		System.out.println("Puzzle solved in " + resolver.getCurrentStepCount() + " step(s)");
	}

	private void printNodes(Node currentNode) {
		String stringRepresentation = currentNode.getState().toStringRepresentation();
		System.out.println(stringRepresentation);
	}
	
}
