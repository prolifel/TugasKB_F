from board import Board

def main():
    print('Penyelesaian 8 Queens dengan Algoritma BFS')
    ukuran = 8
    board = Board(ukuran)
    solusi = board.solve_bfs()
    for i, iter in enumerate(solusi):
        print('Langkah ke-%d:' % (i + 1))
        board.print(iter)
    print('Total langkah: %d' % len(solusi))


if __name__ == '__main__':
    main()
