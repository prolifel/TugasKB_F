from time import time
from BFS_search import breadth_first_search
from puzzle import Puzzle

kondisi=[[1, 8, 2,
        0, 4, 3,
        7, 6, 5]]

Puzzle.num_of_instances=0
t0=time()
bfs=breadth_first_search(kondisi[0])
t1=time()-t0
print('Route:', bfs)
print('Expanded Node:',Puzzle.num_of_instances)
print('Running Time:',t1)