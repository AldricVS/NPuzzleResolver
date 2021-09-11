package avs.aldricvs.resolver;

import avs.aldricvs.node.Node;

public interface Resolver {

	boolean isEndStep();

	void nextStep();

	Node getCurrentNode();

	int getCurrentStepCount();
}