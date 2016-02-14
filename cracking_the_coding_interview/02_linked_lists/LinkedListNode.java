public class LinkedListNode<T> {

    public T data;
    public LinkedListNode<T> next = null;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public LinkedListNode(T data, LinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public String toString() {
        String result = "";
        result += data.toString();
        if (next != null) {
            result += " -> " + next.toString();
        }
        return result;
    }
}