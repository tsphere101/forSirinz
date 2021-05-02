import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {

    public static void main(String[] args) {

        /* TEST CASE */

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
            int value = Integer.parseInt(Character.toString(listOfKind_string.get(i).charAt(0)));
            String suit = Character.toString(listOfKind_string.get(i).charAt(1));
            kindList.add(new MildCard(value, suit));
        }
        Collections.sort(kindList);

        /* List of Straight */
        for (int i = 0; i < listOfStraight_string.size(); i++) {
            int value = Integer.parseInt(Character.toString(listOfStraight_string.get(i).charAt(0)));
            String suit = Character.toString(listOfStraight_string.get(i).charAt(1));
            straightList.add(new MildCard(value, suit));
        }
        Collections.sort(straightList);

        /* List of Deadwood */
        for (int i = 0; i < listOfDeadwood_string.size(); i++) {
            int value = Integer.parseInt(Character.toString(listOfDeadwood_string.get(i).charAt(0)));
            String suit = Character.toString(listOfDeadwood_string.get(i).charAt(1));
            deadwoodList.add(new MildCard(value, suit));
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

        for (int i = 0; i < straightList.size(); i++) {
            if (straightList.get(i).getSuit().equals("c")) {
                straight_clubs.add(straightList.get(i));
            } else if (straightList.get(i).getSuit().equals("h")) {
                straight_heart.add(straightList.get(i));
            } else if (straightList.get(i).getSuit().equals("d")) {
                straight_diamond.add(straightList.get(i));
            } else if (straightList.get(i).getSuit().equals("s")) {
                straight_spade.add(straightList.get(i));
            }
        }
        /* FINISHED SEPARATE CARDS IN THEIR OWN SUIT */

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
            straight_clubs_value.add(straight_clubs.get(i).getValue());
        }
        /*
         * Converting ArrayList<MildClub> straight_heart to ArrayList<Integer>
         * straight_heart_value
         */
        for (int i = 0; i < straight_clubs.size(); i++) {
            straight_heart_value.add(straight_heart.get(i).getValue());
        }
        /*
         * Converting ArrayList<MildClub> straight_diamond to ArrayList<Integer>
         * straight_diamond_value
         */
        for (int i = 0; i < straight_clubs.size(); i++) {
            straight_diamond_value.add(straight_diamond.get(i).getValue());
        }
        /*
         * Converting ArrayList<MildClub> straight_spade to ArrayList<Integer>
         * straight_spade_value
         */
        for (int i = 0; i < straight_clubs.size(); i++) {
            straight_spade_value.add(straight_spade.get(i).getValue());
        }

        /* Check if Straight hadn't been occured */
        ArrayList<ArrayList<Integer>> club_straight_index = getStraightIndex(straight_clubs_value);
        ArrayList<ArrayList<Integer>> heart_straight_index = getStraightIndex(straight_heart_value);
        ArrayList<ArrayList<Integer>> diamond_straight_index = getStraightIndex(straight_diamond_value);
        ArrayList<ArrayList<Integer>> spade_straight_index = getStraightIndex(straight_spade_value);
        try {

            if ((club_straight_index.isEmpty() || club_straight_index == null)
                    && (heart_straight_index.isEmpty() || heart_straight_index == null)
                    && (diamond_straight_index.isEmpty() || diamond_straight_index == null)
                    && (spade_straight_index.isEmpty() || spade_straight_index == null)) {

                throw new IllegalArgumentException("NO STRAIGHT OCCUR IN ANY SUIT.");

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return -1;
        }

        /* Layoff check */

        int result_score = 0;

        /* check if can be banked in kind. */
        for (int i = 0; i < deadwoodList.size(); i++) {

            if (getKindIndex(kindList_value).get(0).get(0) == deadwoodList.get(i).getValue()) {
                /* if matched, deadwoodlist.remove(i) */
                /* result_score = result_score + deadwoodLlist.getScore() */
                result_score += deadwoodList.remove(i).getScore();

                System.out.println("LAYOFF_SCORE_IS::" + result_score);

            }

        }

        System.out.println("LAYOFF_SCORE_IS::" + result_score);

        /* check if can be banked in straight */
        for (int i = 0; i < deadwoodList.size(); i++) {

            // Clubs straight check
            if (!club_straight_index.isEmpty() && club_straight_index != null) {
                /* has straight occured in club */

                Collections.sort(straight_clubs);

                for (int j = 0; j < club_straight_index.size(); j++) {
                    boolean isCombo = true;
                    int q = 0;
                    while (isCombo && q < deadwoodList.size())

                        /* check if it can be place in the bottom */
                        if ((straight_clubs.get(club_straight_index.get(j).get(0))
                                .getValue() == deadwoodList.get(q).getValue() + 1)
                                && deadwoodList.get(q).getSuit().equals("c")) {
                            /* can be placed in the bottom of the straighted card */
                            result_score += deadwoodList.remove(q).getScore();
                            isCombo = true;
                            q = 0;
                        }

                        /* check if it can be placed in the upper */
                        else if (straight_clubs
                                .get(club_straight_index.get(j).get(club_straight_index.get(j).size() - 1))
                                .getValue() == deadwoodList.get(q).getValue() - 1) {
                            /* can be placed at the upper of the straighted card */
                            result_score += deadwoodList.remove(q).getScore();
                            isCombo = true;
                            q = 0;
                        }

                        else {
                            /* No more straight combo can be placed */
                            isCombo = false;
                            q++;

                        }

                }

            }

            // Heart straight check
            if (!heart_straight_index.isEmpty() && heart_straight_index != null) {
                /* has straight occured in heart */
                Collections.sort(straight_heart);
                for (int j = 0; j < heart_straight_index.size(); j++) {
                    /* check if it can be place in the bottom */
                    if (straight_heart.get(heart_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(i).getValue() + 1) {
                        /* can be placed in the bottom of the straighted card */
                        result_score += deadwoodList.remove(i).getScore();
                    }

                    /* check if it can be placed in the upper */
                    if (straight_heart.get(heart_straight_index.get(j).get(heart_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(i).getValue() - 1) {
                        result_score += deadwoodList.remove(i).getScore();
                    }

                }
            }
            // Diamond straight check
            if (!diamond_straight_index.isEmpty() && diamond_straight_index != null) {
                /* has straight occured in diamond */
                Collections.sort(straight_diamond);
                for (int j = 0; j < diamond_straight_index.size(); j++) {
                    /* check if it can be place in the bottom */
                    if (straight_diamond.get(diamond_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(i).getValue() + 1) {
                        /* can be placed in the bottom of the straighted card */
                        result_score += deadwoodList.remove(i).getScore();
                    }

                    /* check if it can be placed in the upper */
                    if (straight_diamond
                            .get(diamond_straight_index.get(j).get(diamond_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(i).getValue() - 1) {
                        result_score += deadwoodList.remove(i).getScore();
                    }

                }
            }
            // Spade straight check
            if (!spade_straight_index.isEmpty() && spade_straight_index != null) {
                /* has straight occured in spade */
                Collections.sort(straight_spade);
                for (int j = 0; j < spade_straight_index.size(); j++) {
                    /* check if it can be place in the bottom */
                    if (straight_spade.get(spade_straight_index.get(j).get(0))
                            .getValue() == deadwoodList.get(i).getValue() + 1) {
                        /* can be placed in the bottom of the straighted card */
                        result_score += deadwoodList.remove(i).getScore();
                    }

                    /* check if it can be placed in the upper */
                    if (straight_spade.get(spade_straight_index.get(j).get(spade_straight_index.get(j).size() - 1))
                            .getValue() == deadwoodList.get(i).getValue() - 1) {
                        result_score += deadwoodList.remove(i).getScore();
                    }

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