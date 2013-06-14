package nakomis.sudoku.gui;

import nakomis.sudoku.engine.SudokuEngine;

public class SudokuGui {

	public static void main(String[] args) {
		SudokuEngine engine = new SudokuEngine();
		engine.solve();
	}

}
