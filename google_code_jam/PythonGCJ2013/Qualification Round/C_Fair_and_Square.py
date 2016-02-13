import math
import time

__author__ = 'Benjamin Stienlet'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    print str(line) + " " + str(time.clock())
    fo.write(line + "\n")


def multiply(x, y):
        """
        Multiply the two given numbers.
        Uses the Karatsuba multiplication algorithm.
        """
        m = 2
        cutoff = 1536
        if x.bit_length() <= cutoff or y.bit_length() <= cutoff:
            return x * y
        else:
            x_low = x % m
            x_high = x // m
            y_low = y % m
            y_high = y // m
            a = multiply(x_high, y_high)
            b = multiply(x_low + x_high, y_low + y_high)
            c = multiply(x_low, y_low)
            return a * m**2 + (b - a - c)*m + c


def is_palindrome(num):
    return str(num) == str(num)[::-1]


def output():
    limits = map(int, read_line().split(" "))
    count = 0
    for i in range(int(math.floor(math.sqrt(limits[0]))), int(math.ceil(math.sqrt(limits[1])))+1):
        if is_palindrome(i):
            square = multiply(i, i)
            if limits[0] <= square <= limits[1] and is_palindrome(square):
                count += 1
    return count


C = int(read_line())
time.clock()
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))