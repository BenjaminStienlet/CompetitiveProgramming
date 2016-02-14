public class LinkedList<T> {
    
    private LinkedListNode<T> head;

    public LinkedList() {
        
    }
    public LinkedList(LinkedListNode head) {
        this.head = head;
    }

    public void insert(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (head == null) {
            head = newNode;
        } else {
            LinkedListNode n = head;
            while(n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    } 



}