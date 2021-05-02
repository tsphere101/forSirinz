import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class CheckKind {

    public static void main(String[] args) {

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();

        arr1.addAll(Arrays.asList(1, 3, 4, 5, 7));

        arr2.addAll(Arrays.asList(1, 2, 3, 3, 3, 4, 4, 4, 6, 7, 9, 4, 5));

        Collections.sort(arr1);
        Collections.sort(arr2);

        System.out.println(arr1);

        System.out.println("HAS KIND::" + hasKind(arr1));
        System.out.println("AMOUNT OF KIND::" + getAmountOfKind(arr1));

    }

    public static ArrayList<ArrayList<Integer>> getKindIndex(ArrayList<Integer> arr) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        int indexOfSubArray = 0;
        int kindCount = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) == arr.get(i + 1)) {
                kindCount++;
                if (kindCount == 2) {
                    result.add(new ArrayList<Integer>());
                    result.get(indexOfSubArray).add(i - 1);
                    result.get(indexOfSubArray).add(i);
                    result.get(indexOfSubArray).add(i + 1);
                } else if (kindCount > 2) {
                    result.get(indexOfSubArray).add(i + 1);
                }
            } else {
                if (kindCount >= 2) {
                    indexOfSubArray++;
                }
                kindCount = 0;
            }
        }
        return (result.isEmpty()) ? null : result;

    }

    public static int getAmountOfKind(ArrayList<Integer> arr) {

        return getKindIndex(arr).size();

    }

}