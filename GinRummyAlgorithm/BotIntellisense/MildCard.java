public class MildCard implements Comparable {

    Integer value;
    String suit = "";

    public MildCard(int value, String suit) {
        this.value = value;
        this.suit = suit;

    }

    public String getSuit() {
        return suit;
    }

    /* Get DeadwoodRank */
    public Integer getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        return this.getValue().compareTo(((MildCard) o).getValue());
    }

    @Override
    public String toString() {
        return "value:" + this.getValue() + " suit:" + this.getSuit();
    }


}
