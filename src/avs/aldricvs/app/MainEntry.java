package avs.aldricvs.app;

import avs.aldricvs.node.state.State;

public class MainEntry {

	public static void main(String[] args) {

		App app = new CliApp();

		int[][] initialLayout = {
				{ 4, 1, 3 },
				{ 7, 2, 6 },
				{ 5, 8, State.BLANK_BOX_VALUE }
		};

		app.resolve(initialLayout);
	}
}
