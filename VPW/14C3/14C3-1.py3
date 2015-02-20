
f = open('14C3-1W.invoer', 'r')
def read():
    return f.readline()

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().strip().split(separator)))


n = int(read())

for i in range(n):
    ni = int(read())
    ingredients = {}
    for ing in range(ni):
        line = read()
        index = line.index(" ")
        ingredients[line[index:]] = int(line[:index])
    nr = int(read())
    recipes = [None]*nr
    rec_ing = [{} for j in range(nr)]
    for rec in range(nr):
        recipes[rec] = read()
        ri = int(read())
        for ing in range(ri):
            line = read()
            index = line.index(" ")
            rec_ing[rec][line[index:]] = int(line[:index])
    # END reading input

    max = 0
    result = []
    for j, rec in enumerate(recipes):
        min = float("inf")
        for ingredient, amount in rec_ing[j].items():
            try:
                a = int(ingredients[ingredient] / amount)
            except KeyError:
                a = 0
            if a < min:
                min = a
        if min > max:
            max = min
            result = [rec]
        elif min == max:
            result.append(rec)

    if max == 0:
        s = "%d GEEN RECEPT GEVONDEN" % (i+1)
    else:
        result.sort()
        s = "%d %d" % (i+1, max)
        for rec in result:
            s += " %s" % rec.strip()
    print(s)

