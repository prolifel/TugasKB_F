# Tugas Kecerdasan Buatan Kelas F
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
  * [Uninformed Search](#Uninformed-Search)
  * [Hill Climbing](#Hill-Climbing)
* [Minimax Tictactoe](#Minimax-Tictactoe)
* [4 Queen CSP](#4-Queen-CSP)
----------------------------------------------------------------

### 8 Puzzle
#### BFS
  > _Source Code main.py:_ [main](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20BFS/main.py)
  
  > _Source Code BFS.py:_ [BFS](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20BFS/BFS.py)
  
  > _Source Code Puzzle_class.py:_ [Puzzle_class](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20BFS/Puzzle_class.py)
  
  BFS (_Breadth-First Search_) merupakan algoritma untuk _traversing_ atau _searching_ tree dan hal ini dimulai dari _root_. Dan aplikasinya adalah memeriksa secara luas ke tiap kemungkinan yang ada pada kondisi sekarang. Maka dari itu, BFS menggunakan sistem FIFO (First In, First Out), sehingga pencarian hanya berdasarkan level per level ketika ada _path_ yang mungkin dilalui.
  
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


#### DFS

  > _Source Code driver.py:_ [driver](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20DFS/driver.py)
  
  > _Source Code state.py:_ [state](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20DFS/state.py)
  
  > _Hasil DFS: output.txt:_ [Puzzle_class](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20DFS/output.txt)
  
 DFS (_Depth-First Search_) merupakan algoritma untuk _traversing_ atau _searching_ tree dan hal ini dimulai dari _root_. Dan aplikasinya adalah memeriksa secara dalam _path_ secara satu satu. Maka dari itu, DFS menggunakan sistem LIFO (Last In First Out) untuk bisa memeriksa tree/graph secara menyeluruh dan dalam.
 
 Ilustrasinya adalah sebagai berikut:
 
 ![dfs_illu](https://codeforces.com/predownloaded/8d/be/8dbe5d89e58b67f3d8e4d8e0e8eb3358ba921b28.png)
 
 Untuk 8 Puzzle solver dengan algoritma DFS, saya menggunakan referensi dari [sini](https://github.com/speix/8-puzzle-solver) dengan sedikit modifikasi karena saya tidak memerlukan algoritma BFS, AST, dan IDA.
 
 Hampir sama dengan BFS, Target state atau _goal state_ untuk 8 Puzzle ini adalah 
  
  ![gambar state](https://miro.medium.com/max/351/1*IQ4oYMH3SCAriifZMdZA9w.png)
  
  Berikut ini adalah penjelasan dari tiap fungsi yang terdapat dalam `driver.py`:
  1. `dfs()` : Fungsi ini merupakan implementasi dari algoritma DFS sendiri, dengan menggunakan _stack_ didalamnya dan menandai node yang telah dikunjungi dengan variabel `explored`. 
  2. `expand()` : Fungsi ini digunakan untuk menjumlahkan berapa banyak nodes yang telah diperluas (_nodes expanded_). Hasil dari _nodes expanded_ akan diprint oleh fungsi `export()`.
  3. `move()` : Fungsi ini digunakan untuk memindahkan posisi dari angka di board, dengan movement _Up_, _Down_, _Left_, _Right_ dan juga untuk melakukan update state baru ketika sudah melakukan giliran.
  4. `backtrace()` : Fungsi ini dijalankan ketika algoritma DFS telah selesai. Tujuannya adalah mengidentifikasi moves apa saja yang telah dilakukan, menamainya, kemudian memasukkan list moves tadi untuk kemudian dilakukan print oleh fungsi `export()`.
  5. `export()` : Fungsi ini untuk membuat file `output.txt`, yang berisi "Jalan/path", "Nodes yang diexpand", dan "Waktu Algoritma berjalan".
  6. `read()` : Fungsi ini digunakan untuk memisah state awal (pada argumen saat menjalankan program), menyimpan state awal di `initial_state`, mencari panjang board, dan mencari ukuran board.
  7. `main()` : Fungsi ini digunakan untuk run aplikasi dari awal dengan langkah-langkah sebagai berikut:
    a. Memisahkan argumen `algoritm` yang ingin dipakai dan `board` yang ingin diproses.
    b. Menghitung waktu ketika program dimulai
    c. Memproses `initial_state` dengan algoritma yang dipakai
    d. Menghitung waktu ketika fungsi `dfs()` selesai memproses `initial_state`
    3. Menjalankan fungsi `export()` dengan argumen hasil fungsi `dfs()` dan selisih waktu fungsi `dfs()` berjalan.
  
  File `state.py` digunakan sebagai class `State` untuk object yang akan digunakan pada Stack yang digunakan oleh algoritma DFS. 
  
  
#### IDS

  > _Source Code IDS.py:_ [IDS](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Puzzle%20IDS/IDS.py)

 IDS (_Iterative Deepening Search_) merupakan algoritma lanjutan dari DFS dengan menerapkan konsep yang sama dengan DFS. Tetapi, _improvement_-nya adalah IDS tidak mencari semua jalur seperti DFS, tetapi dicari secara _iterative_ berdasarkan level.
 
 Ilustrasinya adalah sebagai berikut:
 ![illu_IDS](https://camo.githubusercontent.com/94d997ac6ef676d3c71d7137c6958086465e22e7/687474703a2f2f7777772e686f77326578616d706c65732e636f6d2f6172746966696369616c2d696e74656c6c6967656e63652f696d616765732f4974657261746976652d44657074682d46697273742d5365617263682e676966)
 
 Saya mendapatkan program 8 puzzle solver dengan algoritma IDS dari [sini](https://repl.it/repls/HuskyEnviousTasks) dengan sedikit modifikasi penambahan _running time_.
 
 Berikut ini adalah penjelasan dari tiap fungsi yang terdapat dalam `IDS.py`:
 1. `move()` : Fungsi ini digunakan untuk memindahkan angka di board sesuai dengan algoritma IDS.
 2. `ids()` : Fungsi ini sebagai implementasi algoritma IDS dengan menggunakan stack, dan diperiksa tiap level hingga bertemu `flag = true`.
 
----------------------------------------------------------------
### 8 Queens

8 Queens adalah permasalahan bagaimna bisa menempatkan 8 Ratu didalam kotak catur 8x8 dengan kondisi tidak ada salah satu dari Ratu tersebut yang saling menyerang, baik secara baris, kolom, maupun diagonal. Secara umum, 8 Queens ini adalah permasalahan N Queens dengan menempatkan N Ratu ke dalam NxN kotak catur.

![kondisi](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/Assets/8q-bfs/Artboard%201-%204.jpg?raw=true)

#### Uninformed Search

  > _Source Code main.py:_ [main](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Queens%20BFS/main.py)
  > _Source Code board.py:_ [board](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/8%20Queens%20BFS/board.py)
  
  Pada permasalahan 8 Queens dengan metode Uninformed Search, saya menggunakan algoritma _Breadth-First Search_ (BFS).
  
  Berikut ini penjelasan mengenai setiap fungsi pada `board.py`:
  1. `__init__()` : Sebagai _constructor class_ `board` yang berisi `size` / ukuran dari board
  2. `solve_bfs()` : Digunakan sebagai implementasi dari algoritma BFS.
  3. `conflict()` : Digunakan apabila salah satu Ratu saling menyerang salah satu Ratu yang lain.
  4. `print()` : Digunakan sebagai _output_ board 8 Queens.
  
  Berdasarkan artikel [ini](https://doi.org/10.1145/185009.185019), dapat diketahui bahwa:
  > It is known that there are altogether 92 solutions. and one example is
  
  Hal ini sesuai dengan hasil program saya, yaitu:
  
  ![hasil_8q_bfs](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/Assets/8q-bfs/Annotation%202020-04-12%20122128.png?raw=true)
  

#### Hill Climbing
