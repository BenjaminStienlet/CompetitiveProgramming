import numpy as np
import math

x0 = np.array([30, 50, 90])
x1 = np.array([4**6, 4**8, 9])

speed = 20  # points / second
t = 25*60

v = (x1-x0)/np.linalg.norm(x1-x0)
x_t = speed*v*t + x0
print(x_t)  # x:1.889,11 y:29.992,32 z:52,96
