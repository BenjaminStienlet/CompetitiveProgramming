"""
Names Scores
========================================================================================================================
Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names,
begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value
by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the
938th name in the list. So, COLIN would obtain a score of 938 * 53 = 49714.

What is the total of all the name scores in the file?
========================================================================================================================
"""

import string

alphabet = list(string.ascii_uppercase)

with open('problem_022.txt', 'r') as content_file:
    content = content_file.read()

names = map(lambda x: x.strip('"'), content.split(","))

names.sort()

s = 0
for index, name in enumerate(names):
    score = 0
    for char in name:
        score += alphabet.index(char) + 1
    s += (index + 1) * score

print s