board = [[5,3,0,0,7,0,0,0,0],
         [6,0,0,1,9,5,0,0,0],
         [0,9,8,0,0,0,0,6,0],
         [8,0,0,0,6,0,0,0,3],
         [4,0,0,8,0,3,0,0,1],
         [7,0,0,0,2,0,0,0,6],
         [0,6,0,0,0,0,2,8,0],
         [0,0,0,4,1,9,0,0,5],
         [0,0,0,0,8,0,0,7,9]]

def print_board():
    for i in range(9):
        for j in range(9):
            print(board[i][j], end = " ")
        print()

def findEmpty():
    for i in range(9):
        for j in range(9):
            if board[i][j] == 0:
                return i, j
    return -1,-1

def checkValidity(num, row, col):
    for i in range(9):
        if board[row][i] == num:
            return False
    for i in range(9):
        if board[i][col] == num:
            return False
    i=row//3
    j=col//3
    for m in range(i*3, i*3+3):
        for n in range(j*3, j*3+3):
            if board[m][n] == num:
                return False
    return True

def solveSudoku():
    row,col = findEmpty()
    if row == -1 and col == -1:
        return True
    else:
        for i in range(1, 10):
            if checkValidity(i, row, col):
                board[row][col] = i
                if solveSudoku():
                    return True
                board[row][col] = 0               
    return False

solveSudoku()
print_board()


                
    
    

    

