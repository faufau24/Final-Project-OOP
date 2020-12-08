# Sudoku
This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran. 

[Challenge Guidelines](challenge-guideline.md)

Game Sudoku adalah game logika berbasis teka teki dengan penempatan nomor dan mengisi kolom yang kosong berukuran 9x9 dengan angka yang belum tertera

## Credits
|  NPM          |             Name           |
| ------------- | ---------------------------|
| 140810190011  | Muhammad Rafiq 'Abdillah   |
| 140810190053  | Aidil Fitra                |
| 140810190077  | Muhammad Alwan Fauzi       |

## Change log
- **[Sprint Planning](changelog/sprint-planning.md) - (18 November 2020)** 
   - Membagi tugas kelompok
   - Mengerjakan Sprint 1

- **[Sprint 1](changelog/sprint-1.md) - (18 November 2020 x 24 November 2020)** 
   - Membuat grid 9x9
   - Membuat grid 3x3
   - Membuat grid berisi angka

- **[Sprint 2](changelog/sprint-2.md) - (25 November 2020 x 01 Desember 2020)** 
   - Memasking angka random
   - Membuat tingkat kesulitan
   - Mengecek inputan
   
- **[Sprint 3](changelog/sprint-3.md) - (02 Desember 2020 x 08 Desember 2020)** 
   - Highlight grid
   - Melanjutkan cek inputan
   - Mengecek Apakah semua inputan telah terisi
   - Menambahkan Menu Exit
   - Membuat button Reset/Restart
   - Membuat button tingkat kesulitan
   - Membuat button help
   - Membuat button About Us

## Running The App
- Compile Sudoku.java
- Use command 'java Sudoku easy' for easy dificulty
- Use command 'java Sudoku medium' for medium dificulty
- Use command 'java Sudoku hard' for hard dificulty


## Classes Used
1. Kelas Puzzle merupakan class untuk mengacak isi angka pada sudoku
2. Kelas Inputlistener merupakan class yang berisi action listener untuk set difficulties pada Menu Bar dari Sudoku
3. Kelas Sudoku berfungsi untuk menampilkan UI(tampilan) dan isi serta merupakan main class dari sudoku

![Mockup](/images/UML.png)

## Notable Assumption and Design App Details

![Mockup Aplikasi](https://telegra.ph/file/32506ba215122f09205ce.jpg)

- Tampilan interface Sudoku akan muncul (grid 9 x 9 dengan subgrid 3 x 3 beserta menu dan tampilan lainnya)
- User dapat melakukan input angka terhadap grid yang editable sesuai dengan level, dimana semakin sulit levelnya maka semakin banyak pula grid/tile yang harus diisi
- Klik enter untuk memasukkan angka sekaligus memeriksa apakah inputan user sudah benar dan sesuai
- Jika sesuai, maka inputan user akan berwarna hijau. Namun jika sebaliknya, akan berwarna merah dan tile yang berisi angka yang sama dengan inputan user dalam 1 subgrid akan dihighlight dengan warna merah
- Jumlah 'Cells remaining' akan terus berkurang beriringan dengan jumlah inputan user yang sesuai dengan jawaban
- Dalam permainan, user bisa mengakses pilihan menu (File dan Options). Pada menu File, user dapat restart game, reset jawaban, dan exit (keluar). Sedangkan pada menu Options, user dapat mengganti level permainan (Easy, Medium, Hard)
