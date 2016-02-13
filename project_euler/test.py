from helper_functions import HelperFunctions as Hf
from time import clock

clock()

number = 8192
result = Hf.divisors(number)

print str(clock()) + ", " + str(number)
print result