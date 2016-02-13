import math
d = {
    0: 0, 1: 1, 2: 2, 3: 3, 4: 2,
    5: 1, 6: 2, 7: 3, 8: 4, 9: 2
}


def nr_stones(n):
    stones = 0
    for i in str(n):
        stones += d[int(i)]
    return stones*250

stones = 12500000
years = 0
while stones > 0:
    years += 1

    stones -= nr_stones(years)

    if years % 43 == 0:
        stones = math.ceil(stones * 0.85)

if stones < 0:
    years -= 1

print(years)