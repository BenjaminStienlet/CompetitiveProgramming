
f = open('14C3-2W.invoer', 'r')
def read():
    return f.readline()

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))


def find_coalitions(list_scores, list_coalition, total_score):
    global target_score
    if len(list_scores) == 0:
        if total_score < target_score:
            return [None], float("inf")
        return [list_coalition], total_score
    elif total_score >= target_score:
        return [list_coalition], total_score
    else:
        name, score = list_scores[0]
        list_added, score_added = find_coalitions(list_scores[1:],
                                                  list_coalition+[name],
                                                  total_score + score)
        list_not_added, score_not_added = find_coalitions(list_scores[1:],
                                                          list_coalition,
                                                          total_score)
        if score_added < score_not_added:
            return list_added, score_added
        if score_added == score_not_added:
            list_added.extend(list_not_added)
            return list_added, score_added
        return list_not_added, score_not_added


n = int(read())

for i in range(n):
    city = read()
    nr_cities = int(read())
    scores = [None]*nr_cities
    total = 0

    for j in range(nr_cities):
        line = read().split()
        scores[j] = (line[0], int(line[1]))
        total += int(line[1])

    target_score = int(total / 2) + 1

    result = city.strip()
    coalitions = find_coalitions(scores, [], 0)[0]
    coalitions = [sorted(x) for x in coalitions]
    coalitions.sort()

    for coalition in coalitions:
        result += " "
        for c in coalition:
            result += c

    print(result)
