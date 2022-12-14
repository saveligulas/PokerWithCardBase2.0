package CardBase;

public enum Rank {
    TWO(2),THREE(3),FOUR(4),FIVE(5),
    SIX(6),SEVEN(7),EIGHT(8),NINE(9),
    TEN(10),JACK(11),QUEEN(12),KING(13),ACE(1);

    private final Integer cardValue;
    private Rank(Integer cardValue) {
        this.cardValue = cardValue;
    }
    public int getValue(boolean getAceHigh) {
        if(cardValue == 1 && getAceHigh) {
            return  14;
        }
        return cardValue;
    }
    public int getValue() {
        return cardValue;
    }
    public String getName(Rank card) {
        String str = "";
        if (card.cardValue > 1 && card.cardValue < 10) {
            str = card.cardValue.toString();
        }
        else {
            str = switch (card) {
                case TEN -> "10";
                case JACK -> "Jack";
                case QUEEN -> "Queen";
                case KING -> "King";
                case ACE -> "Ace";
                default -> "Not a Card";
            };
        }
        return str;
    }
}
