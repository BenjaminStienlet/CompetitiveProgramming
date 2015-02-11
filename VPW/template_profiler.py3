f = open('', 'r')
def read():
    return f.readline()

# def read():
#     return input()

import cProfile, pstats, io
pr = cProfile.Profile()
pr.enable()

n = int(read())

for i in range(n):
    pass

pr.disable()
s = io.StringIO()
sortby = 'cumulative'
ps = pstats.Stats(pr, stream=s).sort_stats(sortby)
ps.print_stats()
print(s.getvalue())