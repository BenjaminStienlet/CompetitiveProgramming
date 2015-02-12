__author__ = 'benjamin'

# f = open('14C4-2W.invoer', 'r')
# def read():
#     return f.readline()

def read():
    return input()

# import time
#
# t = time.time()

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
    min_stop = {i: float("inf") for i in range(n_verdiep)}
    # de sequentie om van bv naar verdiep i te gaan met kost min_stop[i]
    seq = {i: [] for i in range(n_verdiep)}

    queue = [(bv, 0)]
    # not needed, there is no significant improvement when using seen
    seen = [(bv, 0)]

    while len(queue) > 0:
        # (verdiep, cost)
        el = queue.pop(0)

        for lift in liften:
            lift_nr = lift[0]
            lift = lift[1]
            if lift[0] <= el[0] <= lift[1] and (el[0]-lift[0]) % lift[2] == 0:
                for verdiep in range(lift[0], lift[1]+1, lift[2]):
                    if verdiep == el[0]:
                        continue

                    cost = abs((el[0]-verdiep)/lift[2]) + el[1]

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
                            change = True
                            for j in range(len(new_seq)):
                                if new_seq[j][0] > seq[verdiep][j][0]:
                                    change = False
                                    break
                            if change:
                                for j in range(len(new_seq)):
                                    if new_seq[j][1] > seq[verdiep][j][1]:
                                        change = False
                                        break
                    elif min_stop[verdiep] > cost:
                        change = True

                    if change:
                        seq[verdiep] = new_seq
                        min_stop[verdiep] = cost
                        if not verdiep in seen:
                            queue.append((verdiep, cost))
                            seen.append((verdiep, cost))

    s = "%d " % (i+1)
    for el in seq[ev]:
        s += "(%s,%s)" % (el[0], el[1])
    print(s)

# print("Time elapse: %fs" % (time.time() - t))
