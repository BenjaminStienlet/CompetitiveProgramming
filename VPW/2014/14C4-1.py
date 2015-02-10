__author__ = 'benjamin'

import time

t = time.time()

f = open('14C4-1W.invoer', 'r')
def read():
    return f.readline()

# def read():
#     return input()


n = int(read())

for i in range(n):
    # Read data
    n_cakes = int(read())
    n_boxes = int(read())

    boxes = [0] * n_boxes
    for j in range(n_boxes):
        box = read()
        boxes[j] = list(map(int, box.split()))

    # Sort boxes, largest capacity first
    # No significant time difference
    # boxes = sorted(boxes, key=lambda x: x[0], reverse=True)

    # Queue with combinations, [(amount, cost)]
    combinations = [(n_cakes, 0)]

    # Dict: {amount: minimal cost}
    min_dict = {i: float("inf") for i in range(n_cakes)}
    min_cost = float("inf")

    while len(combinations) > 0:
        el = combinations.pop(0)
        for j in boxes:
            cost = el[1] + j[1]
            amount = el[0] - j[0]

            if amount <= 0:
                if cost < min_cost:
                    min_cost = cost
            else:
                if cost < min_dict[amount]:
                    combinations.append((amount, cost))
                    min_dict[amount] = cost

    print('%d %d' % (i+1, min_cost))

print("\nTime elapsed: %fs" % (time.time()-t))