"""
Lattice paths
========================================================================================================================
Starting in the top left corner of a 2x2 grid, and only being able to move to the right and down, there are exactly 6
routes to the bottom right corner.

How many such routes are there through a 20x20 grid?
========================================================================================================================
"""

import math

n = 20
s = 0
for i in range(1, n+1):
    s += math.factorial(i)
s *= 2

print s