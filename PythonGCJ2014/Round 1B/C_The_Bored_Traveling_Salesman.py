__author__ = 'Benjamin S.'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    print line
    fo.write(line + "\n")


def add(flights, a, b):
    if not a in flights:
        flights[a] = [b]
    else:
        f = flights[a]
        f.append(b)
        flights[a] = f
    return flights


def output():
    [n, m] = map(int, read_line().split(" "))
    zips = {}
    for i in range(n):
        zips[i+1] = int(read_line())

    flights = {}
    for i in range(m):
        [a, b] = map(int, read_line().split(" "))
        flights = add(flights, a, b)
        flights = add(flights, b, a)

    print zips, flights

    location = sorted(zips, key=zips.get)[0]
    sequence = [zips.pop(location)]

    while len(sequence) <= n:
        f = flights[location]
        m = float('Inf')
        loc = 0
        for i in f:
            if zips[i] < m:
                m = zips[i]
                loc = i



    seq = ""
    for s in set(sequence):
        seq += str(s)
    return seq


C = int(read_line())
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))