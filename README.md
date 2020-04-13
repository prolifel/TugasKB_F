# Tugas Kecerdasan Buatan Kelas F
Oleh
 _Clement Prolifel Priyatama (0511184000013)_

----------------------------------------------------------------
## Soal
* [8 Puzzle](#8-puzzle)
  * [BFS](#BFS)
  * [DFS](#DFS)
  * [IDS](#IDS)
  * [Heuristic 1](#Heuristik-1)
  * [Heuristic 2](#Heuristik-2)
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
  **Note**:
  Disini saya memakai kode Sabrina Jiang tanpa modifikasi. Kode tersebut bisa didapatkan [disini](https://github.com/Sabrina-Jiang/Hill-Climbing)
  
  Hill Climbing merupakan algoritma pencarian heuristik dengan optimisasi secara matematis. Disini, algoritma Hill Climbing tidak mencari solusi paling optimal, tetapi hanya mencari solusi yang masuk akal dari segi waktu. Pencarian heuristik adalah fungsi yang akan mengurutkan segala kemungkinan yang masuk akal dengan menggunakan _tree_ berdasarkan informasi yang ada. Hal tersebut membantu algoritma ini untuk memilih rute terbaik berdasarkan rute yang dapat dilalui.
  
  Berikut ini adalah ilustrasi dari algoritma Hill Climbing:
  
  ![HC](https://fileservice.slidewiki.org/media/images/25/4318.PNG)
  
  Dalam sebuah langkah-langkah sederhana, saya dapat mengartikan algoritma Hill Climbing sebagai berikut:
  1. Buat tree berdasarkan urutan random
  2. Tiap elemen pada array ditukar, kemudian dicari cost dari tiap kemungkinan
  3. Kemudian, _cost_ yang paling kecil dari cabang paling kiri, menjadi induk baru
  
  Dalam `hill_climbing.py` terdapat beberapa fungsi, yang terdiri dari:
  1. `get_num_of_conflict()` : Fungsi ini digunakan untuk menghitung konflik dari setiap Ratu dari kondisi papan catur sekarang yang kemudian menjadi _return value_ dari fungsi ini.
  2. `hill_climbing_first_choice()` : Fungsi ini sebagai implementasi dari langkah ketiga diatasm yaitu menemukan _cost_ yang paling kecil dari semua status yang ada.
  3. `prettyprint()` : Fungsi ini digunakan untuk print board. 'X' berarti Ratu dan '.' berarti tidak ada ratu.
  4. `Queens()` :  Fungsi digunakan sebagai prosedur utama dari algoritma Hill Climbing, yaitu menghitung konflik antar Ratu, dan jika konflik tersebut sama dengan nol, maka posisi/status Ratu tersebut sukses. Fungsi ini juga digunakan untuk menghitung _moving cost_ dari algoritma Hill Climbing, langkah yang akan diambil, dan waktu random start. Fungsi ini akan direkursif hingga dalam posisi/status sekarang sama dengan nol. _Return value_ dari fungsi ini adalah posisi/status sekarang.

----------------------------------------------------------------
### Minimax Tictactoe
 **Note**:
  Disini saya memakai kode Akira Wang tanpa modifikasi. Kode tersebut bisa didapatkan [disini](https://github.com/akiratwang/TicTacToe-AI-agent)
  
  Minimax merupakan algoritma _backtracking_ untuk memtukan sebuah keputusan terbaik untuk seorang _player_, dengan asumsi bahwa lawan main juga bermain dengan terbaik. Biasanya Minimax digunakan pada game yang pemainnya ada 2, seperti Tic Tac Toe, Backgammon, Mancala, Catur dll.
  
  Dalam Minimax, ada dua _player_ yang terdiri dari:
  1. Maximizer, yaitu yang mencoba mendapatkan skor tertinggi sebisa mungkin
  2. Minimizer, yaitu yang mencoba mendapatkan skor terendah sebisa mungkin
  
  Hal ini dapat diilustrasikan sebagai berikut:
  
  ![minimax](https://www.researchgate.net/publication/262672371/figure/fig1/AS:393455625883662@1470818539933/Game-tree-for-Tic-Tac-Toe-game-using-MiniMax-algorithm.png)
  
  Berikut ini pseudo code dari algorima Minimax:
  ```
  function minimax(node, depth, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞ 
        for each child of node do
            value := max(value, minimax(child, depth − 1, FALSE))
        return value
    else (* minimizing player *)
        value := +∞
        for each child of node do
            value := min(value, minimax(child, depth − 1, TRUE))
        return value
  ```
  
  Berikut ini penjelasan fungsi-fungsi pada file `algorithms.py`:
  1. `eval()`: Fungsi ini digunakan sebagai _flag_ apabila _Agent_ atau _Human_ yang menang atau tidak keduanya.
  2. `winner()`: Fungsi ini menyediakan _state_ menang. Selain itu, fungsi ini juga digunakan untuk memeriksa apakah player menang game.
  3. `game_over()`: Fungsi `game_over()` digunakan untuk memeriksa siapakah yang menang (_Agent_ atau _Human_) dengan memanggil fungsi `winener()` dengan _state_ sekarang.
  4. `blank_tiles()`: Dalam permainan Tictactoe, pasti terdapat kotak yang kosong. Oleh karena itu, fungsi ini digunakan untuk memeriksa apa saja kotak yang kosong. Kemudian, dilakukan _return_ list kotak yang kosong tadi.
  5. `valid_action()`: Fungsi ini digunakan untuk mencegah terjadinya input dobel pada satu kotak, dan memberikan _flag_ apabila kotak yang dituju dapat diisi 'X' atau 'O'.
  6. `apply_action()`: Hasil _flag_ dari fungsi `valid_action()` digunakan untuk mengisi 'X' atau 'O' pada kotak. 
  7. `minimax()`: Implementasi dari pseudo code Minimax diatas diterapkan dalam fungsi ini.
  8. `print_board()`: Fungsi ini berfungsi untuk melakukan _print_ kondisi board sekarang.
  9. `agent()`: _Agent_ adalah lawan main dari _Human_. Fungsi ini berisi perintah untuk memeriksa apakah game telah selesai (hal ini terjadi apabila _depth_ == 0 atau _flag_ bernilai _true_ setelah menjalankan fungsi `game_over()`), _print_ kondisi board sekarang, input langkah berikutnya dari _Agent_, dan untuk mengisi 'X' atau 'O' dengan memanggil `apply_action()` dengan argumen dari hasil pemanggilan fungsi `minimax()`.
  10. `human()`: Fungsi ini mirip dengan `agent()`, hanya saja berbeda di penggunaan `apply_action()`-nya. Setelah user memasukkan nomor kotak, nomor tersebut akan diperiksa dengan mencocokkan dengan _global variable_ ACTIONS untuk kemudian menghasilkan koordinat pada board.
  
  `algorithms.py` juga memiliki variabel global, antara lain:
  1. BOARD: Mendefinisikan kondisi board awal, yang kemudian diubah-ubah berdasarkan perintah pada fungsi-fungsi diatas.
  2. ACTIONS: Mendefinisikan koordinat pada board dalam bentuk array.
  3. HUMAN: Sebagai flag untuk menandai user (-1).
  4. AGENT: Sebagai flag untuk menandai _agent_ (-1).
  5. NONE: Menandakan 0 atau tidak terjadi apa-apa.
  6. BLANK: Spasi
  7. LINE: Batas tiap baris saat menggambar board.
  
  Pada file `main.py`, pada saat awal game berjalan, user diminta mau bermain sebagai 'X' atau 'O'. Apabila user memilih 'X', maka user bermain pertama, begitu pula sebaliknya. Kemudian, user akan diminta untuk memasukkan posisi _gaco-an_-nya ke dalam board dengan urutan sebagai berikut:
  
  ![minimaxsc2](https://github.com/prolifel/TugasKB_F/blob/master/Tugas/Assets/tictactoe/board.PNG?raw=true)
  
  Game dimainkan secara terus-menerus dengan memanggil secara bergantian fungsi `agent()` dan `human()` hingga _return value_ `game_over()` bernilai _false_. Game akan berakhir dengan memberikan _output_ **"YOU WIN!"** apabila user menang, **"You muppet, you've lost :("** apabila user kalah, atau **"DRAW!"** apabila game berakhir seri.

----------------------------------------------------------------
### 4 Queen CSP

4 Queens memiliki tipe permasalahan yang sama dengan 8 Queens, dimana ada 4 Ratu dalam sebuah papan catur 4x4 dengan kondisi keempat Ratu tidak boleh saling menyerang (secara horizontal, vertikal, maupun terdekat).

Berdasarkan On-Line Encyclopedia of Integer Sequences (OEIS) ([Sumber](https://oeis.org/A000170)), solusi yang memungkinkan bagi 4 Queens, adalah 2 kondisi, yaitu

![4q_sc](https://sadakurapati.files.wordpress.com/2013/12/n-queens1.png?w=584)

Penyelesaian 4 Queens dapat dilakukan dengan algoritma _Constraint Satisfaction Problems_ (CSP). CSP merupakan algoritma pendekatan untuk menyelesaikan suatu masalah dengan menemukan keadaan atau objek yang memenuhi sejumlah persyaratan atau kriteria. CSP merupakan algoritma _backtracking_ yang dioptimasi, sehingga _running time_ program lebih cepat dan memori yang digunakan lebih sedikit. Perbedaan CSP dengan _backtracking_ adalah pendefinisian _constraints_ dengan jelas. 

Untuk bisa menjalankan algoritma CSP, dibutuhkan tiga hal berikut:
1. Variabel: Sebuah permasalahan yang perlu untuk ditentukan nilainya atau _value_-nya
2. Domains: Berisi nilai-nilai (_values_) yang dapat ditetapkan ke variabel secara spesifik.
3. Constraints: _Constraints_ akan menandakan nilai apa saja yang terdapat dalam domain yang dapat digunakan pada kondisi yang ada.

Algoritma CSP biasa menggunakan _flowchart_ untuk dapat mengerti langkah-langkah lebih detil. Berikut ini adalah contoh _flowchart_ yang digunakan untuk mengelola jadwal kuliah:

![flowchart-csp]()

 **Note**:
  Disini saya memakai kode Theodore Tan tanpa modifikasi. Kode tersebut bisa didapatkan [disini](https://github.com/theodoretan/n-queens-CSP)
  
  
  Pada file `function.py`, terdapat beberapa kelas, yaitu:
  1. Board: Kelas ini digunakan agar sebuah objek _board_ dapat diketahui jejaknya apabila terdapat konflik antar Ratu dan juga digunakan untuk melakukan update _constraints_. 
     Kelas Board berisi beberapa fungsi, antara lain:
     a. `set_queen()`: Digunakan untuk mengatur sebuah Ratu di dalam board. Fungsi ini memakai beberapa input, antara lain koordinat dari board, dan _constraints_ setiap Ratu.
     b. `remove_queen()`: Berfungsi untuk menghapus sebuah Ratu di dalam board. Input pada fungsi ini sama dengan input pada `set_queen()`.
     c. `get_num_conflicts()`: Untuk mengambil konflik pada tiap koordinat dari papan catur.
  2. CSP: Kelas ini berfungsi agar sebuah objek CSP dapat menyimpan variabel, domains, dan constraints.
  3. Colours: Kelas ini berfungsi untuk memberikan warna pada terminal saat aplikasi dijalankan.
  
  Fungsi-fungsi yang terdapat pada file `function.py` terdiri dari:
  1. `min_conflicts()`: Fungsi ini sebagai implementasi dari algoritma Minimal, dengan input berupa komponen-komponen CSP, jumlah Ratu, Board, jumlah langkah maksimal untuk menyerah. Fungsi ini memiliki _return value_ berupa solusi atau sebuah _flag_ (False).
  2. `get_least_conflicts_y()`: Untuk mengambil posisi konflik paling sedikit pada setiap kolom. Fungsi ini mengharuskan input koordinat x pada board, jumlah Ratu, daftar kemungkinan gerak, dan board itu sendiri. _Return value_-nya adalah koordinat y pada board.
  3. `def conflicts()`: Digunakan untuk mengambil posisi dengan konflik paling sedikit pada setiap kolom. Input untuk fungsi ini terdiri dari: variabel dengan _value_ koordinat x pada board atau posisi x Ratu, koordinat y sekarang, jumlah Ratu, komponen CSP, daftar gerakan yang tidak boleh pada setiap kolom, dan board itu sendiri. Fungsi ini akan mengembalikan nilai koordinat y pada board.
  4. `create_board()`: Fungsi ini untuk membuat papan catur.
  5. `print_board()`: Fungsi ini digunakan untuk _print_ papan catur.
  
  Sedangkan pada file `main.py` terdapat beberapa langkah-langkah program ini, antara lain:
  1. Program memungkinkan untuk memasukkan jumlah Ratu. Selain itu, program juga memberikan dua mode berjalannya program yaitu, _verbose_ dan _all_. Mode _verbose_ hanya akan menunjukkan hasil akhir program, sedangkan mode _all_ berfungsi untuk memberikan kondisi awal papan catur dan kondisi akhir papan catur.
  2. Membuat object Board, variabel yang berisi daftar koordinat x dan y, dan mendefinisikan _constraints_ pada setiap Ratu.
  3. Mencari posisi Ratu secara acak, kemudian menginisialisasi daftar domains.
  4. Setelah itu, menempatkan semua Ratu pada papan catur (board) dengan terus menerus melakukan update pada _constraints_.
  5. Kemudiam, menjalankan fungsi CSP.
  6. Apabila jumlah Ratu kurang atau sama dengan 15 dan program berjalan di mode _all_, program akan mencetak kondisi awal papan catur.
  7. Kemudian akan program melakukan pemeriksaan apabila fungsi `min_conflicts()` sudah berjalan dengan baik. Apabila algoritma CSP telah mencapai batas rekursif sebesar 100, maka program mencetak **'Increase Max Steps to solve.'** dan program berhenti. Sebaliknya, program akan mencetak waktu algoritma tersebut berjalan, dan akan mengeluarkan sebuah file `output.txt` apabila jumlah Ratu sangat besar atau mencetak kondisi papan catur akhir.
  
  Saya hanya akan mencoba program tersebut pada jumlah Ratu sebanyak 4. Berikut ini hasil program tersebut:
  
  ![hasil-4q]()
  
  Solusi yang didapatkan oleh program tersebut sama dengan solusi 2 berdasarkan On-Line Encyclopedia of Integer Sequences (OEIS). Hal ini dapat diperiksa pada gambar pada teori Minimax.
  
----------------------------------------------------------------
