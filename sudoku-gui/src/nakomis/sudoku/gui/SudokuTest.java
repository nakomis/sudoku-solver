package nakomis.sudoku.gui;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nakomis.sudoku.engine.Solution;
import nakomis.sudoku.engine.SudokuEngine;

public class SudokuTest {
	
	private static String[] easySudoku = {
		"904670300",
		"001483000",
		"270000806",
		"130094080",
		"690807013",
		"040360079",
		"709000064",
		"000925700",
		"008046102"
	};
	
	private static String[] moderateSudoku = {
			"007690200",
			"009470630",
			"580002700",
			"200006570",
			"000000000",
			"065800004",
			"001300096",
			"093061400",
			"002089300"
	};
	
	private static String[] extremeSudoku = {
		"008006250",
		"000070030",
		"000012980",
		"005003000",
		"020701060",
		"000800100",
		"036280000",
		"070090000",
		"082100400"
	};
	
	private static String[] multipleSolutionSudoku = {
		"000006250",
		"000000030",
		"000012980",
		"005003000",
		"020701060",
		"000800100",
		"036280000",
		"070090000",
		"082100400"
	};
	
	private static String[] manyMultipleSolutionSudoku = {
		"000000250",
		"000000030",
		"000012980",
		"005003000",
		"020701060",
		"000800100",
		"036280000",
		"070090000",
		"082100400"
	};
	
	public static void main(String[] args) {
		System.out.println("Easy");
		run(easySudoku);
		System.out.println("Moderate");
		run(moderateSudoku);
		System.out.println("Extreme");
		run(extremeSudoku);
		System.out.println("Multiple Solutions");
		run(multipleSolutionSudoku);
		System.out.println("Many Multiple Solutions");
		run(manyMultipleSolutionSudoku);
	}
	
	private static void run(String[] sudokuGrid) {
		Map<Point, Integer> presetDigits = new HashMap<Point, Integer>();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				int digit = Character.getNumericValue(sudokuGrid[row].charAt(column));
				if (digit != 0) {
					presetDigits.put(new Point(column, row), digit);
				}
			}
		}
		SudokuEngine engine = new SudokuEngine(presetDigits);
		Set<Solution> solutions = engine.solve();
		for (Solution solution : solutions) {
			System.out.println(solution.toString());
		}
	}
}
