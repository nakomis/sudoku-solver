package nakomis.sudoku.gui;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nakomis.sudoku.engine.Solution;
import nakomis.sudoku.engine.SudokuEngine;

public class SudokuGui {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> input = new ArrayList<String>();
		Map<Point, Integer> presetDigits = new HashMap<Point, Integer>();
		for (int y = 0; y < 9; y++) {
			System.out.println(MessageFormat.format("Input line #{0} as 9 digits (0 for blank)", y + 1));
			String inputLine = br.readLine();
			if (!inputLine.matches("^\\d{9}$")) {
				System.out.println("Invalid input, application will exit");
				return;
			}
			for (int x = 0; x < 9; x++) {
				int digit = Character.getNumericValue(inputLine.charAt(x));
				if (digit != 0) {
					presetDigits.put(new Point(x, y), digit);
				}
			}
			input.add(inputLine);
		}
		br.close();
		System.out.println(input);
		SudokuEngine engine = new SudokuEngine(presetDigits);
		Set<Solution> solutions = engine.solve();
		int solutionIndex = 1;
		for (Solution solution : solutions) {
			System.out.println(MessageFormat.format("Solution #{0}", solutionIndex++));
			System.out.println(solution.toString());
		}
		if (solutions.size() == 0) {
			System.out.println("The sudoku is invalid and has no consistent answer");
		}
	}

}
