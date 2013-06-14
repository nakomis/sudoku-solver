
package nakomis.sudoku.engine;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SudokuEngine {
	private final Map<Map.Entry<Integer, Integer>, Integer> presetDigits;
	private final Set<Board> boards = new HashSet<Board>();
	
	public SudokuEngine(final Map<Map.Entry<Integer,Integer>, Integer> presetDigits) {
		this.presetDigits = presetDigits;
	}

	public Set<Solution> solve() {
		Board startingBoard = new Board(presetDigits);
		boards.add(startingBoard);
		boolean done = false;
		Set<Solution> solutions = new HashSet<Solution>();
		while (!done) {
			boolean allSolved = true;
			for (Board board : boards) {
				board.run();
				if (board.isOnFire()) {
					boards.remove(board);
				}
				if (board.isAtDeadEnd()) {
					boards.remove(board);
					boards.addAll(board.split());
				}
				if (board.isSolved()) {
					solutions.add(board.getSolution());
				} else {
					allSolved = false;
				}
			}
			done = allSolved;
		}
		return solutions;
	}

	public Map<Map.Entry<Integer, Integer>, Integer> getPresetDigits() {
		return presetDigits;
	}
}
