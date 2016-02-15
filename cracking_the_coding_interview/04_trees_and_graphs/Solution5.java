public class Solution5 {
    
    public static void main(String[] args) {
        new Solution5();
    }

    public Solution5() {
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> two = new TreeNode<Integer>(2);
        TreeNode<Integer> three = new TreeNode<Integer>(3);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> six = new TreeNode<Integer>(6);

        two.leftChild = one;
        two.rightChild = four;
        five.leftChild = two;
        five.rightChild = six;

        System.out.println("Valid: " + checkSearchTree(five));

        six.leftChild = three;

        System.out.println("Invalid: " + checkSearchTree(five));
    }

    public <T extends Comparable<T>> boolean checkSearchTree(TreeNode<T> node) {
        return checkSearchTree(node, null, null);
    }

    private <T extends Comparable<T>> boolean checkSearchTree(TreeNode<T> node, T begin, T end) {
        if (node == null) {
            return true;
        }
        return checkInInterval(node.data, begin, end) 
                && checkSearchTree(node.leftChild, begin, node.data)
                && checkSearchTree(node.rightChild, node.data, end);
    }

    private <T extends Comparable<T>> boolean checkInInterval(T data, T begin, T end) {
        if (begin != null && data.compareTo(begin) < 0) {
            return false;
        }
        if (end != null && data.compareTo(end) > 0) {
            return false;
        }
        return true;
    }


    public class TreeNode<T> {

        public T data;
        public TreeNode<T> leftChild;
        public TreeNode<T> rightChild;

        public TreeNode(T data) {
            this.data = data;
        }

    }

}