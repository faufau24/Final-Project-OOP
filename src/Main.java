public static void main(String[] args) {
    Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
    System.out.println("Kotak Sudoku untuk dipecahkan");
    sudoku.display();
    
    // we try resolution
    if (sudoku.solve()) {
        System.out.println("Kotak sudah ku sudah dipecahkan dengan BT simple");
        sudoku.display();
    } else {
        System.out.println("Tidak dapat diselesaikan");
    }
}
