package nakomis.sudoku.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nakomis.sudoku.engine.SudokuEngine;

public class SudokuGui {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> input = new ArrayList<String>();
		Map<Map.Entry<Integer,Integer>, Integer> presetDigits = new HashMap<Map.Entry<Integer,Integer>, Integer>();
		for (int i = 0; i < 9; i++) {
			System.out.println(MessageFormat.format("Input line #{0} as 9 digits (0 for blank)", i + 1));
			String inputLine = br.readLine();
			if (!inputLine.matches("^\\d{9}$")) {
				System.out.println("Invalid input, application will exit");
				return;
			}
			for (int j = 0; j < 9; j++) {
				int digit = Character.getNumericValue(inputLine.charAt(j));
				presetDigits.put(new AbstractMap.SimpleEntry<Integer, Integer>(j, i), digit);
			}
			input.add(inputLine);
		}
		br.close();
		System.out.println(input);
		SudokuEngine engine = new SudokuEngine(presetDigits);
		engine.solve();
	}

}
