package Rules.HoldEm;

import CardBase.Card;
import CardBase.Rank;
import CardBase.Suit;

import java.util.ArrayList;
import java.util.Collections;

public class HoldEmHandCheckerViewModel {
    private int value;
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private ArrayList<Rank> rankList = new ArrayList<>();
    private ArrayList<Suit> suitList = new ArrayList<Suit>();

    public int checkAndGetHandValue(ArrayList<Card> cardArrayList) {


        return 0;
    }

    public void getHandAndInitializeLists(ArrayList<Card> cardArrayList) {
        cardList = cardArrayList;
        for(Card card:cardArrayList) {
            rankList.add(card.getRank());
            suitList.add(card.getSuit());
        }
        Collections.sort(rankList);
        Collections.sort(suitList);
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
