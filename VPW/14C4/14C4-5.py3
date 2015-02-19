
# f = open('14C4-5W.invoer', 'r')
# def read():
#     return f.readline()

def read():
    return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().split(separator)))

###   PRIORITY QUEUE   #########################################################
import heapq


class PriorityQueue(object):
    """Priority queue based on heap, capable of inserting a new node with
    desired priority, updating the priority of an existing node and deleting
    an abitrary node while keeping invariant"""

    def __init__(self):
        """if 'heap' is not empty, make sure it's heapified"""
        heap = []
        heapq.heapify(heap)
        self.heap = heap
        self.size = 0
        self.entry_finder = dict({i[-1]: i for i in heap})
        # Remove marker: CHANGE this value for the problem!
        # Has to be the same type as values of the priority queue
        #self.REMOVED = '<remove_marker>'
        self.REMOVED = (-1, [])

    def insert(self, node, priority=0.0):
        """'entry_finder' bookkeeps all valid entries, which are bonded in
        'heap'. Changing an entry in either leads to changes in both."""

        if node in self.entry_finder:
            self.delete(node)
        entry = [priority, node]
        self.entry_finder[node] = entry
        heapq.heappush(self.heap, entry)
        self.size += 1

    def delete(self, node):
        """Instead of breaking invariant by direct removal of an entry, mark
        the entry as "REMOVED" in 'heap' and remove it from 'entry_finder'.
        Logic in 'pop()' properly takes care of the deleted nodes."""

        entry = self.entry_finder.pop(node)
        entry[-1] = self.REMOVED
        self.size -= 1
        return entry[0]

    def pop(self):
        """Any popped node marked by "REMOVED" does not return, the deleted
        nodes might be popped or still in heap, either case is fine."""

        while self.heap:
            priority, node = heapq.heappop(self.heap)
            if node is not self.REMOVED:
                del self.entry_finder[node]
                self.size -= 1
                return priority, node
        raise KeyError('pop from an empty priority queue')

    def empty(self):
        return self.size == 0
###   PRIORITY QUEUE   #########################################################

n = int(read())

for i in range(n):
    # Read input
    nr = int(read())  # Number of nodes
    source = int(read())   # Source node
    dest = int(read())
    nr_cultuur = int(read())
    cultuur = read_line()
    nr_voetbal = int(read())
    voetbal = read_line()
    nr_edges = int(read())
    edges = []
    for j in range(nr_edges):
        edges.append(read_line())
    for j in range(1,nr+1):
        if [j,j] not in edges:
            edges.append([j,j])

    cultuur = [source] + cultuur + [dest]

    pq = PriorityQueue()

    pq.insert((source,), 0)
    solution = None

    while not pq.empty():
        cost, seq = pq.pop()
        node = seq[-1]
        if len(seq) == len(cultuur):
            if node == dest and all(map(lambda x: x in seq, voetbal)):
                solution = len(cultuur) - 2 - cost
                break
            else:
                continue
        for edge in edges:
            # Check if edge contains node (not needed if edge is a dict)
            if node in edge:
                # Neighbour of the node via edge
                if node == edge[0]:
                    neighbour = edge[1]
                else:
                    neighbour = edge[0]

                new_seq = seq + (neighbour,)
                cost = 0
                for j in range(len(new_seq)):
                    if new_seq[j] != cultuur[j]:
                        cost += 1
                # if neighbour != cultuur[len(seq)]:
                #     cost += 1

                pq.insert(new_seq, cost)

    # Print result
    if solution is None:
        solution = "onmogelijk"
    print("%d %s" % (i+1, str(solution)))
