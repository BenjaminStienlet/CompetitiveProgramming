from itertools import combinations

money = [200] * 4 + [100] * 2 + [50] * 8 + [20] + [10] * 4 + [5] * 3

comb = []
for i in range(1, 6):
    comb.extend(combinations(money, i))

comb = list(map(sum, comb))
print(len(set(comb)))
print(sorted(set(comb)))