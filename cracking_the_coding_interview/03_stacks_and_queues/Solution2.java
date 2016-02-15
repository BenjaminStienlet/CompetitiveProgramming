class Solution2 {

    public static void main(String[] args) {
        new Solution2();
    }

    public Solution2() {
        MinStack<Integer> stack = new MinStack<Integer>();
        stack.push(3);
        stack.push(2);
        System.out.println(stack.min());
        stack.push(0);
        stack.push(1);
        System.out.println(stack.min());
    }
    
    private class MinStack<T extends Comparable<T>> {

        private class MinStackNode<T> {
            public T data;
            public T minData;
            public MinStackNode<T> next;

            public MinStackNode(T data, T minData) {
                this.data = data;
                this.minData = minData;
            }
        }

        private MinStackNode<T> top;

        public T pop() {
            if (top == null) {
                return null;    // error
            }
            T item = top.data;
            top = top.next;
            return item;
        }

        public void push(T item) {
            T minData = item;
            if (top != null && top.minData.compareTo(minData) < 0) {
                minData = top.minData;
            }
            MinStackNode<T> t = new MinStackNode<T>(item, minData);
            t.next = top;
            top = t;
        }

        public T min() {
            if (top == null) {
                return null;    // error
            }
            return top.minData;
        }

        public T peek() {
            if (top == null) {
                return null;    // error
            }
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }

    }

}