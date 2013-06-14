package nakomis.sudoku.engine;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private final Set<Cell> cells;
	
	public Group() {
		cells = new HashSet<Cell>();
	}
	
	public void add(Cell cell) {
		cells.add(cell);
	}

	/**
	 * Runs the rules "<b>Must have each digit 1-9</b>" and "<b>Digits cannot be duplicated</b>"
	 * 
	 * @return <code>true</code> if any progress has been made (i.e. cells solved), <code>false</code> otherwise
	 * @throws SudokuException if an inconsistency is found, i.e. two or more cells have the same value, or a give value is not possible for any cell
	 */
	public boolean checkForProgress() throws SudokuException {
		boolean progress = false;
		for (int i = 1; i < 9; i++) {
			// Group must have each digit 1-9, therefore if only one cell can possibly be i, then it must be i
			Set<Cell> possibleCells = new HashSet<Cell>();
			for (Cell cell : cells) {
				if (cell.getPossibleValues().contains(i)) {
					possibleCells.add(cell);
				}
			}
			if (possibleCells.size() == 0) {
				// No cell can contain the value - the board is on fire
				throw new SudokuException();
			}
			if (possibleCells.size() == 1) {
				possibleCells.iterator().next().setValue(i);
				progress = true;
			}
		}
		
		// Digits cannot be duplicated, so look for the 'solved' cells, and remove that value from the other cells' possible values
		// NOTE: this is a pretty inefficient way of doing this, but I'm hoping it's clearer to read...
		Set<Integer> solvedValues = new HashSet<Integer>();
		for (Cell cell : cells) {
			if (cell.isSolved()) {
				solvedValues.add(cell.getSolvedValue());
			}
		}
		
		for (Cell cell : cells) {
			for (Integer value : solvedValues) {
				if (!(cell.isSolved())) {
					progress |= cell.removePossibleValue(value);
				}
			}
		}
		
		return progress;
	}
}
