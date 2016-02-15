public class Solution4 {
    
    public static void main(String[] args) {
        new Solution4();
    }

    public Solution4() {
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> two = new TreeNode<Integer>(2);
        TreeNode<Integer> three = new TreeNode<Integer>(3);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> five = new TreeNode<Integer>(5);

        one.leftChild = two;
        two.leftChild = four;

        System.out.println("Invalid: " + checkBalanced(one));

        one.rightChild = three;
        three.leftChild = five;

        System.out.println("Valid: " + checkBalanced(one));
    }

    public <T> boolean checkBalanced(TreeNode<T> node) {
        if (checkDepth(node) < 0) {
            return false;
        } else {
            return true;
        }
    }

    // Return -1 if unbalanced
    private <T> int checkDepth(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = checkDepth(node.leftChild);
        int rightDepth = checkDepth(node.rightChild);
        if (leftDepth < 0 || rightDepth < 0 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;  // Unbalanced
        }
        return Math.max(leftDepth, rightDepth) + 1;
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