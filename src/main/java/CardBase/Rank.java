package CardBase;

public enum Rank {
    EMPTY_RANK(0), TWO(2),THREE(3),FOUR(4),FIVE(5),
    SIX(6),SEVEN(7),EIGHT(8),NINE(9),
    TEN(10),JACK(11),QUEEN(12),KING(13),
    ACE(1);

    private final Integer cardValue;
    private Rank(Integer cardValue) {
        if(cardValue <= 0) {
            this.cardValue = AtomicInteger.getInteger();
        } else {
            this.cardValue = cardValue;
        }
    }


    public static Rank getEnum(int value) {
        return switch(value) {
            case 1 -> ACE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            case 5 -> FIVE;
            case 6 -> SIX;
            case 7 -> SEVEN;
            case 8 -> EIGHT;
            case 9 -> NINE;
            case 10 -> TEN;
            case 11 -> JACK;
            case 12 -> QUEEN;
            case 13 -> KING;
            default -> EMPTY_RANK;
        };
    }

    public int getValue(boolean getAceHigh) {
        if(cardValue <= 0) {
            return  AtomicInteger.getInteger();
        }
        if(cardValue == 1 && getAceHigh) {
            return  14;
        }
        return cardValue;
    }
    public int getValue() {
        if(cardValue <= 0) {
            return AtomicInteger.getInteger();
        }
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

    private static class AtomicInteger {
        public final static java.util.concurrent.atomic.AtomicInteger integer = new java.util.concurrent.atomic.AtomicInteger(0);

        public static Integer getInteger() {
            return integer.decrementAndGet();
        }
    }
}
