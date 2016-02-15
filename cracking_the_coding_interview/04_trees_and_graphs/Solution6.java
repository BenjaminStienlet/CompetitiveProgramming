public class Solution6 {
    
    public static void main(String[] args) {
        new Solution6();
    }

    public Solution6() {
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> two = new TreeNode<Integer>(2);
        TreeNode<Integer> three = new TreeNode<Integer>(3);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> six = new TreeNode<Integer>(6);

        six.setLeftChild(four);
        four.setRightChild(five);
        four.setLeftChild(two);
        two.setRightChild(three);
        two.setLeftChild(one);

        System.out.println(inOrderSuccessor(one).getData());
        System.out.println(inOrderSuccessor(two).getData());
        System.out.println(inOrderSuccessor(three).getData());
        System.out.println(inOrderSuccessor(four).getData());
        System.out.println(inOrderSuccessor(five).getData());
    }

    // if has right-child: left-most child of right child
    // else if is left-child: parent
    // else parent of parent
    public <T> TreeNode<T> inOrderSuccessor(TreeNode<T> node) {
        if (node.getRightChild() != null) {
            return leftMostChild(node.getRightChild());
        } else if (node.getParent() != null && node.getParent().getLeftChild() == node) {
            return node.getParent();
        } else if (node.getParent() != null && node.getParent().getRightChild() == node) {
            return node.getParent().getParent();
        }
        return null;
    }

    private <T> TreeNode<T> leftMostChild(TreeNode<T> node) {
        if (node == null) {
            return null;    // error
        }
        if (node.getLeftChild() == null) {
            return node;
        }
        return leftMostChild(node.getLeftChild());
    }

    public class TreeNode<T> {

        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;
        private TreeNode<T> parent;

        public TreeNode(T data) {
            this.data = data;
        }

        public TreeNode<T> getLeftChild() {
            return leftChild;
        }

        public T getData() {
            return data;
        }

        public void setLeftChild(TreeNode<T> node) {
            if (node != null) {
                node.setParent(this);
            }
            leftChild = node;
        }

        public TreeNode<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode<T> node) {
            if (node != null) {
                node.setParent(this);
            }
            rightChild = node;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> node) {
            parent = node;
        }

    }

}