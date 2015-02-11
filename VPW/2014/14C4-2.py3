__author__ = 'benjamin'

# f = open('14C4-2W.invoer', 'r')
# def read():
#     return f.readline()

def read():
    return input()

n = int(read())

for i in range(n):
    line = read().split()
    n_verdiep = int(line[0])
    n_lift = int(line[1])

    liften = [[] for j in range(n_lift)]
    for j in range(n_lift):
        line = list(map(int, read().split()))
        liften[j] = list(range(line[0], line[1]+1, line[2]))

    line = read().split()
    bv = int(line[0])
    ev = int(line[1])

    # minimaal aantal stopplaatsen om van bv naar i te gaan
    min_stop = {i: float("inf") for i in range(n_verdiep)}
    # de sequentie horende om van bv naar verdiep i te gaan met kost min_stop[i]
    seq = {i: [] for i in range(n_verdiep)}

    queue = [(bv, 0)]

    while len(queue) > 0:
        el = queue.pop(0)
        for lift_nr, lift in enumerate(liften):
            if el[0] in lift:
                ind = lift.index(el[0])
                for verdiep in lift:
                    if verdiep == el[0]:
                        continue

                    cost = abs(lift.index(el[0]) - lift.index(verdiep)) + el[1]

                    if el[0] > verdiep:
                        nr = 2*(lift_nr+1)
                    else:
                        nr = 2*(lift_nr+1)-1

                    new_seq = list(seq[el[0]])
                    new_seq.append((nr, verdiep))

                    change = False
                    if min_stop[verdiep] == cost:
                        if len(new_seq) < len(seq[verdiep]):
                            change = True
                        elif len(new_seq) == len(seq[verdiep]):
                            for j in range(len(new_seq)):
                                if new_seq[j][0] > seq[verdiep][j][0]:
                                    change = False
                                    break
                            if change:
                                for j in range(len(new_seq)):
                                    if new_seq[j][1] < seq[verdiep][j][1]:
                                        change = False
                                        break
                    elif min_stop[verdiep] > cost:
                        change = True

                    if change:
                        seq[verdiep] = new_seq
                        min_stop[verdiep] = cost
                        queue.append((verdiep, cost))

    s = "%d " % (i+1)
    for el in seq[ev]:
        s += "(%s,%s)" % (el[0], el[1])
    print(s)