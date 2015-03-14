__author__ = 'Benjamin Stienlet'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    fo.write(line + "\n")


# def check(i, row):
#     return all(map(lambda x: x <= i, row))
#
#
# def check_grid(i, j, grid):
#     if grid[0][j] == grid[i][j] or grid[-1][j] == grid[i][j]:
#         if check(grid[i][j], map(list, zip(*grid))[j]):
#             return True
#     if grid[i][0] == grid[i][j] or grid[i][-1] == grid[i][j]:
#         if check(grid[i][j], grid[i]):
#             return True
#     return False
#
#
# def output():
#     size = map(int, read_line().split(" "))
#
#     grid = []
#     numbers = []
#     for i in range(size[0]):
#         grid.append(map(int, read_line().split(" ")))
#         for j in grid[i]:
#             if not j in numbers:
#                 numbers.append(j)
#
#     numbers.sort(reverse=True)
#
#     for i in range(len(grid)):
#         for j in range(len(grid[0])):
#             if not check_grid(i, j, grid):
#                 return "NO"
#     return "YES"

def trim(grid, sim):
    for i in range(len(grid)):
        m = max(grid[i])
        for j in range(len(sim[i])):
            if sim[i][j] > m:
                sim[i][j] = m
    return grid, sim


def output():
    size = map(int, read_line().split(" "))
    grid = []
    sim = []

    for i in range(size[0]):
        grid.append(map(int, read_line().split(" ")))
        sim.append([100]*size[1])

    grid, sim = trim(grid, sim)
    grid, sim = trim(map(list, zip(*grid)), map(list, zip(*sim)))

    if sim == grid:
        return "YES"
    return "NO"


C = int(read_line())
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))