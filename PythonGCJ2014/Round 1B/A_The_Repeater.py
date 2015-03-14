__author__ = 'Benjamin S.'


fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def read_line():
    return fi.readline()


def write_line(line):
    fo.write(line + "\n")


def output():
    n = int(read_line())
    strings = []
    for i in range(n):
        strings.append(read_line()[:-1])
    i = 0
    actions = 0
    while i < max(map(len, strings)):
        j = 0
        while i >= len(strings[j]):
            j += 1
        fc = strings[j][i]
        once = 0
        twice = 0
        for s in strings:
            if i >= len(s) or s[i] != fc:
                return "Fegla Won"
            if i+1 == len(s) or s[i] == fc and s[i+1] != fc:
                once += 1
            else:
                twice += 1
        newStrings = []
        for s in strings:
            if once > twice:
                if not(i+1 == len(s) or s[i] == fc and s[i+1] != fc):
                    newStrings.append(s[:i] + s[i+1:])
                    actions += 1
                else:
                    newStrings.append(s)
            else:
                if i+1 == len(s) or s[i] == fc and s[i+1] != fc:
                    newStrings.append(s[:i] + s[i] + s[i:])
                    actions += 1
                else:
                    newStrings.append(s)
        strings = newStrings
        i += 1

    return actions

C = int(read_line())
for c in range(C):
    write_line("Case #%s: %s" % (c+1, output()))