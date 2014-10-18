"""
Summation of primes
========================================================================================================================
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
Find the sum of all the primes below two million.
========================================================================================================================
"""

from ..helper_functions import HelperFunctions as Hf

print sum(Hf.prime(2000000))