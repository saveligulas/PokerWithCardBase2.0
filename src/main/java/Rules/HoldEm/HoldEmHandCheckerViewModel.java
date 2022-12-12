package Rules.HoldEm;

import CardBase.Card;

import java.util.ArrayList;
import java.util.Collections;

public class HoldEmHandCheckerViewModel {
    private int value;
    private ArrayList<Card> cardList = new ArrayList<Card>();

    public int checkAndGetHandValue(ArrayList<Card> cardArrayList) {
        Collections.sort();
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
