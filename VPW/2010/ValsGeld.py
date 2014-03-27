

# f = open('input.txt', 'r')
# def readline():
#     return f.readline()


def readline():
    return raw_input()


m = int(readline())
g = 26
pakketten = []

for i in range(0, m):
    n = int(readline())
    pakket = []
    for j in range(0, n):
        line = readline()
        if line.endswith('\n'):
            line = line[:-1]
        tmp = line.split(" ")
        pakket.append((tmp[0], tmp[1], tmp[2]))
    pakketten.append(pakket)

for pakket in pakketten:
    inconsistent = False
    munten_lighter_add = []
    munten_heavier_add = []
    munten_lighter_remove = []
    munten_heavier_remove = []

    for meting in pakket:
        if meting[2] == "evenwicht":
            munten_lighter_remove.extend(meting[0] + meting[1])
            munten_heavier_remove.extend(meting[0] + meting[1])

        elif meting[2] == "omhoog":
            munten_heavier_add.extend(meting[0])
            munten_lighter_remove.extend(meting[0])
            munten_lighter_add.extend(meting[1])
            munten_heavier_remove.extend(meting[1])

        elif meting[2] == "omlaag":
            munten_lighter_add.extend(meting[0])
            munten_heavier_remove.extend(meting[0])
            munten_heavier_add.extend(meting[1])
            munten_lighter_remove.extend(meting[1])

    munten_lighter = list(set(munten_lighter_add) - set(munten_lighter_remove))
    munten_heavier = list(set(munten_heavier_add) - set(munten_heavier_remove))

    if len(munten_heavier) == 0 and len(munten_lighter) == 0 and len(munten_heavier_add) > 0:
        inconsistent = True

    if inconsistent:
        print "Inconsistente gegevens."
    else:
        if len(munten_lighter) == 1 and len(munten_heavier) != 1:
            print "Het valse geldstuk " + str(munten_lighter[0]) + " is lichter."
        elif len(munten_heavier) == 1 and len(munten_lighter) != 1:
            print "Het valse geldstuk " + str(munten_heavier[0]) + " is zwaarder."
        else:
            print "Te weinig gegevens."
