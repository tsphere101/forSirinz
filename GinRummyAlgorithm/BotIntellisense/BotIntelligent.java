import java.util.ArrayList;
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

        ArrayList<MildCard> arr4 = new ArrayList<MildCard>();
        arr4.add(new MildCard(8, "d"));
        arr4.add(new MildCard(9, "d"));
        arr4.add(new MildCard(3, "h"));
        arr4.add(new MildCard(9, "c"));
        arr4.add(new MildCard(7, "s"));

        System.out.println(botIntelliDrop(arr4).getValue() + "SUIT::" + botIntelliDrop(arr4).getSuit());

    }

    public static MildCard botIntelliDrop(ArrayList<MildCard> arr) {

        Collections.sort(arr);
        ArrayList<MildCard> duplicate = new ArrayList<MildCard>();
        ArrayList<MildCard> duplicateForNull = new ArrayList<MildCard>();

        duplicate = (ArrayList<MildCard>) arr.clone();
        duplicateForNull = (ArrayList<MildCard>) arr.clone();

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
        }
        if (duplicate.isEmpty()) {
            return duplicateForNull.get(duplicateForNull.size() - 1);
        }
        return duplicate.get(duplicate.size() - 1);
    }

}