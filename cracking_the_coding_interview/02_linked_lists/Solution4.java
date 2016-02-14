class Solution4 {
    
    public static void main(String[] args) {
        new Solution4();
    }

    public Solution4() {
        LinkedListNode<Integer> node = 
            new LinkedListNode<Integer>(5, 
                new LinkedListNode<Integer>(2, 
                    new LinkedListNode<Integer>(6, 
                        new LinkedListNode<Integer>(4,
                            new LinkedListNode<Integer>(1, 
                                new LinkedListNode<Integer>(3))))));
        System.out.println("Before: " + node);
        System.out.println("After: " + partition(node, 4));
    }

    public <T extends Comparable<T>> LinkedListNode<T> partition(LinkedListNode<T> node, T partEl) {
        LinkedListNode<T> head = node;
        LinkedListNode<T> tail = node;

        while (node != null) {
            LinkedListNode<T> next = node.next;

            if (node.data.compareTo(partEl) < 0) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }

            node = next;
        }
        tail.next = null;
        return head;
    }

}