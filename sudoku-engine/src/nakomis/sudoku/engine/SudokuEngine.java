
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
			Set<Board> boardsToRemove = new HashSet<Board>();
			Set<Board> boardsToAdd = new HashSet<Board>();
			for (Board board : boards) {
				board.runRules();
				if (board.isOnFire()) {
					boardsToRemove.add(board);
				}
				if (board.isAtDeadEnd()) {
					boardsToRemove.add(board);
					boardsToAdd.addAll(board.split());
				}
				if (board.isSolved()) {
					solutions.add(board.getSolution());
					boardsToRemove.add(board);
				} else {
					allSolved = false;
				}
			}
			boards.removeAll(boardsToRemove);
			boards.addAll(boardsToAdd);
			done = allSolved;
		}
		return solutions;
	}

	public Map<Map.Entry<Integer, Integer>, Integer> getPresetDigits() {
		return presetDigits;
	}
}
