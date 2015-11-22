import math

population = 7000000000
zombies = 1
# between 8 and 22: 2/h, else 1/h
start_hour = 8
end_hour = 22

time = 0
while population > 0:
    time += 1
    if 8 <= (time % 24) < 22:
        attacks = 2*zombies
    else:
        attacks = zombies
    if time >= 8:
        attacks -= math.floor(attacks / 3)
    population -= attacks
    zombies += attacks - math.floor(attacks / 5)
    print("hour %d, population %d, zombies %d, attacks %d" % (time, population, zombies, attacks))

print(time)