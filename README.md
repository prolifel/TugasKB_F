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

  Pada `BFS.py`, disana ada import dari `Puzzle_class` dan `queue`. Hal ini dilakukan karena semua method untuk melakukan `generate_child` dan memeriksa apakah _array_ dari 
