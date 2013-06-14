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
	 * @return <code>true</code> if any progress has been made (i.e. cells solved), <code>false</code> otherwise
	 */
	public boolean checkForProgress() throws SudokuException {
		// Run the rules and 
		return false;
	}
}
