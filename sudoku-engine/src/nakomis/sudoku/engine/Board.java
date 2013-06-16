package nakomis.sudoku.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
	private final List<Cell> cells;
	private final Set<Group> groups;
	private final Map<Point, Integer> presetDigits;
	private boolean atDeadEnd;
	private boolean onFire;
	
	public Board(Map<Point, Integer> presetDigits) {
		this.presetDigits = presetDigits;
		cells = new ArrayList<Cell>();
		groups = new HashSet<Group>();
		List<Group> rows = new ArrayList<Group>();
		List<Group> columns = new ArrayList<Group>();
		List<Group> quadrants = new ArrayList<Group>();
		for (int i = 0; i < 9; i++) {
			rows.add(new Group());
			columns.add(new Group());
			quadrants.add(new Group());
		}
		
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Integer preset = presetDigits.get(new Point(x, y));
				Cell cell;
				if (preset == null) {
					cell = new Cell(x, y);
				} else {
					cell = new Cell(preset, x, y);
				}
				cells.add(cell);
				columns.get(x).add(cell);
				rows.get(y).add(cell);
				quadrants.get(findRegion(x, y)).add(cell);
			}
		}
		
		groups.addAll(rows);
		groups.addAll(columns);
		groups.addAll(quadrants);
	}

	private int findRegion(int x, int y) {
		return (y / 3) * 3 + (x / 3);
	}

	public void runRules() {
		boolean progressMade = false;
		// Run 'the rules' for each group. If no progress is made, we're at a dead end and will need to be split
		for (Group group : groups) {
			try {
				progressMade |= group.checkForProgress();
			} catch (SudokuException e) {
				this.setOnFire(true);
			}
		}
		if (!progressMade) {
			setAtDeadEnd(true);
		} else {
			setAtDeadEnd(false);
		}
	}

	public Collection<Board> split() {
		Cell targetCell = null;
		int minPossibleValues = 10;
		for (Cell cell : cells) {
			if (!cell.isSolved() && cell.getPossibleValues().size() < minPossibleValues) {
				targetCell = cell;
				minPossibleValues = cell.getPossibleValues().size();
			}
		}
		Collection<Board> splitBoard = new HashSet<Board>();
		for (Integer possibleValue : targetCell.getPossibleValues()) {
			Board board = this.cloneAndSetValue(targetCell, possibleValue);
			splitBoard.add(board);
		}
		return splitBoard;
	}

	private Board cloneAndSetValue(Cell targetCell, Integer value) {
		Board newBoard = new Board(presetDigits);
		for (Cell cell : this.cells) {
			newBoard.setCellPossibleValues(cell, cell.getPossibleValues());
		}
		newBoard.setCell(targetCell, value);
		return newBoard;
	}

	private void setCell(Cell targetCell, Integer possibleValue) {
		for (Cell cell : cells) {
			if (cell.equalsXY(targetCell)) {
				try {
					cell.setValue(possibleValue);
				} catch (SudokuException e) {
					this.setOnFire(true);
				}
			}
		}
	}

	private void setCellPossibleValues(Cell targetCell, Set<Integer> possibleValues) {
		for (Cell cell : cells) {
			if (cell.equalsXY(targetCell)) {
				cell.setPossibleValues(possibleValues);
				break;
			}
		}
	}

	public boolean isSolved() {
		boolean isSolved = true;
		for (Cell cell : cells) {
			isSolved &= cell.isSolved();
		}
		return isSolved;
	}

	public Solution getSolution() {
		List<Integer> values = new ArrayList<Integer>();
		for (Cell cell : cells) {
			values.add(cell.getSolvedValue());
		}
		return new Solution(values);
	}
	
	public boolean isAtDeadEnd() {
		return atDeadEnd;
	}

	private void setAtDeadEnd(boolean atDeadEnd) {
		this.atDeadEnd = atDeadEnd;
	}
	
	public boolean isOnFire() {
		return onFire;
	}
	
	private void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}

}
