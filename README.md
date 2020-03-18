# Tugas KB Kelas F
Oleh
 _Clement Prolifel Priyatama (0511184000013)_

----------------------------------------------------------------
## Soal
* [8 Puzzle](#8-puzzle)
  * [BFS](#BFS)
  * [DFS](#DFS)
  * [IDS](#IDS)
  * [Heuristik 1](#Heuristik-1)
  * [Heuristik 2](#Heuristik-2)
* [8 Queen](#8-Queen)
  * [Normal](#Normal)
  * [Hill Climbing](#Hill-Climbing)
* [Minimax Tictactoe](#Minimax-Tictactoe)
* [4 Queen CSP](#4-Queen-CSP)
----------------------------------------------------------------

### 8 Puzzle
#### BFS
  > _Source Code main.py:_ [main](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20BFS/main.py)
  
  > _Source Code BFS.py:_ [BFS](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20BFS/BFS.py)
  
  > _Source Code Puzzle_class.py:_ [Puzzle_class](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20BFS/Puzzle_class.py)
  
  BFS (_Breadth-First Search_) merupakan algoritma untuk _traversing_ atau _searching_ tree dan hal ini dimulai dari _root_.
  
  Ilustrasi nya adalah seperti berikut:
  ![Gambar BFS](https://upload.wikimedia.org/wikipedia/commons/4/46/Animated_BFS.gif)
  
  Pada aplikasi yang saya buat, saya menggunakan referensi dari [sini](https://github.com/NiloofarShahbaz/8-puzzle-search-implementation/tree/60776b1cb6e59c1510d6d1b0ae7d10ba6b3a8df2). Dimana memiliki 5 files python, yang terdiri dari Class `Astar_search`, `BFS_search`,`RBFS_search`, dan `puzzle`, dan satu program `main`.
  
  Pada kali ini yang saya gunakan hanya Class BFS, dengan sedikit modifikasi pada `main.py`. 
  
  Target state atau _goal state_ untuk 8 Puzzle ini adalah 
  
  ![gambar state](https://miro.medium.com/max/351/1*IQ4oYMH3SCAriifZMdZA9w.png)
  
  Untuk memudahkan perhitungan komputer, state akan disederhanakan menjadi sebuah _array_. Maka array _goal state_ adalah `[1, 2, 3, 4, 5, 6, 7, 8, 0]`. Oleh karena itu, `goal_state` pada class `Puzzle_class.py` berisi `[1, 2, 3, 4, 5, 6, 7, 8, 0]`.
  
  Berikut ini adalah method yang terdapat dalam class `Puzzle_class.py` beserta penjelasan singkatnya:
  1. `generate_heuristic()` : Fungsi ini digunakan untuk menyelesaikan permasalahan ketika `step` yang ingin diambil terlalu banyak langkah dan diperlukan penyingkatan langkah dan memperpendek _time executed_, sehingga fungsi `generate_heuristic()` sangat diperlukan.
  2. `goal_test()` : Fungsi ini digunakan untuk memeriksa apakah _state-child_ sudah sama dengan `goal_state`. Jika berbeda, akan melakukan return value `false`.
  3. `find_legal_actions()` : Fungsi ini digunakan untuk melakukan filter arah mana yang bisa dipenuhi berdasarkan koordinat kotak 0. Jika koordinat 0 berada di pojok 8puzzle, maka ada arah yang tidak bisa dipenuhi. `i` menunjukkan kolom, sedangkan `j` menunjukkan baris.
  4. `generate_child()` : Fungsi ini digunakan untuk membuat _child_ pada queue/tree yang ingin dicari. Kemudian mencari state baru ketika action yang ada valid (lewat fungsi `find_legal_actions()`).
  5. `find_solution()` : Fungsi ini digunakan untuk menyimpan solusi dari semua _moves_ yang ada. Dimulai dari _parent_ hingga _leaf_ paling bawah.

  Pada `BFS.py`, saya menggunakan berbagai method yang ada di `Puzzle_class.py` untuk bisa mencari solusi. Dimulai dengan membuat node, queue, dan array `explored` baru. Kemudian akan terus dilakukan perulangan hingga queue kosong, dan pada saat itu dimulai untuk membuat child satu-persatu secara rekursif. Setelah itu, langkah terakhir adalah melakukan pemeriksaan apakah string `goal_state == child_state`. Jika sama, maka sebuah array `solution` dari method `find_solution()` akan diprint, dan semua queue dilepas. 
