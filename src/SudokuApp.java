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
   public static final Color OPEN_CELL_BGCOLOR1 = Color.YELLOW;
   public static final Color OPEN_CELL_BGCOLOR2 = Color.BLUE;
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
   private int[][] puzzle = { 
      {5, 3, 4, 6, 7, 8, 9, 1, 2},
      {6, 7, 2, 1, 9, 5, 3, 4, 8},
      {1, 9, 8, 3, 4, 2, 5, 6, 7},
      {8, 5, 9, 7, 6, 1, 4, 2, 3},
      {4, 2, 6, 8, 5, 3, 7, 9, 1},
      {7, 1, 3, 9, 2, 4, 8, 5, 6},
      {9, 6, 1, 5, 3, 7, 2, 8, 4},
      {2, 8, 7, 4, 1, 9, 6, 3, 5},
      {3, 4, 5, 2, 8, 6, 1, 7, 9} };
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

   static class difficultyapp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         System.exit(0);
      }
   }

   class restartApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         dispose();
         new SudokuApp("EASY", 4);
      }
   }

   private class DifficultyMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "easy":
				// ketika level easy dipilih, maka difficultyLevelnya 5
				// panggil method dispose untuk menutup window game sudoku yang sebelumnya
				dispose();
				new SudokuApp("EASY", 4);
				break;
			case "Medium":
				// ketika level medium dipilih, maka difficultyLevelnya 7
				// panggil method dispose untuk menutup window game sudoku yang sebelumnya
				dispose();
				new SudokuApp("MEDIUM", 2);
				break;
			case "Hard":
				// ketika level hard dipilih, maka difficultyLevelnya 8
				// panggil method dispose untuk menutup window game sudoku yang sebelumnya
				dispose();
				new SudokuApp("HARD", 1);
				break;
			default:
         dispose();
         new SudokuApp("EASY", 4);
         break;
			}
		}
	}

   // class aboutus implements ActionListener {
   //    public void actionPerformed(ActionEvent e) {
   //       JOptionPane.showMessageDialog(null, "tentang Kami");
   //    }
   // }

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
      JMenu difficulty = new JMenu("difficulty");
      JMenuItem easy = new JMenuItem("Easy");
      easy.addActionListener(new DifficultyMenuListener());
      difficulty.add(easy);
      JMenuItem medium = new JMenuItem("Medium");
      medium.addActionListener(new DifficultyMenuListener());
      difficulty.add(medium);
      JMenuItem hard = new JMenuItem("Hard");
      hard.addActionListener(new DifficultyMenuListener());
      difficulty.add(hard);
      JMenu help = new JMenu("help");
      JMenu about = new JMenu("about");
      JMenuItem aboutus = new JMenuItem("about us");
      aboutus.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Tim Kita");
         }
      });
      about.add(aboutus);

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
      menubar.add(difficulty);
      menubar.add(help);
      menubar.add(about);
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
      // int[][] tempRow;
      // for (int i = 0; i < 9; i++) {
      //    for (int i = 0; i < 9; i++) {
      //       int rowNum = (rd.nextInt(GRID_SIZE));
      //       // utk i=0. newRow = 1/9*3 = 0
      //       // utk i=1. newRow = 2/9*3 = 0
      //       int newRow = (rowNum/GRID_SIZE) * SUBGRID_SIZE;
      //       // tukar baris
      //       // utk i=0. swap baris index 1 dan 0
      //       // utk i=1. swap baris index 2 dan 0 (yg tdnya baris 1)
      //       tempRow = puzzle[rowNum];
      //       puzzle[rowNum] = puzzle[newRow];
      //       puzzle[newRow] = tempRow;
      //       if()
      //    }
      // }

      // Untuk menampilkan grid sudoku
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            tfCells[row][col] = new JTextField();
            cp.add(tfCells[row][col]);

            if(((row>=0 && row<=2)&&(col>=0 && col<=2))||((row>=0 && row<=2)&&(col>=6 && col<=8))||((row>=3 && row<=5)&&(col>=3 && col<=5))||((row>=6 && row<=8)&&(col>=0 && col<=2))||((row>=6 && row<=8)&&(col>=6 && col<=8))){
					tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR1);
				}
				else{
					tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR2);
            }
            
            if (masks[row][col]) {
               tfCells[row][col].setText("");
               tfCells[row][col].setEditable(true);
               tfCells[row][col].addActionListener(listener);

            } else {
               tfCells[row][col].setText(puzzle[row][col] + "");
               tfCells[row][col].setEditable(false);
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
  
            if (masks[3][0] == false && masks[3][1] == false && masks[3][2] == false && masks[3][3] == false
                  && masks[3][4] == false && masks[3][5] == false && masks[3][6] == false && masks[3][7] == false
                  && masks[3][8] == false) {
               for (int row1 = 3; row1 < 4; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[4][0] == false && masks[4][1] == false && masks[4][2] == false && masks[4][3] == false
                  && masks[4][4] == false && masks[4][5] == false && masks[4][6] == false && masks[4][7] == false
                  && masks[4][8] == false) {
               for (int row1 = 4; row1 < 5; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[5][0] == false && masks[5][1] == false && masks[5][2] == false && masks[5][3] == false
                  && masks[5][4] == false && masks[5][5] == false && masks[5][6] == false && masks[5][7] == false
                  && masks[5][8] == false) {
               for (int row1 = 5; row1 < 6; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[6][0] == false && masks[6][1] == false && masks[6][2] == false && masks[6][3] == false
                  && masks[6][4] == false && masks[6][5] == false && masks[6][6] == false && masks[6][7] == false
                  && masks[6][8] == false) {
               for (int row1 = 6; row1 < 7; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[7][0] == false && masks[7][1] == false && masks[7][2] == false && masks[7][3] == false
                  && masks[7][4] == false && masks[7][5] == false && masks[7][6] == false && masks[7][7] == false
                  && masks[7][8] == false) {
               for (int row1 = 7; row1 < 8; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[8][0] == false && masks[8][1] == false && masks[8][2] == false && masks[8][3] == false
                  && masks[8][4] == false && masks[8][5] == false && masks[8][6] == false && masks[8][7] == false
                  && masks[8][8] == false) {
               for (int row1 = 8; row1 < 9; row1++) {
                  for (int col1 = 0; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][0] == false && masks[1][0] == false && masks[2][0] == false && masks[3][0] == false
                  && masks[4][0] == false && masks[5][0] == false && masks[6][0] == false && masks[7][0] == false
                  && masks[8][0] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 0; col1 < 1; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][1] == false && masks[1][1] == false && masks[2][1] == false && masks[3][1] == false
                  && masks[4][1] == false && masks[5][1] == false && masks[6][1] == false && masks[7][1] == false
                  && masks[8][1] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 1; col1 < 2; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][2] == false && masks[1][2] == false && masks[2][2] == false && masks[3][2] == false
                  && masks[4][2] == false && masks[5][2] == false && masks[6][2] == false && masks[7][2] == false
                  && masks[8][2] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 2; col1 < 3; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][3] == false && masks[1][3] == false && masks[2][3] == false && masks[3][3] == false
                  && masks[4][3] == false && masks[5][3] == false && masks[6][3] == false && masks[7][3] == false
                  && masks[8][3] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 3; col1 < 4; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][4] == false && masks[1][4] == false && masks[2][4] == false && masks[3][4] == false
                  && masks[4][4] == false && masks[5][4] == false && masks[6][4] == false && masks[7][4] == false
                  && masks[8][4] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 4; col1 < 5; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][5] == false && masks[1][5] == false && masks[2][5] == false && masks[3][5] == false
                  && masks[4][5] == false && masks[5][5] == false && masks[6][5] == false && masks[7][5] == false
                  && masks[8][5] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 5; col1 < 6; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][6] == false && masks[1][6] == false && masks[2][6] == false && masks[3][6] == false
                  && masks[4][6] == false && masks[5][6] == false && masks[6][6] == false && masks[7][6] == false
                  && masks[8][6] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 6; col1 < 7; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][7] == false && masks[1][7] == false && masks[2][7] == false && masks[3][7] == false
                  && masks[4][7] == false && masks[5][7] == false && masks[6][7] == false && masks[7][7] == false
                  && masks[8][7] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 7; col1 < 8; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
            if (masks[0][8] == false && masks[1][8] == false && masks[2][8] == false && masks[3][8] == false
                  && masks[4][8] == false && masks[5][8] == false && masks[6][8] == false && masks[7][8] == false
                  && masks[8][8] == false) {
               for (int row1 = 0; row1 < 9; row1++) {
                  for (int col1 = 8; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                  }
               }
            }
  
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
  
            if (masks[0][3] == false && masks[0][4] == false && masks[0][5] == false && masks[1][3] == false
                  && masks[1][4] == false && masks[1][5] == false && masks[2][3] == false && masks[2][4] == false
                  && masks[2][5] == false) {
               for (int row1 = 0; row1 < 3; row1++) {
                  for (int col1 = 3; col1 < 6; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[0][6] == false && masks[0][7] == false && masks[0][8] == false && masks[1][6] == false
                  && masks[1][7] == false && masks[1][8] == false && masks[2][6] == false && masks[2][7] == false
                  && masks[2][8] == false) {
               for (int row1 = 0; row1 < 3; row1++) {
                  for (int col1 = 6; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[3][0] == false && masks[3][1] == false && masks[3][2] == false && masks[4][0] == false
                  && masks[4][1] == false && masks[4][2] == false && masks[5][0] == false && masks[5][1] == false
                  && masks[5][2] == false) {
               for (int row1 = 3; row1 < 6; row1++) {
                  for (int col1 = 0; col1 < 3; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[3][3] == false && masks[3][4] == false && masks[3][5] == false && masks[4][3] == false
                  && masks[4][4] == false && masks[4][5] == false && masks[5][3] == false && masks[5][4] == false
                  && masks[5][5] == false) {
               for (int row1 = 3; row1 < 6; row1++) {
                  for (int col1 = 3; col1 < 6; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[3][6] == false && masks[3][7] == false && masks[3][8] == false && masks[4][6] == false
                  && masks[4][7] == false && masks[4][8] == false && masks[5][6] == false && masks[5][7] == false
                  && masks[5][8] == false) {
               for (int row1 = 3; row1 < 6; row1++) {
                  for (int col1 = 6; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[6][0] == false && masks[6][1] == false && masks[6][2] == false && masks[7][0] == false
                  && masks[7][1] == false && masks[7][2] == false && masks[8][0] == false && masks[8][1] == false
                  && masks[8][2] == false) {
               for (int row1 = 6; row1 < 9; row1++) {
                  for (int col1 = 0; col1 < 3; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[6][3] == false && masks[6][4] == false && masks[6][5] == false && masks[7][3] == false
                  && masks[7][4] == false && masks[7][5] == false && masks[8][3] == false && masks[8][4] == false
                  && masks[8][5] == false) {
               for (int row1 = 6; row1 < 9; row1++) {
                  for (int col1 = 3; col1 < 6; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
            if (masks[6][6] == false && masks[6][7] == false && masks[6][8] == false && masks[7][6] == false
                  && masks[7][7] == false && masks[7][8] == false && masks[8][6] == false && masks[8][7] == false
                  && masks[8][8] == false) {
               for (int row1 = 6; row1 < 9; row1++) {
                  for (int col1 = 6; col1 < 9; col1++) {
                     tfCells[row1][col1].setBackground(Color.GRAY);
                     tfCells[row1][col1].setEditable(false);
                  }
               }
            }
  
         }
      }
   }
}