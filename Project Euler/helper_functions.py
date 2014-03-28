

class HelperFunctions():

    def __init__(self):
        pass

    @staticmethod
    def fibonacci(n):
        return HelperFunctions._fibonacci_doubling(n)[0]

    @staticmethod
    def _fibonacci_recursive(n):
        """
        Recursive implementation for fibonacci. This is extremely slow.
        """
        if n == 0:
            return 0
        if n == 1:
            return 1
        return HelperFunctions._fibonacci_recursive(n-1) + HelperFunctions._fibonacci_recursive(n-2)

    @staticmethod
    def _fibonacci_doubling(n):
        """
        Fast doubling fibonacci algorithm.
        """
        if n == 0:
            return 0, 1
        fib1, fib2 = HelperFunctions._fibonacci_doubling(n // 2)
        c = HelperFunctions.multiply(fib1, 2*fib2 - fib1)
        d = HelperFunctions.square(fib1) + HelperFunctions.square(fib2)
        if n % 2 == 0:
            return c, d
        else:
            return d, c+d

    @staticmethod
    def multiply(x, y):
        """
        Karatsuba multiplication.
        """
        m = 2
        cutoff = 1536
        if x.bit_length() <= cutoff or y.bit_length() <= cutoff:
            return x * y
        else:
            x_low = x % m
            x_high = x // m
            y_low = y % m
            y_high = y // m
            a = HelperFunctions.multiply(x_high, y_high)
            b = HelperFunctions.multiply(x_low + x_high, y_low + y_high)
            c = HelperFunctions.multiply(x_low, y_low)
            return a * m**2 + (b - a - c)*m + c

    @staticmethod
    def square(n):
        return HelperFunctions.multiply(n, n)