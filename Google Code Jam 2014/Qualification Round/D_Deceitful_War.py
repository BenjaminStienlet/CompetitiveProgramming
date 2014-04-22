fi = open('input.txt', 'r')
fo = open('output.txt', 'w')


def readline():
    return fi.readline()


def writeline(line):
    fo.write(line + "\n")


def war_naomi(blocks):
    chosen = blocks[0]
    blocks.remove(chosen)
    return chosen, blocks


def war_ken(chosen_n, blocks):
    for num in blocks:
        if num > chosen_n:
            blocks.remove(num)
            return num, blocks
    chosen = blocks[0]
    blocks.remove(chosen)
    return chosen, blocks


def deceitful_war_naomi(blocks_n, blocks_k):
    chosen = blocks_n[0]
    blocks_n.remove(chosen)
    num = 0.001
    if chosen < blocks_k[0]:
        told = blocks_k[-1]-num
        while told in blocks_k:
            num += 0.001
            told = blocks_k[-1]-num

        return chosen, blocks_n, told
    else:
        return chosen, blocks_n, blocks_k[-1]+num


T = int(readline())
for t in range(0, T):
    n = int(readline())
    blocks_naomi = sorted(map(float, readline().split(" ")))
    blocks_ken = sorted(map(float, readline().split(" ")))
    blocks_naomi_war = list(blocks_naomi)
    blocks_ken_war = list(blocks_ken)
    score_war = 0
    score_deceitful = 0
    for i in range(0, n):
        chosen_naomi, blocks_naomi_war = war_naomi(blocks_naomi_war)
        chosen_ken, blocks_ken_war = war_ken(chosen_naomi, blocks_ken_war)
        score_war += (chosen_naomi > chosen_ken)
    for i in range(0, n):
        chosen_naomi, blocks_naomi, told_naomi = deceitful_war_naomi(blocks_naomi, blocks_ken)
        chosen_ken, blocks_ken = war_ken(told_naomi, blocks_ken)
        score_deceitful += (chosen_naomi > chosen_ken)
    output = "Case #%s: %s %s" % (t+1, score_deceitful, score_war)
    print output
    writeline(output)