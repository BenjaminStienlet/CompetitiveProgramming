
public class Solution5 {
    
    private class LinkedListNode {
    
        public int data;
        public LinkedListNode next = null;

        public LinkedListNode(int data) {
            this.data = data;
        }
        public LinkedListNode(int data, LinkedListNode next) {
            this.data = data;
            this.next = next;
        }

        public String toString() {
            String result = "";
            result += data;
            if (next != null) {
                result += " -> " + next.toString();
            }
            return result;
        }
    }

    public static void main(String [] args) {
        new Solution5();
    }

    public Solution5() {
        LinkedListNode l617 = new LinkedListNode(7, new LinkedListNode(1, new LinkedListNode(6)));
        LinkedListNode l295 = new LinkedListNode(5, new LinkedListNode(9, new LinkedListNode(2)));
        LinkedListNode l963 = new LinkedListNode(3, new LinkedListNode(6, new LinkedListNode(9)));
        LinkedListNode l54  = new LinkedListNode(4, new LinkedListNode(5));
        System.out.println(addLists(l617, l295, 0));
        System.out.println(addLists(l963, l54, 0));
    }
    
    public LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
        // check carry >= 0
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int resultData = carry;
        LinkedListNode resultNode = new LinkedListNode(0);

        if (l1 != null) {
            resultData += l1.data;
        }
        if (l2 != null) {
            resultData += l2.data;
        }

        resultNode.data = resultData % 10;
        resultNode.next = addLists( l1 == null ? null : l1.next, 
                                    l2 == null ? null : l2.next, 
                                    resultData >= 10 ? 1 : 0);
        
        return resultNode;
    }
}
