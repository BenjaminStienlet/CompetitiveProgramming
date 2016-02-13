
from queue import PriorityQueue

monsters = [300, 600, 850, 900, 1100, 3500]
overkill = 0

for monster in monsters:
    queue = PriorityQueue()

    queue.put((0, 0))
    queue.put((2, 1))
    queue.put((0, 2))
    queue.put((0, 3))

    while monster > 0:
        t, unit = queue.get()
        if unit == 0:       # warrior
            monster -= 35
            if monster < 0:
                overkill -= monster
            queue.put((t+4, 0))
        elif unit == 1:     # mage
            monster -= 80
            queue.put((t+4, 1))
        elif unit == 2:     # rogue, primary
            monster -= 30
            queue.put((t+4, 1))
        elif unit == 3:     # rogue, secondary
            monster -= 20
            queue.put((t+3, 1))

print(overkill)