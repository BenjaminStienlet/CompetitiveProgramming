import heapq

# f = open('14C4-3.invoer', 'r')
# def read():
#     return f.readline()

def read():
    return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))


n = int(read())

for i in range(n):
    k = int(read())
    machines = int(read())
    installed = []

    time = {}
    nodes = [0]*k
    dep = {}
    idep = {}
    for j in range(k):
        line = read().split()
        node = line[0]
        nodes[j] = node
        time[node] = int(line[1])
        dep[node] = line[2:]


    no_dep = []
    heapq.heapify(no_dep)
    dep_nodes = []
    for node in nodes:
        if node not in idep:
            idep[node] = []
        if len(dep[node]) == 0:
            dep_nodes.append(node)
            heapq.heappush(no_dep, node)
        for node2 in dep[node]:
            if node2 in idep:
                idep[node2].append(node)
            else:
                idep[node2] = [node]

    pq = []
    heapq.heapify(pq)
    idle_m = set([])
    idle = "IDLE"

    for m in range(machines):
        heapq.heappush(pq, (0, m, idle))

    now = 0
    while len(pq) > 0:
        now, m, node = heapq.heappop(pq)
        if len(installed) < k:
            if node is not idle:
                for node2 in idep[node]:
                    dep[node2].remove(node)
                    if len(dep[node2]) == 0:
                        if node2 not in dep_nodes:
                            dep_nodes.append(node2)
                            heapq.heappush(no_dep, node2)
                        while len(idle_m) > 0:
                            mach = idle_m.pop()
                            heapq.heappush(pq, (now, mach, idle))
            if len(no_dep) > 0:
                node2 = heapq.heappop(no_dep)
                installed.append(node2)
                # print(len(installed), k, node2, no_dep, dep_nodes)
                heapq.heappush(pq, (now+time[node2], m, node2))
            else:
                # heapq.heappush(pq, (now+1,m,None))
                idle_m.add(m)

    print("%d %d" % (i+1, now))


