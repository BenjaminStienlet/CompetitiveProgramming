import java.util.Stack;

class Solution5 {

    public static void main(String[] args) {
        new Solution5();
    }

    public Solution5() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(4);
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(5);
        System.out.println(stack);
        System.out.println(sortStack(stack));
    }

    public <T extends Comparable<T>> Stack<T> sortStack(Stack<T> stack) {
        Stack<T> sortedStack = new Stack<T>();

        while (!stack.isEmpty()) {
            T current = stack.pop();
            int count = 0;
            while(!sortedStack.isEmpty() && 
                sortedStack.peek().compareTo(current) < 0) {
                stack.push(sortedStack.pop());
            count++;
            }
            sortedStack.push(current);
            while(count > 0) {
                sortedStack.push(stack.pop());
                count--;
            }
        }

        return sortedStack;
    }

}