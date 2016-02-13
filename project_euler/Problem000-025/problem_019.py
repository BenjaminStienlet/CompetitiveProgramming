"""
Counting Sundays
========================================================================================================================
You are given the following information, but you may prefer to do some research for yourself.

    1 Jan 1900 was a Monday.
    Thirty days has September,
    April, June and November.
    All the rest have thirty-one,
    Saving February alone,
    Which has twenty-eight, rain or shine.
    And on leap years, twenty-nine.
    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.

How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
========================================================================================================================
"""

months = {1: 31, 2: 28, 3: 31, 4: 30, 5: 31, 6: 30, 7: 31, 8: 31, 9: 30, 10: 31, 11: 30, 12: 31}


def is_leap_year(y):
    if y % 4 == 0:
        if y % 100 == 0:
            if y % 400 == 0:
                return True
            return False
        return True
    return False


year = 1900
month = 1
day = 0
count = 0
while year <= 2000:
    days = months[month]
    if month == 2 and is_leap_year(year):
        days = 29
    day = (day + days) % 7
    if day == 6 and year > 1900:
        count += 1

    month += 1
    if month == 13:
        month = 1
        year += 1

print count