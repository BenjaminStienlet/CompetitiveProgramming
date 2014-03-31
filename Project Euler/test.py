from helper_functions import HelperFunctions as Hf
from time import clock

clock()
limit = 123456789123456789
# primes = Hf.prime(limit)
primes = Hf.factorization(limit)
print str(clock()) + ", " + str(limit)
print primes

# clock()
# b = all(map(lambda x: Hf.is_prime(x), primes))
# print str(b) + ", " + str(clock())
