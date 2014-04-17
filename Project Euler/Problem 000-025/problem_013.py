"""
Large sum
========================================================================================================================
Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
========================================================================================================================
"""

lines = file('problem_013.txt', 'r').readlines(100)

sum = 0

for line in lines:
    sum += int(line)

print str(sum)[0:10]