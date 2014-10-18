"""
Largest product in a grid
========================================================================================================================
What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in
the 20x20 grid?
========================================================================================================================
"""

f = open('problem_011.txt', 'r')

grid = []
line = f.readline()
while line != '':
    grid.append(map(int, line.split(' ')))
    line = f.readline()

h = len(grid)
w = len(grid[0])

product = 0

for i in range(0, w):
    for j in range(0, h):
        #diagonal down
        if i+4 < w and j+4 < h:
            prod = 1
            for k in range(0, 4):
                prod *= grid[i+k][j+k]
            if prod > product:
                product = prod
        #diagonal up
        if i >= 4 and j+4 < h:
            prod = 1
            for k in range(0, 4):
                prod *= grid[i-k][j+k]
            if prod > product:
                product = prod
        #vertical
        if j+4 < h:
            prod = 1
            for k in range(0, 4):
                prod *= grid[i][j+k]
            if prod > product:
                product = prod
        #horizontal
        if i+4 < w:
            prod = 1
            for k in range(0, 4):
                prod *= grid[i+k][j]
            if prod > product:
                product = prod

print product