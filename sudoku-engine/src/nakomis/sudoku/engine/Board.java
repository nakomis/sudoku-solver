package nakomis.sudoku.engine;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Board {
	private final Set<Cell> cells;
	private final Set<Group> groups;
	private boolean atDeadEnd;
	private boolean onFire;
	
	public Board(Map<Entry<Integer, Integer>, Integer> presetDigits) {
		cells = new HashSet<Cell>();
		groups = new HashSet<Group>();
		List<Group> rows = new ArrayList<Group>();
		List<Group> columns = new ArrayList<Group>();
		List<Group> quadrants = new ArrayList<Group>();
		for (int i = 0; i < 9; i++) {
			rows.add(new Group());
			columns.add(new Group());
			quadrants.add(new Group());
		}
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Integer preset = presetDigits.get(new AbstractMap.SimpleEntry<Integer, Integer>(x, y));
				Cell cell;
				if (preset == null) {
					cell = new Cell();
				} else {
					cell = new Cell(preset);
				}
				cells.add(cell);
				columns.get(x).add(cell);
				rows.get(y).add(cell);
				quadrants.get(findQuadrant(x, y)).add(cell);
			}
		}
		
		groups.addAll(rows);
		groups.addAll(columns);
		groups.addAll(quadrants);
	}

	private int findQuadrant(int x, int y) {
		return ((int) y / 3) * 3 + x;
	}

	public void run() {
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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSolved() {
		boolean isSolved = true;
		for (Cell cell : cells) {
			isSolved &= cell.isSolved();
		}
		return isSolved;
	}

	public Solution getSolution() {
		// TODO Auto-generated method stub
		return null;
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
