"""
10001st prime
========================================================================================================================
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
What is the 10 001st prime number?s
========================================================================================================================
"""

from ..helper_functions import HelperFunctions as Hf

primes = Hf.prime(2**20)
print primes[10000]
