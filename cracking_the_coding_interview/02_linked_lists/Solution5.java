public class Solution5 {

    public static void main(String [] args) {
        new Solution5();
    }

    public Solution5() {
        LinkedListNode<Integer> l617 = 
            new LinkedListNode<Integer>(7, 
                new LinkedListNode<Integer>(1, 
                    new LinkedListNode<Integer>(6)));
        LinkedListNode<Integer> l295 = 
            new LinkedListNode<Integer>(5, 
                new LinkedListNode<Integer>(9, 
                    new LinkedListNode<Integer>(2)));
        LinkedListNode<Integer> l963 = 
            new LinkedListNode<Integer>(3, 
                new LinkedListNode<Integer>(6, 
                    new LinkedListNode<Integer>(9)));
        LinkedListNode<Integer> l54  = 
            new LinkedListNode<Integer>(4, 
                new LinkedListNode<Integer>(5));
        System.out.println(addLists(l617, l295, 0));
        System.out.println(addLists(l963, l54, 0));
    }
    
    public LinkedListNode addLists(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2, int carry) {
        // check carry >= 0
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int resultData = carry;
        LinkedListNode<Integer> resultNode = new LinkedListNode<Integer>(0);

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
