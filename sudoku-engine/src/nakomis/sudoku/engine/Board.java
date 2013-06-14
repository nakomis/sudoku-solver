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
		// TODO Auto-generated method stub
		
	}

	public boolean isOnFire() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAtDeadEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<Board> split() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSolved() {
		// TODO Auto-generated method stub
		return false;
	}

	public Solution getSolution() {
		// TODO Auto-generated method stub
		return null;
	}

}
