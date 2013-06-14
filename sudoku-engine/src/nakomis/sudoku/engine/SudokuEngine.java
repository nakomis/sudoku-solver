
package nakomis.sudoku.engine;

import java.util.Map;

public class SudokuEngine {
	private final Map<Map.Entry<Integer, Integer>, Integer> presetDigits;
	
	public SudokuEngine(final Map<Map.Entry<Integer,Integer>, Integer> presetDigits) {
		this.presetDigits = presetDigits;
	}

	public void solve() {
		// TODO Auto-generated method stub
		
	}

	public Map<Map.Entry<Integer, Integer>, Integer> getPresetDigits() {
		return presetDigits;
	}
}
