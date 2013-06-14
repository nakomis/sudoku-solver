package nakomis.sudoku.engine;

import java.util.List;

public class Solution {
	private List<Integer> values;
	
	public Solution(List<Integer> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (values.size() != 81) {
			throw new RuntimeException("Invalid number of values passed to solution");
		}
		int columnCount = 0;
		for (Integer value : values) {
			builder.append(String.valueOf(value));
			builder.append(" | ");
			columnCount++;
			if (columnCount == 9) {
				builder.append(System.getProperty("line.separator")); // Apple, *nix and Windows friendly
				columnCount = 0;
			}
		}
		return builder.toString();
	}
}
