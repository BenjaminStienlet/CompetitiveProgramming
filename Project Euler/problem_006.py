"""
Sum square difference
========================================================================================================================
The sum of the squares of the first ten natural numbers is,
1^2 + 2^2 + ... + 10^2 = 385
The square of the sum of the first ten natural numbers is,
(1 + 2 + ... + 10)^2 = 55^2 = 3025
Hence the difference between the sum of the squares of the first ten natural numbers and
the square of the sum is 3025 - 385 = 2640.
Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
========================================================================================================================
Solution:
(a + b + c)^2 - (a^2 + b^2 + c^2) = 2*(ab + ac + bc)
"""

numbers = range(1, 101)
sum = 0

for i in range(0, len(numbers)):
    for j in range(i+1, len(numbers)):
        sum += numbers[i]*numbers[j]

print 2*sum