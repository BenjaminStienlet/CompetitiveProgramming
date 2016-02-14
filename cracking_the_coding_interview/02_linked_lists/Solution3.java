class Solution3 {
    
    public static void main(String[] args) {
        new Solution3();
    }

    public Solution3() {
        LinkedListNode<Integer> node = 
            new LinkedListNode<Integer>(1, 
                new LinkedListNode<Integer>(2, 
                    new LinkedListNode<Integer>(3, 
                        new LinkedListNode<Integer>(4))));
        System.out.println("Before: " + node);
        removeNode(node.next);
        System.out.println("After: " + node);
    }

    public <T> void removeNode(LinkedListNode<T> node) {
        if (node != null && node.next != null) {
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }

}