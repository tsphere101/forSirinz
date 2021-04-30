import java.util.Collections;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        ArrayList<Integer> arr3 = new ArrayList<Integer>();
        ArrayList<Integer> arr4 = new ArrayList<Integer>();

        arr1.add(9);
        arr1.add(1);
        arr1.add(2);
        arr1.add(5);
        arr1.add(6);

        arr2.add(1);
        arr2.add(4);
        arr2.add(5);
        arr2.add(6);
        arr2.add(9);

        arr3.add(1);
        arr3.add(2);
        arr3.add(3);
        arr3.add(5);
        arr3.add(6);
        arr3.add(7);
        arr3.add(9);
        arr3.add(10);
        arr3.add(11);
        arr3.add(12);
        arr3.add(13);

        arr4.add(1);
        arr4.add(2);
        arr4.add(4);
        arr4.add(5);
        arr4.add(6);
        arr4.add(7);
        arr4.add(13);
        arr4.add(12);
        arr4.add(10);
        arr4.add(9);

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
        System.out.println("arr4 has stf : " + getStraightMatchedIndex(arr4));

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
        if (hasStraight(arr) == 0)
            /* IF the array has no straight */
            return null;

        Collections.sort(arr);

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        int amount_of_straight_matched = hasStraight(arr);
        for (int i = 0; i < amount_of_straight_matched; i++) {
            /* pre-create instance of the ArrayList<Integer> in the result ArrayList */
            result.add(new ArrayList<Integer>());
        }

        /* Index of sub array in the result array */
        int subArrayIndex = 0;
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
                        for (int k = 0; k < straightCount + 1; k++) {
                            /* Add index number from here until previous ex. 4 5 6 -> adding : 6 5 4 */
                            result.get(subArrayIndex).add(i - k);
                        }
                        Collections.sort(result.get(subArrayIndex)); /* Sort back to 4 5 6 */
                        subArrayIndex++;
                        continuity = false;
                        straightCount = 0;
                    } else {
                        straightCount = 0; /* Not continue and straight count is not more than 2 */
                    }
                    continuity = false;
                }

            }
        }
        return result;
    }

}
