import math


class HelperFunctions():

    def __init__(self):
        pass

    #===================================================================================================================
    # BASIC CALCULATIONS
    #===================================================================================================================

    @staticmethod
    def multiply(x, y):
        """
        Multiply the two given numbers.
        Uses the Karatsuba multiplication algorithm.
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
        """
        Get the square of the given number.
        """
        return HelperFunctions.multiply(n, n)

    #===================================================================================================================
    # FIBONACCI
    #===================================================================================================================

    @staticmethod
    def fibonacci(n):
        """
        Get the nth prime number.
        Uses the fast doubling fibonacci algorithm.
        """
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

    #===================================================================================================================
    # PRIME NUMBERS: GENERATION AND FACTORIZATION
    #===================================================================================================================

    @staticmethod
    def prime(limit):
        """
        Generate prime numbers up to the given limit.
        Uses the Sieve of Atkin algorithm.
        """
        if limit < 2:
            return []

        # Initialise the sieve
        sieve = [False]*(limit+1)

        # Put in candidate primes
        root = int(math.sqrt(limit)) + 1
        for x in range(1, root):
            for y in range(1, root):
                x_s = HelperFunctions.square(x)
                y_s = HelperFunctions.square(y)

                n = 4*x_s + y_s
                if n <= limit and (n % 12 == 1 or n % 12 == 5):
                    sieve[n] = not sieve[n]

                n = 3*x_s + y_s
                if n <= limit and n % 12 == 7:
                    sieve[n] = not sieve[n]

                n = 3*x_s - y_s
                if x > y and n <= limit and n % 12 == 11:
                    sieve[n] = not sieve[n]

        # Eliminate by sieving
        for n in range(5, root):
            if sieve[n]:
                n_s = HelperFunctions.square(n)
                for k in range(n_s, limit, n_s):
                    sieve[k] = False

        return [2, 3, 5] + [x for x in range(7, len(sieve)) if sieve[x]]

    @staticmethod
    def is_prime(n):
        """
        Checks whether the given number is a prime.
        """
        for i in range(math.sqrt(n)):
            if n % i == 0:
                return False
        return True

