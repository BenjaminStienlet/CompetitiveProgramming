from PIL import Image

img = Image.open('problem7.jpeg')
w, h = img.size
pix = img.load()

p = []
for i in range(1, w-1):
    for j in range(1, h-1):
        if pix[i, j] == (0, 0, 0):
            p.append((i, j))
print(p)