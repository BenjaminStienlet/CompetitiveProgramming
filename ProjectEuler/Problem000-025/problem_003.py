"""
Largest prime factor
========================================================================================================================
The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
========================================================================================================================
"""

from ..helper_functions import HelperFunctions as Hf

print Hf.factorization(600851475143)[-1][0]
