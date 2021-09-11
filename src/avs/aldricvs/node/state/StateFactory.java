package avs.aldricvs.node.state;

public class StateFactory {
	
	public static State createState(int[][] content) {
		return new IntArrayState(content);
	}
}
