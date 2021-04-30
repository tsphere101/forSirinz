import java.util.ArrayList;
import java.util.Collections;

public class CheckKind {

    public static void main(String[] args) {

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        arr1.add(1);
        arr1.add(3);
        arr1.add(4);
        arr1.add(5);
        arr1.add(7);

        Collections.sort(arr1);

        System.out.println(arr1);

    }

    public static ArrayList<ArrayList<Integer>> hasKind(ArrayList<Integer> arr) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Collections.sort(arr);

        /*  */
        int kindCount = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) == arr.get(i + 1)) {
                kindCount++;
            }

        }

        return result;

    }

}