import numpy as np
import matplotlib.pyplot as plt
import random


def get_array():
    global n
    a = [0]*n
    for k in range(n):
        a[k] = k
    for k in range(n):
        p = random.randint(0, n-1)
        tmp = a[k]
        a[k] = a[p]
        a[p] = tmp
    return a

n = 1000
nr = 1000
A = np.zeros((n, n))

count = 0
threshold = 0.01
diff = 0
for i in range(nr):
    a = get_array()
    print(i)
    for j in range(n):
        A[a[j]][j] += 1
        diff += abs(j - a[j])
        if a[j]-n*threshold <= j <= a[j]:
            count += 1

plt.matshow(A, cmap='hot')
plt.colorbar()
# plt.show()
prob = float(count)/(n*nr)
print("Probability %f" % prob)
diff = float(diff) / (n*nr)
print("Difference %f" % diff)