import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {

    public static void main(String[] args) {

        /* TEST CASE */

        ArrayList<String> listOfKind_string = new ArrayList<String>();
        listOfKind_string.add("1s");
        listOfKind_string.add("1d");
        listOfKind_string.add("1d");
        listOfKind_string.add("10c");
        listOfKind_string.add("10s");
        listOfKind_string.add("10d");

        ArrayList<String> listOfStraight_string = new ArrayList<String>();
        listOfStraight_string.add("3s");
        listOfStraight_string.add("4s");
        listOfStraight_string.add("5s");

        listOfStraight_string.add("ms");
        listOfStraight_string.add("ns");
        listOfStraight_string.add("os");

        ArrayList<String> listOfDeadwood_string = new ArrayList<String>();
        listOfDeadwood_string.add("1s");
        listOfDeadwood_string.add("2s");
        listOfDeadwood_string.add("6s");
        listOfDeadwood_string.add("7s");
        listOfDeadwood_string.add("8s");
        listOfDeadwood_string.add("9s");
        listOfDeadwood_string.add("ps");

        System.out.println(layoffScore(listOfKind_string, listOfStraight_string, listOfDeadwood_string));

    }

    public static int layoffScore(ArrayList<String> listOfKind_string, ArrayList<String> listOfStraight_string,
            ArrayList<String> listOfDeadwood_string) {

        try {
            if (listOfKind_string.isEmpty() || listOfKind_string == null) {
                throw new IllegalArgumentException("LIST OF KIND IS EMPTY OR BEING NULL.");
            }
            if (listOfStraight_string.isEmpty() || listOfStraight_string == null) {
                throw new IllegalArgumentException("LIST OF STRAIGHT IS EMPTY OR BEING NULL.");
            }
            if (listOfDeadwood_string.isEmpty() || listOfDeadwood_string == null) {
                throw new IllegalArgumentException("LIST OF DEADWOOD IS EMPTY OR BEING NULL.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }

        /* Convert ArrayList<String> type to ArrayList<MildCard> */

        ArrayList<MildCard> kindList = new ArrayList<MildCard>();
        ArrayList<MildCard> straightList = new ArrayList<MildCard>();
        ArrayList<MildCard> deadwoodList = new ArrayList<MildCard>();

        /* "1s" -> ACE, SLADE */

        /* Conver list of kind string to ArrayList<MildCard> */
        for (int i = 0; i < listOfKind_string.size(); i++) {

            int value;
            String suit = "";
            if (Character.toString(listOfKind_string.get(i).charAt(0)).equals("m")) {
                value = 10;
                suit = Character.toString(listOfKind_string.get(i).charAt(1));
            } else if (Character.toString(listOfKind_string.get(i).charAt(0)).equals("n")) {
                value = 11;
                suit = Character.toString(listOfKind_string.get(i).charAt(1));
            } else if (Character.toString(listOfKind_string.get(i).charAt(0)).equals("o")) {
                value = 12;
                suit = Character.toString(listOfKind_string.get(i).charAt(1));
            } else if (Character.toString(listOfKind_string.get(i).charAt(0)).equals("p")) {
                value = 13;
                suit = Character.toString(listOfKind_string.get(i).charAt(1));
            } else {
                value = Integer.parseInt(Character.toString(listOfKind_string.get(i).charAt(0)));
                suit = Character.toString(listOfKind_string.get(i).charAt(1));
            }
            kindList.add(new MildCard(value, suit));
        }
        Collections.sort(kindList);

        /* List of Straight */
        for (int i = 0; i < listOfStraight_string.size(); i++) {
            int value;
            String suit = "";
            if (Character.toString(listOfStraight_string.get(i).charAt(0)).equals("m")) {
                value = 10;
                suit = Character.toString(listOfStraight_string.get(i).charAt(1));
            } else if (Character.toString(listOfStraight_string.get(i).charAt(0)).equals("n")) {
                value = 11;
                suit = Character.toString(listOfStraight_string.get(i).charAt(1));
            } else if (Character.toString(listOfStraight_string.get(i).charAt(0)).equals("o")) {
                value = 12;
                suit = Character.toString(listOfStraight_string.get(i).charAt(1));
            } else if (Character.toString(listOfStraight_string.get(i).charAt(0)).equals("p")) {
                value = 13;
                suit = Character.toString(listOfStraight_string.get(i).charAt(1));
            } else {
                value = Integer.parseInt(Character.toString(listOfStraight_string.get(i).charAt(0)));
                suit = Character.toString(listOfStraight_string.get(i).charAt(1));
            }
            straightList.add(new MildCard(value, suit));
        }
        Collections.sort(straightList);

        /* List of Deadwood */
        for (int i = 0; i < listOfDeadwood_string.size(); i++) {
            int value;
            String suit = "";
            if (Character.toString(listOfDeadwood_string.get(i).charAt(0)).equals("m")) {
                value = 10;
                suit = Character.toString(listOfDeadwood_string.get(i).charAt(1));
            } else if (Character.toString(listOfDeadwood_string.get(i).charAt(0)).equals("n")) {
                value = 11;
                suit = Character.toString(listOfDeadwood_string.get(i).charAt(1));
            } else if (Character.toString(listOfDeadwood_string.get(i).charAt(0)).equals("o")) {
                value = 12;
                suit = Character.toString(listOfDeadwood_string.get(i).charAt(1));
            } else if (Character.toString(listOfDeadwood_string.get(i).charAt(0)).equals("p")) {
                value = 13;
                suit = Character.toString(listOfDeadwood_string.get(i).charAt(1));
            } else {
                value = Integer.parseInt(Character.toString(listOfDeadwood_string.get(i).charAt(0)));
                suit = Character.toString(listOfDeadwood_string.get(i).charAt(1));
            }
            deadwoodList.add(new MildCard(value,suit));
        }
        Collections.sort(deadwoodList);
        /* FINISHED CONVERT FROM ARRAYLIST OF STRING TO ARRAYLIST OF MILDCARD */

        /*
         * Convert from arrayList of MILDCARD to ArrayList of integer(rank value) to be
         * checked in getKindIndex(ArrayList<Integer>)
         */
        ArrayList<Integer> kindList_value = new ArrayList<Integer>();
        for (int i = 0; i < kindList.size(); i++) {
            kindList_value.add(kindList.get(i).getValue());
        }

        /* Check if in kindlist hadn't kind occur */
        try {
            if (getKindIndex(kindList_value).size() == 0) {
                /* NO KIND OCCUR */
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return -1;
        }

        /* Check if in straightlist had straight occur */
        /* has to be matched in their own suit */
        /** CHECK SUIT FIRST! */

        /**
         * Sparate in their own suit CLUBS HEART DIAMOND SPLADE
         */
        ArrayList<MildCard> straight_clubs = new ArrayList<MildCard>();
        ArrayList<MildCard> straight_heart = new ArrayList<MildCard>();
        ArrayList<MildCard> straight_diamond = new ArrayList<MildCard>();
        ArrayList<MildCard> straight_spade = new ArrayList<MildCard>();

        System.out.println(straightList);
        System.out.println("STRAIGHTLIST::SIZE()::" + straightList.size());
        for (int i = 0; i < straightList.size(); i++) {
            if (straightList.get(i).getSuit().equals("c")) {
                straight_clubs.add(straightList.get(i));
            } else if (straightList.get(i).getSuit().equals("h")) {
                straight_heart.add(straightList.get(i));
                System.out.println("ADDED TO HEART");
                System.out.println(straightList.get(i));
            } else if (straightList.get(i).getSuit().equals("d")) {
                straight_diamond.add(straightList.get(i));
            } else if (straightList.get(i).getSuit().equals("s")) {
                straight_spade.add(straightList.get(i));
            }
            System.out.println("ITERATE");
        }
        /* FINISHED SEPARATE CARDS IN THEIR OWN SUIT */
        System.out.println("xSTRAIGHT_CLUBS::SIZE()::" + straight_clubs.size());
        System.out.println("xSTRAIGHT_HEART::SIZE()::" + straight_heart.size());
        System.out.println("xSTRAIGHT_DIAMODN::SIZE()::" + straight_diamond.size());
        System.out.println("xSTRAIGHT_SPADE::SIZE()::" + straight_spade.size());

        /**
         * Convert each suit list to be ArrayList<Integer> in order to be checked in
         * getStraightIndex(ArrayList<Integer>)
         */
        ArrayList<Integer> straight_clubs_value = new ArrayList<Integer>();
        ArrayList<Integer> straight_heart_value = new ArrayList<Integer>();
        ArrayList<Integer> straight_diamond_value = new ArrayList<Integer>();
        ArrayList<Integer> straight_spade_value = new ArrayList<Integer>();

        /*
         * Converting ArrayList<MildClub> straight_clubs to ArrayList<Integer>
         * straight_clubs_value
         */
        for (int i = 0; i < straight_clubs.size(); i++) {
            straight_clubs_value.add(Integer.valueOf(straight_clubs.get(i).getValue()));
        }
        /*
         * Converting ArrayList<MildClub> straight_heart to ArrayList<Integer>
         * straight_heart_value
         */
        for (int i = 0; i < straight_heart.size(); i++) {
            straight_heart_value.add(Integer.valueOf(straight_heart.get(i).getValue()));
        }
        /*
         * Converting ArrayList<MildClub> straight_diamond to ArrayList<Integer>
         * straight_diamond_value
         */
        for (int i = 0; i < straight_diamond.size(); i++) {
            straight_diamond_value.add(Integer.valueOf(straight_diamond.get(i).getValue()));
        }
        /*
         * Converting ArrayList<MildClub> straight_spade to ArrayList<Integer>
         * straight_spade_value
         */
        for (int i = 0; i < straight_spade.size(); i++) {
            straight_spade_value.add(Integer.valueOf(straight_spade.get(i).getValue()));
        }

        System.out.println(straight_clubs_value);
        System.out.println(straight_heart_value);
        System.out.println(straight_diamond_value);
        System.out.println(straight_spade_value);

        /* Check if Straight hadn't been occured */
        ArrayList<ArrayList<Integer>> club_straight_index = getStraightIndex(straight_clubs_value);
        ArrayList<ArrayList<Integer>> heart_straight_index = getStraightIndex(straight_heart_value);
        ArrayList<ArrayList<Integer>> diamond_straight_index = getStraightIndex(straight_diamond_value);
        ArrayList<ArrayList<Integer>> spade_straight_index = getStraightIndex(straight_spade_value);
        try {
            System.out.println(club_straight_index);
            System.out.println(heart_straight_index);
            System.out.println(diamond_straight_index);
            System.out.println(spade_straight_index);
            if ((club_straight_index == null) && (heart_straight_index == null) && (diamond_straight_index == null)
                    && (spade_straight_index == null)) {

                throw new IllegalArgumentException("NO STRAIGHT OCCUR IN ANY SUIT.");

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return -1;
        }

        /* Layoff check */

        int result_score = 0;

        /* check if can be banked in kind. */
        System.out.println("W(TSPEHRE)" + kindList.get(getKindIndex(kindList_value).get(0).get(0)).getValue());

        for (int i = 0; i < deadwoodList.size(); i++) {

            System.out.println("GETSCORE()::" + deadwoodList.get(i).getScore());
            if (kindList.get(getKindIndex(kindList_value).get(0).get(0)).getValue() == deadwoodList.get(i).getValue()) {
                /* if matched, deadwoodlist.remove(i) */
                /* result_score = result_score + deadwoodLlist.getScore() */
                System.out.println("GETSCORE()::" + deadwoodList.get(i).getScore());
                result_score += deadwoodList.remove(i).getScore();

            }

        }

        System.out.println("LAYOFF_SCORE_IS::" + result_score);

        /* check if can be banked in straight */

        // Clubs straight check , check if can be banked in straight club.
        if (club_straight_index != null) {
            /* has straight occured in club */

            Collections.sort(straight_clubs);

            for (int j = 0; j < club_straight_index.size(); j++) {
                boolean isCombo = true;
                int q = 0;
                while (isCombo && q < deadwoodList.size() && q < deadwoodList.size())

                    /* check if it can be place in the bottom */
                    if ((straight_clubs.get(club_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(q).getValue() + 1)
                            && deadwoodList.get(q).getSuit().equals("c")) {
                        /* can be placed in the bottom of the straighted card */
                        straight_clubs.add(deadwoodList.get(q)); // Add deadwood card to bank of straight
                        straight_clubs_value.add(deadwoodList.get(q).getValue()); // Add deadwood rank to bank value
                        Collections.sort(straight_clubs);
                        Collections.sort(straight_clubs_value);
                        club_straight_index = getStraightIndex(straight_clubs_value); // recall the straight index.
                        result_score += deadwoodList.remove(q).getScore(); // add score and remove.
                        isCombo = true;
                        q = 0;
                    }

                    /* check if it can be placed in the upper */
                    else if (straight_clubs.get(club_straight_index.get(j).get(club_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(q).getValue() - 1) {
                        /* can be placed at the upper of the straighted card */
                        straight_clubs.add(deadwoodList.get(q));
                        straight_clubs_value.add(deadwoodList.get(q).getValue());
                        Collections.sort(straight_clubs);
                        Collections.sort(straight_clubs_value);
                        club_straight_index = getStraightIndex(straight_clubs_value);
                        result_score += deadwoodList.remove(q).getScore();
                        isCombo = true;
                        q = 0;
                    }

                    else {
                        /* No more straight combo can be placed */
                        q++;

                    }

            }

        }

        // Heart straight check , check if can be banked in straight heart.
        if (heart_straight_index != null) {
            /* has straight occured in heart */
            System.out.println("HEART_STRAIGHT_INDEX is not null");
            System.out.println("HEART_STRAIGHT_INDEX::SIZE::" + heart_straight_index.size());
            Collections.sort(straight_heart);
            System.out.println(straight_heart);
            for (int j = 0; j < heart_straight_index.size(); j++) {
                boolean isCombo = true;
                int q = 0;
                while (isCombo && q < straight_heart.size() && q < deadwoodList.size()) {

                    /* check if it can be place in the bottom */
                    if ((straight_heart.get(heart_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(q).getValue() + 1)
                            && deadwoodList.get(q).getSuit().equals("h")) {
                        /* can be placed in the bottom of the straighted card */
                        System.out.println("CAN BE PLACE IN THE BOTTOM OF THE STRAIGHT CARD" + deadwoodList.get(q));
                        straight_heart.add(deadwoodList.get(q)); // Add deadwood card to bank of straight
                        straight_heart_value.add(deadwoodList.get(q).getValue()); // Add deadwood rank to bank value
                        Collections.sort(straight_heart);
                        Collections.sort(straight_heart_value);
                        heart_straight_index = getStraightIndex(straight_heart_value); // recall the straight index.
                        result_score += deadwoodList.remove(q).getScore(); // add score and remove.
                        isCombo = true;
                        q = 0;
                    }

                    /* check if it can be placed in the upper */
                    else if (straight_heart.get(heart_straight_index.get(j).get(heart_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(q).getValue() - 1) {
                        /* can be placed at the upper of the straighted card */
                        System.out.println("CAN BE PLACE AT THE UPPER OF THE STRAIGHT CARD" + deadwoodList.get(q));
                        System.out.println("LEFT IN DEADWOOD::"+deadwoodList);
                        straight_heart.add(deadwoodList.get(q));
                        straight_heart_value.add(deadwoodList.get(q).getValue());
                        Collections.sort(straight_heart);
                        Collections.sort(straight_heart_value);
                        heart_straight_index = getStraightIndex(straight_heart_value);
                        result_score += deadwoodList.remove(q).getScore();
                        isCombo = true;
                        q = 0;
                    }

                    else {
                        /* No more straight combo can be placed */
                        q++;

                    }
                }

            }

        }

        // Diamond straight check , check if can be banked in straight diamond.
        if (diamond_straight_index != null) {
            /* has straight occured in diamond */

            Collections.sort(straight_diamond);

            for (int j = 0; j < diamond_straight_index.size(); j++) {
                boolean isCombo = true;
                int q = 0;
                while (isCombo && q < straight_diamond.size() && q < deadwoodList.size())

                    /* check if it can be place in the bottom */
                    if ((straight_diamond.get(diamond_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(q).getValue() + 1)
                            && deadwoodList.get(q).getSuit().equals("d")) {
                        /* can be placed in the bottom of the straighted card */
                        straight_diamond.add(deadwoodList.get(q)); // Add deadwood card to bank of straight
                        straight_diamond_value.add(deadwoodList.get(q).getValue()); // Add deadwood rank to bank value
                        Collections.sort(straight_diamond);
                        Collections.sort(straight_diamond_value);
                        diamond_straight_index = getStraightIndex(straight_diamond_value); // recall the straight index.
                        result_score += deadwoodList.remove(q).getScore(); // add score and remove.
                        isCombo = true;
                        q = 0;
                    }

                    /* check if it can be placed in the upper */
                    else if (straight_diamond
                            .get(diamond_straight_index.get(j).get(diamond_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(q).getValue() - 1) {
                        /* can be placed at the upper of the straighted card */
                        straight_diamond.add(deadwoodList.get(q));
                        straight_diamond_value.add(deadwoodList.get(q).getValue());
                        Collections.sort(straight_diamond);
                        Collections.sort(straight_diamond_value);
                        diamond_straight_index = getStraightIndex(straight_diamond_value);
                        result_score += deadwoodList.remove(q).getScore();
                        isCombo = true;
                        q = 0;
                    }

                    else {
                        /* No more straight combo can be placed */
                        q++;

                    }

            }

        }

        // spade straight check , check if can be banked in straight spade.
        if (spade_straight_index != null) {
            /* has straight occured in spade */

            Collections.sort(straight_spade);

            for (int j = 0; j < spade_straight_index.size(); j++) {
                boolean isCombo = true;
                int q = 0;
                while (isCombo && q < straight_spade.size() && q < deadwoodList.size())

                    /* check if it can be place in the bottom */
                    if ((straight_spade.get(spade_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(q).getValue() + 1)
                            && deadwoodList.get(q).getSuit().equals("s")) {
                        /* can be placed in the bottom of the straighted card */
                        straight_spade.add(deadwoodList.get(q)); // Add deadwood card to bank of straight
                        straight_spade_value.add(deadwoodList.get(q).getValue()); // Add deadwood rank to bank value
                        Collections.sort(straight_spade);
                        Collections.sort(straight_spade_value);
                        spade_straight_index = getStraightIndex(straight_spade_value); // recall the straight index.
                        result_score += deadwoodList.remove(q).getScore(); // add score and remove.
                        isCombo = true;
                        q = 0;
                    }

                    /* check if it can be placed in the upper */
                    else if (straight_spade.get(spade_straight_index.get(j).get(spade_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(q).getValue() - 1) {
                        /* can be placed at the upper of the straighted card */
                        straight_spade.add(deadwoodList.get(q));
                        straight_spade_value.add(deadwoodList.get(q).getValue());
                        Collections.sort(straight_spade);
                        Collections.sort(straight_spade_value);
                        spade_straight_index = getStraightIndex(straight_spade_value);
                        result_score += deadwoodList.remove(q).getScore();
                        isCombo = true;
                        q = 0;
                    }

                    else {
                        /* No more straight combo can be placed */
                        q++;

                    }

            }

        }

        return result_score;
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

    public static ArrayList<ArrayList<Integer>> getStraightIndex(ArrayList<Integer> arr) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        int indexOfSubArray = 0;
        boolean continuity = false;
        int straightCount = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i + 1) - arr.get(i) == 1 && i != arr.size() - 2) {
                /* If the next number is more than the current number for 1 (Sequencely) */
                continuity = true;
                straightCount++;
            } else {
                /* No more continue */
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
                        Collections.sort(result.get(indexOfSubArray));
                        /* Sort back to 4 5 6 */
                        indexOfSubArray++;
                        continuity = false;
                        straightCount = 0;
                    } else {
                        straightCount = 0;
                        /* Not continue and straight count is not more than 2 */
                    }
                    continuity = false;
                }

            }
        }

        return (result.isEmpty()) ? null : result;
    }

}