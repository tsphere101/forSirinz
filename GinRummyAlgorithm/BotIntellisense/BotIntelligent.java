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



        System.out.println(botIntelliDrop(arr).getValue());

    }

    public static MildCard botIntelliDrop(ArrayList<MildCard> arr) {
        Collections.sort(arr); /* Sort by deadwoodRank */

        for (int i = arr.size() - 1; i >= 1; i--) {
            if (arr.get(i).getValue() != arr.get(i - 1).getValue()) {

                if (!arr.get(i).getSuit().equals(arr.get(i - 1).getSuit())) {
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

        return null;

    }

}