"""
Largest palindrome product
========================================================================================================================
A palindromic number reads the same both ways.
The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 x 99.
Find the largest palindrome made from the product of two 3-digit numbers.
========================================================================================================================
"""
largest = 0

for i in range(999, 0, -1):
    for j in range(999, i-1, -1):
        product = i*j
        index = int(len(str(product))/2)
        if str(product) == str(product)[::-1]:
            if product > largest:
                largest = product

print largest