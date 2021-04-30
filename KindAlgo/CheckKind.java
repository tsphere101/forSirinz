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

    public static ArrayList<ArrayList<Integer>> hasKind(ArrayList<Integer> arr) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Collections.sort(arr);

        int indexOfSubArray = 0;
        int kindCount = 0;
        boolean isContinue = false;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) == arr.get(i + 1)) {
                kindCount++;
                isContinue = true;
            } else {
                /* No more continue. */
                if (isContinue && kindCount >= 2) {
                    /* They are kind. */
                    result.add(new ArrayList<Integer>());
                    /* Add the continue cards into the result array */
                    for (int k = kindCount + 1; k > 0 + 1; k--) {
                        /* Add index in the sub array */
                        result.get(indexOfSubArray).add(i - k);
                    }
                    indexOfSubArray++;
                }

                isContinue = false;
                kindCount = 0;
            }

        }

        return (result.isEmpty()) ? null : result;

    }

    public static int getAmountOfKind(ArrayList<Integer> arr) {
        Collections.sort(arr);

        int amountOfKind = 0;
        int kindCount = 0;
        boolean isContinue = false;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) == arr.get(i + 1)) {
                kindCount++;
                isContinue = true;
            } else {
                /* No more continue. */
                if (isContinue && kindCount >= 2) {
                    /* They are kind. */
                    amountOfKind++;
                }

                isContinue = false;
                kindCount = 0;
            }

        }

        return amountOfKind;

    }

}