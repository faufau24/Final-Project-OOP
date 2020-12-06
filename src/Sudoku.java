public class Sudoku {
    public static void main(String[] args) {
        // SudokuApp s1 = new SudokuApp("EASY", 4);
        if (args[0].equals("easy")) {
            SudokuApp sudokuApp = new SudokuApp("EASY", 4);
        } else if (args[0].equals("medium")) {
            SudokuApp sudokuApp = new SudokuApp("MEDIUM", 2);
        } else if (args[0].equals("hard")) {
            SudokuApp sudokuApp = new SudokuApp("HARD", 1);
        }
    }
}
