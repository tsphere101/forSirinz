import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        ArrayList<Integer> arr3 = new ArrayList<Integer>();
        ArrayList<Integer> arr4 = new ArrayList<Integer>();

        arr1.addAll(Arrays.asList(9, 1, 2, 5, 6));

        arr2.addAll(Arrays.asList(1, 4, 5, 6, 9));

        arr3.addAll(Arrays.asList(1, 2, 3, 5, 6, 7, 8, 10, 11, 12, 13));

        arr4.addAll(Arrays.asList(1, 2, 4, 5, 6, 7, 9, 10, 12, 13));

        /* Sorting array */
        Collections.sort(arr1);
        Collections.sort(arr2);
        Collections.sort(arr3);
        Collections.sort(arr4);

        /* Print the array */
        System.out.println(arr4);

        // System.out.println("arr1 has stf : " + getStraightMatchedIndex(arr1));
        // System.out.println("arr2 has stf : " + getStraightMatchedIndex(arr2));
        // System.out.println("arr3 has stf : " + getStraightMatchedIndex(arr3));

        // System.out.println("arr4 has stf amount :: " + hasStraight(arr4));
        System.out.println("arr4 HAS STRAIGHT : " + getStraightMatchedIndex(arr4));

    }

    public static int hasStraight(ArrayList<Integer> arr) {

        /* Sort before matching */
        Collections.sort(arr);

        boolean isContinue = false;

        /*
         * Straight count will track the amount of pair which are consequently match ex.
         * if straightCount is 3 -> means the amount of card has straight is 4.
         */
        int straightCount = 0;

        /* the amount of set that are being straight */
        int amountOfMatched = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i + 1) - arr.get(i) == 1 && i != arr.size() - 2) {
                /* If the next number is more than the current number for 1 (Sequencely) */
                isContinue = true;
                straightCount++; /* track amount of straight */
            } else { /* No more continue */
                if (isContinue == true) {
                    if (straightCount >= 2) {
                        /* They are straighted */
                        amountOfMatched++;
                        straightCount = 0;
                    } else {
                        straightCount = 0; /* reset count back to 0 */
                    }
                    isContinue = false; /* Not continue. */
                }

            }
        }
        return amountOfMatched;
    }

    public static ArrayList<ArrayList<Integer>> getStraightMatchedIndex(ArrayList<Integer> arr) {

        Collections.sort(arr);

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        int indexOfSubArray = 0;
        boolean continuity = false;
        int straightCount = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i + 1) - arr.get(i) == 1 && i != arr.size() - 2) {
                /* If the next number is more than the current number for 1 (Sequencely) */
                continuity = true;
                straightCount++;
            } else { /* No more continue */
                if (continuity == true && arr.size() - 2 == i) {
                    /* case of the last 2 index of the array */
                    if (arr.get(arr.size() - 1) - arr.get(arr.size() - 2) == 1) {
                        /* They are straight */
                        straightCount++;
                        i++;
                    }
                }
                if (continuity == true) {
                    if (straightCount >= 2) {
                        /* They are straight */
                        result.add(new ArrayList<Integer>());
                        for (int k = 0; k < straightCount + 1; k++) {
                            /* Add index number from here until previous ex. 4 5 6 -> adding : 6 5 4 */
                            result.get(indexOfSubArray).add(i - k);
                        }
                        Collections.sort(result.get(indexOfSubArray)); /* Sort back to 4 5 6 */
                        indexOfSubArray++;
                        continuity = false;
                        straightCount = 0;
                    } else {
                        straightCount = 0; /* Not continue and straight count is not more than 2 */
                    }
                    continuity = false;
                }

            }
        }

        return (result.isEmpty()) ? null : result;
    }

}
