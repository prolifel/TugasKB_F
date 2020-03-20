import copy
from time import time

inp=[[1,2,3],[4,5,6],[7,-1,8]]
out=[[1,2,3],[4,5,6],[7,8,-1]]

def move(temp, movement):
  if movement=="up":
    for i in range(3):
      for j in range(3):
        if(temp[i][j]==-1):
          # blank space found
          if i!=0:
            temp[i][j]=temp[i-1][j]
            temp[i-1][j]=-1
          return temp

  if movement=="down":
    for i in range(3):
      for j in range(3):
        if(temp[i][j]==-1):
          # blank space found
          if i!=2:
            temp[i][j]=temp[i+1][j]
            temp[i+1][j]=-1
          return temp

  if movement=="left":
    for i in range(3):
      for j in range(3):
        if(temp[i][j]==-1):
          # blank space found
          if j!=0:
            temp[i][j]=temp[i][j-1]
            temp[i][j-1]=-1
          return temp

  if movement=="right":
    for i in range(3):
      for j in range(3):
        if(temp[i][j]==-1):
          # blank space found
          if j!=2:
            temp[i][j]=temp[i][j+1]
            temp[i][j+1]=-1
          return temp
      
def ids():
  global inp
  global out
  global flag

  for limit in range(100):
    print('LIMIT -> '+str(limit))
    stack=[]
    inpx=[inp,"none"]
    stack.append(inpx)
    # for i in range(9):
    level=0
    while(True):
      if len(stack)==0:
        break
      puzzle=stack.pop(0)
      if level<=limit:
        print(str(puzzle[1])+" --> "+str(puzzle[0]))
        if(puzzle[0]==out):
          print("Solusi ditemukan")
          print('pada level ke-'+str(level))
          flag=True
          return
        else:
          level=level+1
          # up
          if(puzzle[1]!="down"):
            temp=copy.deepcopy(puzzle[0])
            up=move(temp, "up")
            if(up!=puzzle[0]):
              upx=[up,"up"]
              stack.insert(0, upx)
          # left
          if(puzzle[1]!="right"):
            temp=copy.deepcopy(puzzle[0])
            left=move(temp, "left")
            if(left!=puzzle[0]):
              leftx=[left,"left"]
              stack.insert(0, leftx)
          # down
          if(puzzle[1]!="up"):
            temp=copy.deepcopy(puzzle[0])
            down=move(temp, "down")
            if(down!=puzzle[0]):
              downx=[down,"down"]
              stack.insert(0, downx)
          # right
          if(puzzle[1]!="left"):
            temp=copy.deepcopy(puzzle[0])
            right=move(temp, "right")
            if(right!=puzzle[0]):
              rightx=[right,"right"]
              stack.insert(0, rightx)

print('Berikut ini Algoritma IDS pada 8 Puzzle\n')

flag=False

t0 = time()
ids()
t1 = time() - t0

if flag==False:
  print('8 Puzzle tidak dapat diselesaikan')

print('Running time:', t1)