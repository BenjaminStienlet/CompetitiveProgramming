
# Reversible Numbers


def reverse(n):
    return int(str(n)[::-1])


def is_reversible(n):
    if n % 10 == 0:
        return False
    for i in str(n + reverse(n)):
        if int(i) % 2 == 0:
            return False
    return True

count = 0
for i in range(100000000):
    if is_reversible(i):
        print(i)
        count += 1

print(count)