import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {
    
    public static void main(String[] args) {
        new Solution3();
    }

    public Solution3() {
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> two = new TreeNode<Integer>(2);
        TreeNode<Integer> three = new TreeNode<Integer>(3);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> five = new TreeNode<Integer>(5);

        one.leftChild = two;
        one.rightChild = three;
        two.leftChild = four;
        three.leftChild = five;

        ArrayList<LinkedList<TreeNode<Integer>>> result = treeToLists(one);
        for (LinkedList<TreeNode<Integer>> list : result) {
            for (TreeNode<Integer> node : list) {
                System.out.print(node.data + " ");
            }
            System.out.print("\n");
        }
    }

    public <T> ArrayList<LinkedList<TreeNode<T>>> treeToLists(TreeNode<T> root) {
        ArrayList<LinkedList<TreeNode<T>>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Queue<TreeNode<T>> nextQueue = new LinkedList<>();
            LinkedList<TreeNode<T>> list = new LinkedList<>();
            while(!queue.isEmpty()) {
                TreeNode<T> node = queue.remove();
                list.add(node);
                if (node.leftChild != null) {
                    nextQueue.add(node.leftChild);
                }
                if (node.rightChild != null) {
                    nextQueue.add(node.rightChild);
                }
            }
            queue = nextQueue;
            result.add(list);
        }
        return result;
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