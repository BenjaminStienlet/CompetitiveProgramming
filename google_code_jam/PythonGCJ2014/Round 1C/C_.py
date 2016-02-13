__author__ = 'Benjamin S.'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    fo.write(line + "\n")


def output():
    inp = map(int, read_line().split(' '))
    n = inp[0]
    m = inp[0]
    k = inp[0]



    return None


C = int(read_line())
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))