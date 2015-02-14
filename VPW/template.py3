
f = open('', 'r')
def read():
    return f.readline()

# def read():
#     return input()

def read_line(input_type=int, separator=" "):
    return list(map(input_type, read().split(separator)))


n = int(read())

for i in range(n):
    pass