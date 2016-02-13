__author__ = 'Benjamin Stienlet'


fi = open('kaarten10000000.txt', 'r')

cards = list(map(lambda x: sum(map(int, x.strip().split())), fi.readlines()))

memo = [-1] * (len(cards)+1)
memo[-1] = 0


def simulate_player(index):
    if memo[index] != -1:
        return memo[index]

    s_p1 = 0
    # Allow the player to pick up 0 cards
    new_index, s_p2 = simulate_computer(index)
    gain = determine_gain(s_p1, s_p2)
    max_result = gain + simulate_player(new_index)
    orig_index = index
    # Pick cards
    while s_p1 <= 2015:
        if index == len(cards):
            break
        s_p1 += cards[index]
        index += 1
        # Simulate computer
        new_index, s_p2 = simulate_computer(index)
        # Calculate win/loss
        gain = determine_gain(s_p1, s_p2)
        # Recursive call
        result = gain + simulate_player(new_index)
        max_result = max(result, max_result)

    memo[orig_index] = max_result
    return max_result


def simulate_computer(index):
    s = 0
    while s < 1830:
        if index == len(cards):
            break
        s += cards[index]
        index += 1
    return index, s


def determine_gain(s_p1, s_p2):
    if s_p1 > 2015 and s_p2 > 2015:
        return 0
    if s_p1 > 2015:
        return -2
    if s_p2 > 2015:
        return 2
    if s_p1 > s_p2:
        return 2
    if s_p2 > s_p1:
        return -2
    return 0


print(">>> Start")
for i in range(len(cards)-1, 0, -1000):
    simulate_player(i)
    print(i)

print(">>> Solution: ", simulate_player(0))