

fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def readline():
    return fi.readline()


def writeline(line):
    fo.write(line + "\n")

# def readline():
#     return raw_input()

# def writeline(line):
#     print line

t = int(readline())
for c in range(0, t):
    pos_cards = []
    for _ in range(0, 2):
        answer = int(readline())
        cards = []
        for i in range(0, 4):
            cards.append(map(int, readline().split(" ")))
        pos_cards.append(cards[answer-1])
    answer = []
    for i in pos_cards[0]:
        if i in pos_cards[1]:
            answer.append(i)
    output = 0
    if len(answer) == 0:
        output = "Volunteer cheated!"
    elif len(answer) == 1:
        output = answer[0]
    else:
        output = "Bad magician!"
    writeline("Case #%s: %s" % (c+1, output))
