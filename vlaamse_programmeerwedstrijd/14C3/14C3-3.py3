
f = open('14C3-3W.invoer', 'r')
def read():
    return f.readline()

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))


def place_files(files, sticks, used_sticks):
    # MEMOIZATION
    global memo
    tf = tuple(files)
    ts = tuple(sorted(sticks))
    tus = tuple(sorted(used_sticks))
    if (tf, ts, tus) in memo:
        return memo[(tf, ts, tus)]
    # END MEMO

    if len(files) == 0:
        return sum(used_sticks)
    f = files.pop(0)
    min = float("inf")

    for i, s in enumerate(sticks):
        if s >= f:
            cost = place_files(list(files), sticks[:i]+sticks[i+1:], used_sticks+[s-f])
            if cost < min:
                min = cost
    for i, s in enumerate(used_sticks):
        if s >= f:
            cost = place_files(list(files), sticks, used_sticks[:i]+used_sticks[i+1:]+[s-f])
            if cost < min:
                min = cost

    memo[(tf,ts,tus)] = min
    return min



n = int(read())

for i in range(n):
    memo = {}
    line = read_line()
    ns = line[0]
    sticks = line[1:]
    line = read_line()
    nf = line[0]
    files = line[1:]

    result = place_files(files, sticks, [])
    if result == float("inf"):
        print("%d ONMOGELIJK" % (i+1))
    else:
        print("%d %d" % (i+1, result))