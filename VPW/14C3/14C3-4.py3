
f = open('14C3-4W.invoer', 'r')
def read():
    return f.readline()

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))

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
        self.REMOVED = (-1, -1)

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

def get_sequence(row, col):
    seq_prev = [row, col]
    cr = row
    cc = col
    while prev[cr][cc] is not None:
        cr, cc = prev[cr][cc]
        seq_prev.insert(0, (cr, cc))
    return seq_prev

n = int(read())

for i in range(n):
    line = read_line()
    rows = line[0]
    cols = line[1]
    grid = [None]*rows
    for r in range(rows):
        grid[r] = read_line()

    dist = [[float("inf")]*cols for r in range(rows)]
    prev = [[None]*cols for r in range(rows)]

    pq = PriorityQueue()

    for r in range(rows):
        dist[r][0] = grid[r][0]
        for c in range(cols):
            pq.insert((r, c), dist[r][c])

    result = (-1, -1)
    while not pq.empty():
        # (priority, node)
        _, (r, c) = pq.pop()
        if c == cols-1:
            result = (r, c)
            break

        neighbours = []
        if r > 0:
            neighbours.append((r-1, c))
        if r < rows - 1:
            neighbours.append((r+1, c))
        if c > 0:
            neighbours.append((r, c-1))
        if c < cols - 1:
            neighbours.append((r, c+1))

        for (rn, cn) in neighbours:
            if (rn, cn) == prev[r][c]:
                continue
            cost = dist[r][c] + grid[rn][cn]
            if cost < dist[rn][cn]:
                dist[rn][cn] = cost
                prev[rn][cn] = (r, c)
                pq.insert((rn, cn), cost)
            if cost == dist[rn][cn]:
                if len(get_sequence(r, c)) > len(get_sequence(rn, cn)):
                    dist[rn][cn] = cost
                    prev[rn][cn] = (r, c)
                    pq.insert((rn, cn), cost)

    # Print result
    print("%d %d %d" % (i+1, len(get_sequence(result[0], result[1]))-1, dist[result[0]][result[1]]))