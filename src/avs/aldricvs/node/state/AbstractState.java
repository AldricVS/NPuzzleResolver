package avs.aldricvs.node.state;

import avs.aldricvs.node.point.Position;

public abstract class AbstractState implements State {

	@Override
	public boolean areSameState(State state) {
		int size = state.getSize();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Position position = new Position(row, col);
				int num = this.getNumberAt(position);
				int otherNum = state.getNumberAt(position);
				if(num != otherNum) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String getStringRepresentation() {
		int size = this.getSize();
		StringBuilder stringBuilder = new StringBuilder();
		for (int col = 0; col < size; col++) {
			for (int row = 0; row < size; row++) {
				Position position = new Position(row, col);
				int num = this.getNumberAt(position);
				stringBuilder.append(num + "");
			}
			stringBuilder.append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}
}
