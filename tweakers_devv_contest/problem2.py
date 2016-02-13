import math

count = 6

fib_n1 = 0
fib_n = 1

while fib_n < 10**18:
    fib = fib_n + fib_n1
    fib_n1 = fib_n
    fib_n = fib
    sum_fib = 0

    for i in str(fib):
        sum_fib += int(i)

    if math.floor(math.sqrt(sum_fib)) ** 2 == sum_fib:
        print(sum_fib)
        count += 1

print(count)