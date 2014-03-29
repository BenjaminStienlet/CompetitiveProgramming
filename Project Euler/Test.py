from helper_functions import HelperFunctions as Hf
from time import clock

clock()
limit = 1000000
primes = Hf.prime(limit)
print str(clock()) + ", " + str(limit)
print primes