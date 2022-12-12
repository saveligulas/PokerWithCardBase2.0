package Rules.HoldEm;

import CardBase.Card;

import java.util.ArrayList;

public class HoldEmHandCheckerViewModel {
    private int value;
    private ArrayList<Card> cardList = new ArrayList<Card>();

    public int checkAndGetHandValue(ArrayList<Card> cardArrayList) {
        return 0;
    }

    public void getHand(ArrayList<Card> cardArrayList) {
        cardList = cardArrayList;
    }

    public void checkForPair() {

    }

    public void checkForTwoPair() {

    }

    public void checkForThreeOfAKind() {

    }

    public void checkForStraight() {

    }

    public void checkForFlush() {

    }

    public void checkForFullHouse() {

    }

    public void checkForFourOfAKind() {

    }

    public void checkForStraightFlush() {

    }

    public void checkForRoyalFlush() {

    }

}
