package nakomis.sudoku.engine;

import java.util.HashSet;
import java.util.Set;

public class Cell {
	private final Set<Integer> possibleValues = new HashSet<Integer>();
	private final Integer presetValue;

	public Cell() {
		this.presetValue = null;
		// Initialize the possible values with 1..9
		for (int i = 1; i < 10; i++) {
			getPossibleValues().add(i);
		}
	}

	public Cell(int presetValue) {
		this.presetValue = presetValue;
		this.possibleValues.add(presetValue);
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

	/**
	 * Removes a given value from the set of possible values
	 * @return <code>true</code> if the value was in the possible values (i.e. progress has been made), <code>false</code> otherwise
	 */
	public boolean removePossibleValue(int value) {
		return possibleValues.remove(value);
	}

	public Integer getSolvedValue() {
		if (!isSolved()) {
			throw new RuntimeException("Attempt to get solved value from unsolved cell");
		}
		return possibleValues.iterator().next();
	}
}
