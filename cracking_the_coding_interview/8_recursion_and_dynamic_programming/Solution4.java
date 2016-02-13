import java.util.ArrayList;

public class Solution4 {
    
    public static void main(String[] args) {
        new Solution4();
    }

    public Solution4() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(powerset(list));
    }

    public <T> ArrayList<ArrayList<T>> powerset(ArrayList<T> items) {
        ArrayList<ArrayList<T>> powerset;

        if (items.isEmpty()) {
            powerset = new ArrayList<ArrayList<T>>();
            powerset.add(new ArrayList<T>());
            return powerset;
        }

        T item = items.remove(0);
        powerset = powerset(items);

        ArrayList<ArrayList<T>> moreSets = new ArrayList<ArrayList<T>>();
        for (ArrayList<T> set : powerset) {
            ArrayList<T> setCopy = new ArrayList<T>(set);
            setCopy.add(item);
            moreSets.add(setCopy);
        }
        powerset.addAll(moreSets);

        return powerset;
    }

}