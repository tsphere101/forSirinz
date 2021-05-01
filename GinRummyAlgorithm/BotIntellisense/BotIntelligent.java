import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BotIntelligent {

    public static void main(String[] args) {

        ArrayList<MildCard> arr = new ArrayList<MildCard>();

        arr.add(new MildCard(8, "c"));
        arr.add(new MildCard(1, "s"));
        arr.add(new MildCard(3, "h"));
        arr.add(new MildCard(4, "d"));
        arr.add(new MildCard(9, "c"));
        arr.add(new MildCard(4, "s"));

        ArrayList<MildCard> arr1 = new ArrayList<MildCard>();
        arr1.add(new MildCard(5, "c"));
        arr1.add(new MildCard(6, "c"));
        arr1.add(new MildCard(2, "s"));
        arr1.add(new MildCard(2, "h"));
        arr1.add(new MildCard(1, "d"));

        ArrayList<MildCard> arr2 = new ArrayList<MildCard>();
        arr2.add(new MildCard(2, "s"));
        arr2.add(new MildCard(3, "s"));
        arr2.add(new MildCard(6, "d"));
        arr2.add(new MildCard(7, "d"));

        ArrayList<MildCard> arr3 = new ArrayList<MildCard>();
        arr3.add(new MildCard(5, "c"));
        arr3.add(new MildCard(6, "c"));
        arr3.add(new MildCard(2, "s"));
        arr3.add(new MildCard(3, "s"));
        arr3.add(new MildCard(1, "d"));

        ArrayList<MildCard> arr4= new ArrayList<MildCard>();
        arr4.add(new MildCard(5, "c"));
        arr4.add(new MildCard(7, "c"));
        arr4.add(new MildCard(6, "s"));
        arr4.add(new MildCard(8, "d"));

        System.out.println(botIntelliDrop(arr4).getValue() + "SUIT::" + botIntelliDrop(arr4).getSuit());

    }

    public static MildCard botIntelliDrop(ArrayList<MildCard> arr) {

        Collections.sort(arr);
        ArrayList<MildCard> duplicate = new ArrayList<MildCard>();
        ArrayList<MildCard> duplicateForNull = new ArrayList<MildCard>();
        // for (int i = 0; i < duplicate.size(); i++) {
        //     duplicate.add(arr.get(i));
        //     duplicateForNull.add(arr.get(i));
        // }

        duplicate = (ArrayList<MildCard>) arr.clone();
        duplicateForNull = (ArrayList<MildCard>) arr.clone();
        
        System.out.println("ARR ARRAYLIST SIZE :: " + arr.size());
        System.out.println("DUPLICATE ARRAYLIST SIZE :: " + duplicate.size());
        System.out.println("DUPLICATEFORNULL ARRAYLIST SIZE :: " + duplicateForNull.size());

        int i = duplicate.size() - 1;
        while (i > 0) {
            if (duplicate.get(i).getValue() == duplicate.get(i - 1).getValue()) {
                duplicate.remove(i);
                duplicate.remove(i - 1);
                i--;
            } else if (duplicate.get(i).getValue() != duplicate.get(i - 1).getValue()
                    && duplicate.get(i).getSuit().equals(duplicate.get(i - 1).getSuit())) {
                duplicate.remove(i);
                duplicate.remove(i - 1);
                i--;
            }

            i--;
            if (i <= 0) {
                System.out.println("CRITICAl VALUE OF I :: " + i);
            }
        }

        if (duplicate.isEmpty()) {
            System.out.println("ALL IN HAND(TSPEHRE)");
            return duplicateForNull.get(duplicateForNull.size() - 1);
        }

        return duplicate.get(duplicate.size() - 1);

    }

    public static MildCard botIntelliDropNEEDTOFIXBUG(ArrayList<MildCard> arr) {
        Collections.sort(arr); /* Sort by deadwoodRank */
        System.out.println(arr);

        for (int i = arr.size() - 1; i >= 1; i--) {
            if (arr.get(i).getValue() != arr.get(i - 1).getValue()) {
                if (!arr.get(i).getSuit().equals(arr.get(i - 1).getSuit())) {
                    System.out.println(arr.get(i));
                    return arr.get(i); // NO FRIEND, NO BROTHER
                } else {
                    // HAS 1 BROTHER (SAME SUIT)
                    i--;
                }

            } else {
                // HAS 1 FRIEND (SAME RANK)
                i--;
            }
        }

        System.out.println("BOTINTELLIDROP RETURNED NULL");

        return null;

    }

}