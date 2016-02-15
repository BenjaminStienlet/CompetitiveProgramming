class Solution1 {
    
    public static void main(String[] args) {
        new Solution1();
    }

    public Solution1() {
        MultiStack<Integer> stack = new MultiStack<Integer>();
        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(1, 3);
        stack.push(1, 4);
        stack.push(2, 5);
        stack.push(2, 6);
        System.out.println(stack);
        stack.push(1, 7);
        stack.push(1, 8);
        stack.push(1, 9);
        System.out.println(stack);
    }

    private class MultiStack<T> {

        private final int nrStacks;
        private final static int DEFAULT_NR_STACKS = 3;

        private T[] array;
        private Interval[] boundaries;
        private final static int DEFAULT_SIZE = 4;

        public MultiStack() {
            this(DEFAULT_NR_STACKS);
        }
        public MultiStack(int nrStacks) {
            this.nrStacks = nrStacks;

            array = (T[]) new Object[nrStacks * DEFAULT_SIZE];

            boundaries = new Interval[nrStacks];
            for (int i = 0; i < nrStacks; i++) {
                boundaries[i] = new Interval(i*DEFAULT_SIZE, i*DEFAULT_SIZE);
            }
        }

        public void push(int stackIndex, T item) {
            if (freeSpace(stackIndex) == 0) {
                doubleSize(stackIndex);
            }
            array[boundaries[stackIndex].end] = item;
            boundaries[stackIndex].end++;
        }

        public T pop(int stackIndex) {
            if (boundaries[stackIndex].size() == 0) {
                return null; // error
            }
            T element = array[boundaries[stackIndex].end - 1];
            array[boundaries[stackIndex].end - 1] = null;
            boundaries[stackIndex].end--;
            // TODO: resize when allocated space is too much?
            return element;
        }

        private int freeSpace(int stackIndex) {
            if (stackIndex != nrStacks - 1) {
                return boundaries[stackIndex+1].begin - boundaries[stackIndex].end;
            } else {
                return array.length - boundaries[stackIndex].end;
            }
        }

        private int allocatedSize(int stackIndex) {
            if (stackIndex != nrStacks - 1) {
                return boundaries[stackIndex+1].begin - boundaries[stackIndex].begin;
            } else {
                return array.length - boundaries[stackIndex].begin;
            }
        }

        private void doubleSize(int stackIndex) {
            int length = allocatedSize(stackIndex);
            T[] newArray = (T[]) new Object[array.length + length];

            for (int i = 0; i < nrStacks; i++) {
                int moved = 0;
                if (i > stackIndex) {
                    moved = length;
                }
                for (int j = boundaries[i].begin; j < boundaries[i].end; j++) {
                    newArray[j + moved] = array[j];
                }
                boundaries[i].begin += moved;
                boundaries[i].end += moved;
            }
            array = newArray;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < nrStacks; i++) {
                result.append("[ ");
                for (int j = boundaries[i].begin; j < boundaries[i].end; j++) {
                    result.append(array[j] + " ");
                }
                result.append("] ");
            }
            return result.toString();
        }
    }

    private class Interval {
        public int begin;
        public int end;

        public Interval(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        public int size() {
            return end - begin;
        }
    }

}