import math

fib0 = 0
fib1 = 1

d = fib0 + fib1

for i in range(3, 1001):
    fib = fib0 + fib1
    fib0 = fib1
    fib1 = fib

    d += fib

print(math.pi*d)    # 2.2095e+209