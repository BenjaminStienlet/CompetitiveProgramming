import java.util.Set;
import java.util.HashSet;

class Solution1 {
    
    public static void main(String[] args) {
        new Solution1();
    }

    public Solution1() {
        LinkedListNode<Integer> node = 
            new LinkedListNode<Integer>(1, 
                new LinkedListNode<Integer>(2, 
                    new LinkedListNode<Integer>(3, 
                        new LinkedListNode<Integer>(1))));
        System.out.println("Before: " + node);
        System.out.println("After: " + removeDuplicates(node));
    }

    // Remove the duplicates from an unsorted linked list
    public <T> LinkedListNode<T> removeDuplicates(LinkedListNode<T> head) {
        if (head == null) {
            return head;
        }

        Set<T> alreadySeen = new HashSet<T>();
        LinkedListNode<T> node = head;
        alreadySeen.add(node.data);

        while (node.next != null) {
            if (!alreadySeen.contains(node.next.data)) {
                alreadySeen.add(node.next.data);
                node = node.next;
            } else {
                node.next = node.next.next;
            }
        }
        return head;
    }

}