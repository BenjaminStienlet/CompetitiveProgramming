
f = open('', 'r')
def read():
    return f.readline()

# def read():
#     return input()

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
        self.REMOVED = -1

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
    nr = 0  # Number of nodes
    source = 0   # Source node
    edges = []
    nodes = []

    dist = [float("inf")]*nr
    prev = [None]*nr

    pq = PriorityQueue()

    dist[source] = 0
    for node in nodes:
        pq.insert(node, dist[node])

    while not pq.empty():
        # (priority, node)
        node = pq.pop()

        for edge in edges:
            # Check if edge contains node (not needed if edge is a dict)
            if .... :
                # Neighbour of the node via edge
                neighbour = ....
                cost = dist[node] + length(node, neighbour)
                if cost < dist[neighbour]:
                    dist[neighbour] = cost
                    prev[neighbour] = node

    # Print result
