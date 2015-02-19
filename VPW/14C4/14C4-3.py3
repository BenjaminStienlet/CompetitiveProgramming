import heapq

f = open('14C4-3W.invoer', 'r')
def read():
    return f.readline()
#
# fout = open('14C4-3W.uitvoer', 'r')

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))

n = int(read())

for i in range(n):
    # nr nodes
    k = int(read())
    # nr machines
    machines = int(read())
    # Machines installed/installing
    installed = 0

    nodes = [0]*k
    # node -> time
    time = {}
    # node -> [node]
    dep = {}
    # node -> [node] (inverse dependencies)
    idep = {}
    # Heap with nodes that aren't waiting for a dependency to be installed
    no_dep = []
    heapq.heapify(no_dep)
    # All nodes that have been added to no_dep
    dep_nodes = []
    # Future event list, contains (time, type, info)
    # type == 0: info = node that is installed
    # type == 1: info = machine to be scheduled
    fel = []
    heapq.heapify(fel)
    # List of idle machines
    idle_m = set([])
    idle = "IDLE"

    for j in range(k):
        line = read().split()
        node = line[0]
        nodes[j] = node
        time[node] = int(line[1])
        dep[node] = line[2:]

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

    for m in range(machines):
        heapq.heappush(fel, (0, 1, m))

    now = 0
    while len(fel) > 0:
        now, event_type, info = heapq.heappop(fel)
        if event_type == 0:
            # remove node from the dependency-lists
            # add the nodes which have an empty dep list
            for node in idep[info]:
                dep[node].remove(info)
                if len(dep[node]) == 0:
                    if node not in dep_nodes:
                        dep_nodes.append(node)
                        heapq.heappush(no_dep, node)
                    while len(idle_m) > 0:
                        mach = idle_m.pop()
                        heapq.heappush(fel, (now, 1, mach))
        elif event_type == 1 and installed < k:
            if len(no_dep) > 0:
                node = heapq.heappop(no_dep)
                installed += 1
                # print(installed, k, node, now, now+time[node], no_dep)
                heapq.heappush(fel, (now+time[node], 0, node))
                heapq.heappush(fel, (now+time[node], 1, info))
            else:
                # heapq.heappush(fel, (now+1, m, idle))
                idle_m.add(info)

    print("%d %d" % (i+1, now))

    # result = int(fout.readline().split()[1])
    # if result != now:
    #     print(i+1, result, now)


