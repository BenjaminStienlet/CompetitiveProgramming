__author__ = 'Benjamin Stienlet'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    fo.write(line + "\n")


def output():
    return None


C = int(read_line())
for c in range(0, C):
    write_line("Case #%s: %s" % (c+1, output()))