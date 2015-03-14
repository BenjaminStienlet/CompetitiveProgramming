import math

__author__ = 'Benjamin S.'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    print line
    fo.write(line + "\n")


def output():
    inp = map(float, read_line().split("/"))
    p = inp[0]
    q = inp[1]

    if (q % 2) != 0:
        return "impossible"

    gen = int(math.ceil(math.log(q, 2)))

    for i in range(gen):
        p *= 2
        if p >= q:
            return i+1

    return "impossible"


C = int(read_line())
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))