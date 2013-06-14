package nakomis.sudoku.engine;

import java.util.HashSet;
import java.util.Set;

public class Cell {
	private final Set<Integer> possibleValues;
	private final Integer presetValue;

	public Cell() {
		this.presetValue = null;
		// Initialize the possible values with 1..9
		this.possibleValues = new HashSet<Integer>();
		for (int i = 1; i < 10; i++) {
			getPossibleValues().add(i);
		}
	}

	public Cell(int presetValue) {
		this.possibleValues = null;
		this.presetValue = presetValue;
	}

	public Integer getPresetValue() {
		return presetValue;
	}

	public Set<Integer> getPossibleValues() {
		return possibleValues;
	}

	public boolean isSolved() {
		return (presetValue != null) || possibleValues.size() == 1;
	}
	
	public void setValue(int value) throws SudokuException {
		if (!possibleValues.contains(value)) {
			throw new SudokuException();
		}
		
		possibleValues.clear();
		possibleValues.add(value);
	}

	public void removePossibleVelue(int value) {
		possibleValues.remove(value);
	}
}
