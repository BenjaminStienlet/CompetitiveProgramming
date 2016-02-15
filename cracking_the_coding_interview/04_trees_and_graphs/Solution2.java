public class Solution2 {
    
    public static void main(String[] args) {
        new Solution2();
    }

    public Solution2() {
        System.out.println(arrayToSearchTree(new Integer[] {1,2,3,4,5,6,7,8,9}));
    }

    public <T> TreeNode<T> arrayToSearchTree(T[] array) {
        return arrayToSearchTree(array, 0, array.length-1);
    }

    private <T> TreeNode<T> arrayToSearchTree(T[] array, int begin, int end) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return new TreeNode<T>(array[begin]);
        }
        int mid = (int) Math.ceil((begin + end) / 2.0);
        TreeNode<T> node = new TreeNode<T>(array[mid]);
        node.leftChild = arrayToSearchTree(array, begin, mid-1);
        node.rightChild = arrayToSearchTree(array, mid+1, end);
        return node;
    }

    public class TreeNode<T> {

        public T data;
        public TreeNode<T> leftChild;
        public TreeNode<T> rightChild;

        public TreeNode(T data) {
            this.data = data;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(data + " ->");
            
            String left = "";
            if (leftChild != null) {
                builder.append(" " + leftChild.data);
                left = "\n" + leftChild.toString();
            } else {
                builder.append(" _");
            }
            
            String right = "";
            if (rightChild != null) {
                builder.append(" " + rightChild.data);
                right = "\n" + rightChild.toString();
            } else {
                builder.append(" _");
            }

            builder.append(left);
            builder.append(right);
            return builder.toString();
        }

    }

}