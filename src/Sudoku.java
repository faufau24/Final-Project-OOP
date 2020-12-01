import javax.lang.model.util.ElementScanner6;

public class Sudoku {
    public static void main(String[] args) {
        if (args[0].equals("easy")) {
            SudokuBoard sudokuBoard = new SudokuBoard("EASY", 4);
        } else if (args[0].equals("medium")) {
            SudokuBoard sudokuBoard = new SudokuBoard("MEDIUM", 2);
        } else if (args[0].equals("hard")) {
            SudokuBoard sudokuBoard = new SudokuBoard("HARD", 1);
        }
    }
}