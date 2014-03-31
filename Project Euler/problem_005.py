"""
Smallest multiple
========================================================================================================================
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
========================================================================================================================
"""

i = 0
while True:
    i += 20
    b = True
    for j in range(19, 1, -1):
        b = b and i % j == 0
    if b:
        break

print i