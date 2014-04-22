__author__ = 'Benjamin Stienlet'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    fo.write(line + "\n")


def contains(row, s):
    for r in row:
        if r != s and r != 'T':
            return False
    return True


def four_in_a_row(s, grid):
    if any(map(lambda x: contains(x, s), grid)):
        return True
    if any(map(lambda x: contains(x, s), map(list, zip(*grid)))):
        return True
    if contains([grid[i][i] for i in range(0, 4)], s):
        return True
    if contains([grid[i][3-i] for i in range(0, 4)], s):
        return True
    return False


def output():
    grid = []
    for _ in range(0, 4):
        grid.append(read_line()[:4])

    if four_in_a_row('X', grid):
        return 'X won'
    if four_in_a_row('O', grid):
        return 'O won'
    if any(map(lambda x: '.' in x, grid)):
        return 'Game has not completed'
    return 'Draw'


C = int(read_line())
for c in range(0, C):
    write_line("Case #%s: %s" % (c+1, output()))
    read_line()