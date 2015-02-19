
# f = open('14C4-3W.invoer', 'r')
# def read():
#     return f.readline()

def read():
    return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))

import heapq

n = int(read())

for i in range(n):
    nr_stukken = int(read())
    stukken = read_line()

    pq = []
    heapq.heapify(pq)

    for stuk in stukken:
        heapq.heappush(pq, stuk)

    kost = 0
    while len(pq) > 1:
        el1 = heapq.heappop(pq)
        el2 = heapq.heappop(pq)
        kost += el1 + el2
        heapq.heappush(pq, el1+el2)

    print("%d %d" % (i+1, kost))