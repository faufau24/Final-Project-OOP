import java.awt.*;
import java.awt.event.*;
import java.util.Random;
// import java.lang.Math;

import javax.swing.*;

public class SudokuApp extends JFrame {

   public static final int GRID_SIZE = 9; // besar board 9x9
   public static final int SUBGRID_SIZE = 3; // besar sub-grid 3x3

   // constanta untuk UI
   public static final int CELL_SIZE = 60;
   public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
   public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

   // aturan untuk board
   public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
   // public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0); // RGB
   // public static final Color OPEN_CELL_TEXT_NO = Color.RED;
   public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
   public static final Color CLOSED_CELL_TEXT = Color.BLACK;
   public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

   // tampilan grid akan disimpan di JTextField
   private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
   public static final int min = 1;
   public static final int max = 9;

   // puzzle untuk sudoku
   private int[][] puzzle = { { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
         { 6, 7, 2, 1, 9, 5, 3, 4, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
         { 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
         { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
   // For testing, open only 2 cells.

   JLabel label = new JLabel();

   Random rd = new Random();
   // boolean rd_bool = boolRandom.nextBoolean();
   // rd_bool = true;
   private boolean[][] masks = { { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true },
         { true, true, true, true, true, true, true, true, true } };

   JTextArea textKondisi = new JTextArea();

   static class exitApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         System.exit(0);
      }
   }

   static class restartApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // System.exit(0);
      }
   }

   static class clearApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // System.exit(0);
      }
   }

   public SudokuApp(String difName, int difficulty) {
      initiation(difName);
      createBoard(difficulty);
   }

   private void initiation(String difName) {
      setLayout(new BorderLayout());

      JMenuBar menubar = new JMenuBar();
      JMenu file = new JMenu("File");

      JMenuItem restart = new JMenuItem("Restart");
      restart.addActionListener(new restartApp());
      file.add(restart);

      JMenuItem clear = new JMenuItem("Clear");
      clear.addActionListener(new clearApp());
      file.add(clear);

      JMenuItem exit = new JMenuItem("Exit");
      exit.addActionListener(new exitApp());
      file.add(exit);

      menubar.add(file);
      setJMenuBar(menubar);

      // Container judul = getContentPane();
      label.setText("SUDOKU");
      label.setFont(new Font("Tw Cen", Font.BOLD, 55));
      label.setHorizontalAlignment(JLabel.CENTER);
      add(label, BorderLayout.NORTH);

      JPanel Condition = new JPanel();
      Condition.setLayout(new GridLayout(1, 3));
      JTextArea kondisi = new JTextArea("Kondisi: ");
      kondisi.setFont(new Font("Tw Cen", Font.ITALIC, 20));
      textKondisi.setFont(new Font("Tw Cen", Font.ITALIC, 20));
      Condition.add(kondisi);
      Condition.add(textKondisi);
      add(Condition, BorderLayout.SOUTH);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("SUDOKU [DIFFICULTY : " + difName + "]");
      setVisible(true);
   }

   private void createBoard(int difficulty) {
      // panel untuk sudoku
      JPanel cp = new JPanel();
      cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0));

      InputListener listener = new InputListener();

      // banyak yang di masking sesuai difficulty
      for (int i = 0; i < difficulty * 9; i++) {
         int acak1 = rd.nextInt(9);
         int acak2 = rd.nextInt(9);
         if (masks[acak1][acak2] == false) {
            i--;
         } else {
            masks[acak1][acak2] = false;
         }

      }

      // NANTI
      // DIUBAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH!
      for (int i = 1; i <= 9; i++) {
         for (int j = 1; j <= 9; j++) {
            puzzle[i - 1][j - 1] = 1 + ((i * 10 / 3) + j) % (9);
         }
      }

      // Untuk menampilkan grid sudoku
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            tfCells[row][col] = new JTextField();
            cp.add(tfCells[row][col]);

            if (masks[row][col]) {
               tfCells[row][col].setText("");
               tfCells[row][col].setEditable(true);
               tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
               tfCells[row][col].addActionListener(listener);

            } else {
               tfCells[row][col].setText(puzzle[row][col] + "");
               tfCells[row][col].setEditable(true);
               tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
               tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
            }
            tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
            tfCells[row][col].setFont(FONT_NUMBERS);
         }
      }

      cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      add(cp, BorderLayout.CENTER);
      pack();
   }

   private class InputListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {

         // variabel untuk menyimpan row dan col dari inputan
         int rowSelected = -1;
         int colSelected = -1;

         // Guntuk scan source
         JTextField source = (JTextField) e.getSource();

         boolean found = false;
         for (int row = 0; row < GRID_SIZE && !found; ++row) {
            for (int col = 0; col < GRID_SIZE && !found; ++col) {
               if (tfCells[row][col] == source) {
                  rowSelected = row;
                  colSelected = col;
                  found = true; // memberhentikan perulangan jika sudah bertemu row yang kosong
               }
            }
         }

         // mengecek apakah angka pada row sudah benar dan merubah warna background jika
         // salah atau benar
         String x = tfCells[rowSelected][colSelected].getText();
         int toCheckNumber = Integer.parseInt(x);
         for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
               if (toCheckNumber == puzzle[rowSelected][colSelected]) {
                  masks[rowSelected][colSelected] = false;
                  tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
                  tfCells[rowSelected][colSelected].setEditable(false);
               } else {
                  masks[rowSelected][colSelected] = true;
                  tfCells[rowSelected][colSelected].setBackground(Color.RED);
               }
            }
         }
         checkGrid();
      }

      private void checkGrid() {
         // untuk mengecek apakah sudah semua cell terisi
         int counter = 0;
         for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
               if (masks[row][col] == false) {
                  counter++;
                  highlightGrid();
               }
            }
         }
         if (counter == 81) {
            label.setText("YOU WIN");
            textKondisi.setText("Selesai");
         } else {
            textKondisi.setText("Belum Selesai");
         }
      }
   }

   private void highlightGrid() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (masks[0][0] == false && masks[0][1] == false && masks[0][2] == false && masks[1][0] == false
                  && masks[1][1] == false && masks[1][2] == false && masks[2][0] == false && masks[2][1] == false
                  && masks[2][2] == false) {
               for (int row1 = 0; row1 < 3; row1++) {
                  for (int col1 = 0; col1 < 3; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }

            if (masks[0][0] == false && masks[0][1] == false && masks[0][2] == false && masks[0][3] == false
                  && masks[0][4] == false && masks[0][5] == false && masks[0][6] == false && masks[0][7] == false
                  && masks[0][8] == false) {
               for (int row1 = 0; row1 < 1; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }

            if (masks[1][0] == false && masks[1][1] == false && masks[1][2] == false && masks[1][3] == false
                  && masks[1][4] == false && masks[1][5] == false && masks[1][6] == false && masks[1][7] == false
                  && masks[1][8] == false) {
               for (int row1 = 1; row1 < 2; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }

            if (masks[2][0] == false && masks[2][1] == false && masks[2][2] == false && masks[2][3] == false
                  && masks[2][4] == false && masks[2][5] == false && masks[2][6] == false && masks[2][7] == false
                  && masks[2][8] == false) {
               for (int row1 = 2; row1 < 3; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
         }
      }
   }
}