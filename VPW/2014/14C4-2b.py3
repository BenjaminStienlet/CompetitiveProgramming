__author__ = 'benjamin'

# f = open('14C4-2.invoer', 'r')
# def read():
#     return f.readline()

def read():
    return input()

# import time
#
# t = time.time()

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


def get_sequence(item):
    seq_prev = [item]
    seq_koker = [koker_nr[item]]
    current = item
    while prev[current] != bv and prev[current] is not None:
        current = prev[current]
        seq_prev.insert(0, current)
        seq_koker.insert(0, koker_nr[current])
    return seq_prev, seq_koker

n = int(read())

for i in range(n):
    line = read().split()
    n_verdiep = int(line[0])
    n_lift = int(line[1])

    liften = [[] for j in range(n_lift)]
    for j in range(n_lift):
        liften[j] = (j, list(map(int, read().split())))

    line = read().split()
    bv = int(line[0])
    ev = int(line[1])

    liften = sorted(liften, key=lambda x: x[1][2], reverse=True)

    # minimaal aantal stopplaatsen om van bv naar i te gaan
    dist = [float("inf")]*n_verdiep
    # vorige verdiep
    prev = [None]*n_verdiep
    # kokernummer i om van prev[i] naar i te gaan
    koker_nr = [None]*n_verdiep

    pq = PriorityQueue()

    dist[bv] = 0
    for verdiep in range(n_verdiep):
        pq.insert(verdiep, dist[verdiep])

    while not pq.empty():
        # (priority, verdiep)
        el = pq.pop()

        for lift in liften:
            lift_nr = lift[0]
            lift = lift[1]
            if lift[0] <= el[1] <= lift[1] and (el[1]-lift[0]) % lift[2] == 0:
                for verdiep in range(lift[0], lift[1]+1, lift[2]):
                    if verdiep == el[1]:
                        continue

                    cost = abs((el[1]-verdiep)/lift[2]) + dist[el[1]]

                    if el[1] > verdiep:
                        nr = 2*(lift_nr+1)
                    else:
                        nr = 2*(lift_nr+1)-1

                    change = False
                    if dist[verdiep] > cost:
                        change = True
                    elif dist[verdiep] == cost:
                        seq_verdiep, seq_kokers = get_sequence(verdiep)
                        new_seq, new_seq_kokers = get_sequence(el[1])
                        new_seq.append(verdiep)
                        new_seq_kokers.append(nr)

                        if len(new_seq) < len(seq_verdiep):
                            change = True
                        elif len(new_seq) == len(seq_verdiep):
                            change = new_seq_kokers < seq_kokers
                            if new_seq_kokers == seq_kokers:
                                change = new_seq < seq_verdiep

                    if change:
                        prev[verdiep] = el[1]
                        koker_nr[verdiep] = nr
                        dist[verdiep] = cost
                        pq.insert(verdiep, cost)

    s = "%d " % (i+1)
    seq_verdiep, seq_kokers = get_sequence(ev)
    seq = zip(seq_kokers, seq_verdiep)
    for el in seq:
        s += "(%s,%s)" % (el[0], el[1])
    print(s)

# print("Time elapse: %fs" % (time.time() - t))