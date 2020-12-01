import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SudokuBoard implements ActionListener {

    JFrame frame = new JFrame();
    JTextField grid[][] = new JTextField[9][9];
    private static int board[][] = new int[9][9];
    Random r = new Random();
    JPanel title_panel = new JPanel();
    JLabel textfield = new JLabel();
    private static int[][] number = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

    SudokuBoard(String title, Integer difficulty) {

        frame.setTitle("SUDOKU [DIFFICULTY : " + title + "]");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setVisible(true);
        frame.setBounds(100, 100, 410, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textfield.setBackground(Color.GRAY);
        textfield.setFont(new Font("Tw Cen", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("SUDOKU");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        title_panel.add(textfield);

        frame.add(title_panel, BorderLayout.NORTH);

        int h = 12, w = 105, hi = 39, wi = 38, acak1 = 0, acak2 = 0;
        int mask = 9 * difficulty;

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0)
                w += 13;

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0)
                    h += 11;

                grid[i][j] = new JTextField();
                grid[i][j].setColumns(10);
                grid[i][j].setBounds(h, w, 38, 37);
                frame.getContentPane().add(grid[i][j]);

                h += hi;
            }
            h = 12;
            w += wi;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 22));
                grid[i][j].setBackground(Color.WHITE);
                grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                grid[i][j].setEditable(true);
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                number[i - 1][j - 1] = 1 + ((i * 10 / 3) + j) % (9);
            }
        }

        for (int i = 0; i < mask; i++) {
            acak1 = r.nextInt(9);
            acak2 = r.nextInt(9);
            if (grid[acak1][acak2].getText().isEmpty() == true) {
                grid[acak1][acak2].setText(Integer.toString(number[acak1][acak2]));
                grid[acak1][acak2].setEditable(false);
                grid[acak1][acak2].setBackground(Color.GRAY);
            } else {
                i--;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == grid[i][j]) {
                    grid[i][j].setText("");
                    if (Integer.parseInt(grid[i][j].getText()) == number[i][j]) {
                        grid[i][j].setForeground(Color.GREEN);
                    }
                    grid[i][j].setForeground(Color.RED);
                }
            }
        }
    }

}
