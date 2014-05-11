import itertools
import math

__author__ = 'Benjamin S.'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    print line
    fo.write(line + "\n")


def remove_cont_doubles(s):
    s2 = s[0]
    for i in range(1, len(s)):
        if s[i-1] != s[i]:
            s2 += s[i]
    return ''.join(s2)


def is_valid(s):
    s1 = remove_cont_doubles(s)
    s2 = remove_cont_doubles(sorted(s))

    return len(s1) == len(s2)


def add_to_dict(d, i):
    if i in d:
        d[i] += 1
    else:
        d[i] = 1
    return d


def output():
    n = int(read_line())
    trains = read_line().strip().split(" ")

    begin = {}
    end = {}
    double = {}
    invalid = []
    for i in trains:
        if i[0] != i[-1]:
            begin = add_to_dict(begin, i[0])
            end = add_to_dict(end, i[-1])
        else:
            double = add_to_dict(double, i[0])
        for j in i:
            if not is_valid(j):
                return 0
            if j in invalid:
                return 0
            if j != i[-1] and j != i[0]:
                invalid.append(j)

    count = 1
    free = 0
    for k, v in double.iteritems():
        count *= math.factorial(v)
        if k in begin and k in end:
            return 0
        if not k in begin and not k in end:
            free += 1

    for i in trains:
        if i[0] != i[-1] and not (i[0] in double or i[-1] in double or i[0] in end or i[-1] in begin):
            free += 1
    # for k, v in begin.iteritems():
    #     if v > 1:
    #         return 0
    #     if not k in end:
    #         print 'begin'
    #         free += 1
    #
    # for k, v in end.iteritems():
    #     if v > 1:
    #         return 0
    #     if not k in begin:
    #         print 'end'
    #         free += 1

    return count*(2**free)

    # c = 0
    # for i in itertools.permutations(trains, n):
    #     s = ''.join(i)
    #     if is_valid(s):
    #         c += 1
    # return c


C = int(read_line())
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))