from queue import PriorityQueue

f = open('problem10.txt')

l = list(map(lambda x: list(map(int, x.split())), f.readlines()))

queue = PriorityQueue()


m = 0
cost = [[0]*len(l[len(l)-1]) for _ in range(len(l))]
queue.put((-l[0][0], l[0][0], 0, 0))
cost[0][0] = l[0][0]

while not queue.empty():
    _, s, i, j = queue.get()
    if i == len(l)-1:
        m = max(m, s)
        continue
    s0 = s + l[i+1][j]
    if cost[i+1][j] < s0:
        queue.put((-s0, s0, i+1, j))
        cost[i+1][j] = s0
    s1 = s + l[i+1][j+1]
    if cost[i+1][j+1] < s1:
        queue.put((-s1, s1, i+1, j+1))
        cost[i+1][j+1] = s1

print(m)