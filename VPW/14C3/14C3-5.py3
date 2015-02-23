
f = open('14C3-5.invoer', 'r')
def read():
    return f.readline()

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))

_empty = " "
_wall = "#"
_target = "."
_box = "$"
_miner = "@"
_miner_target = "+"
_box_target = "*"

def execute(miner_pos, next_pos, nnext_pos):
    miner_item = grid[miner_pos[0]][miner_pos[1]]
    next_item = grid[next_pos[0]][next_pos[1]]
    nnext_item = grid[nnext_pos[0]][nnext_pos[1]]

    if next_item == _wall:
        return miner_pos

    if next_item == _box or next_item == _box_target:
        if nnext_item == _wall or nnext_item == _box or nnext_item == _box_target:
            return miner_pos
        if nnext_item == _target:
            line = grid[nnext_pos[0]]
            line = line[:nnext_pos[1]] + _box_target + line[nnext_pos[1]+1:]
            grid[nnext_pos[0]] = line
        else:
            line = grid[nnext_pos[0]]
            line = line[:nnext_pos[1]] + _box + line[nnext_pos[1]+1:]
            grid[nnext_pos[0]] = line

    if next_item == _target or next_item == _box_target:
        line = grid[next_pos[0]]
        line = line[:next_pos[1]] + _miner_target + line[next_pos[1]+1:]
        grid[next_pos[0]] = line
    else:
        line = grid[next_pos[0]]
        line = line[:next_pos[1]] + _miner + line[next_pos[1]+1:]
        grid[next_pos[0]] = line

    if miner_item == _miner_target:
        line = grid[miner_pos[0]]
        line = line[:miner_pos[1]] + _target + line[miner_pos[1]+1:]
        grid[miner_pos[0]] = line
    else:
        line = grid[miner_pos[0]]
        line = line[:miner_pos[1]] + _empty + line[miner_pos[1]+1:]
        grid[miner_pos[0]] = line
    return next_pos

n = int(read())

for i in range(n):
    line = read_line()
    n_rows = line[0]
    n_cols = line[1]
    pos = (-1, -1)
    _empty_line = ''.join([_empty]*(n_cols+2))

    grid = [_empty_line]
    for r in range(1, n_rows+1):
        grid.append(_empty + read().strip() + _empty)
        if _miner in grid[r]:
            pos = (r, grid[r].index(_miner))
        elif _miner_target in grid[r]:
            pos = (r, grid[r].index(_miner_target))
    grid.append(_empty_line)

    n_com = int(read())
    commands = read().strip()

    for com in commands:
        if com == "L":
            pos = execute(pos, (pos[0]-1, pos[1]), (pos[0]-2, pos[1]))
        if com == "R":
            pos = execute(pos, (pos[0]+1, pos[1]), (pos[0]+2, pos[1]))
        if com == "B":
            pos = execute(pos, (pos[0], pos[1]+1), (pos[0], pos[1]+2))
        if com == "O":
            pos = execute(pos, (pos[0], pos[1]-1), (pos[0], pos[1]-2))

    for x in grid[1:n_rows+1]:
        print(x[1:n_cols+1])
    if i < n-1:
        print()