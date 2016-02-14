class Solution2 {
    
    public static void main(String[] args) {
        new Solution2();
    }

    public Solution2() {
        LinkedListNode<Integer> node = 
            new LinkedListNode<Integer>(1, 
                new LinkedListNode<Integer>(2, 
                    new LinkedListNode<Integer>(3, 
                        new LinkedListNode<Integer>(4))));
        System.out.println("Result: " + kthToLast(node, 2));
    }
    
    public <T> T kthToLast(LinkedListNode<T> head, int k) {
        if (head == null) {
            return null;    // error
        }
        LinkedListNode<T> pointer1 = head;
        LinkedListNode<T> pointer2 = head;

        for (int i = 0; i < k; i++) {
            if (pointer1.next == null) {
                return null;    // error
            } else {
                pointer1 = pointer1.next;
            }
        }

        while (pointer1.next != null) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer2.data;
    }

}