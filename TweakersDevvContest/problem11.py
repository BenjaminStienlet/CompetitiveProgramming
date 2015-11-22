
from bitarray import bitarray

f = open('problem11.txt')
numbers = f.readlines()
numbers = [int(item) for sublist in numbers for item in sublist.split()]

max_n = max(numbers)
print("max number: ", max_n)

# Sieve of eratosthenes
array = bitarray(max_n+1)
array.setall(False)
array[0] = True
array[1] = True
primes = []
for i in range(len(array)):
    if not array[i]:
        primes.append(i)
        print("prime ", i)
        j = 2*i
        while j < len(array):
            array[j] = True
            j += i

# count prime numbers in the text file
count = 0
for item in numbers:
    if item in primes:
        count += 1

print(count)