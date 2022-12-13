package Rules.HoldEm;

import CardBase.Card;
import CardBase.Rank;
import CardBase.Suit;
import SuperClasses.Player;
import SuperClasses.Table;

import java.util.ArrayList;
import java.util.Collections;

public class HoldEmHandCheckerViewModel {
    private int value;
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private ArrayList<Rank> rankList = new ArrayList<>();
    private ArrayList<Suit> suitList = new ArrayList<Suit>();

    public int checkAndGetHandValue(Player player, Table table) {
        getHandAndInitializeLists(player,table);
        int value = 0;
        return checkForTwoPair();
    }

    public void getHandAndInitializeLists(Player player, Table table) {
        cardList = table.getAllCards();
        cardList.addAll(player.getHand());
        for(Card card:cardList) {
            rankList.add(card.getRank());
            suitList.add(card.getSuit());
        }
        Collections.sort(rankList);
        Collections.reverse(rankList);
        System.out.println(rankList);
        Collections.sort(suitList);
        System.out.println(suitList);
    }

    public int checkForPair() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        for(Rank rank:rankList) {
            if(rank.getValue() == placeholder.getValue() && counter!= 0) {
                return rank.getValue();
            }
            counter += 1;
            placeholder = rank;
        }
        return 0;
    }

    public int checkForTwoPair() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        int PairCounter = 0;
        int highestPairValue = 0;
        for(Rank rank: rankList) {
            if(rank.getValue() == placeholder.getValue() && counter!= 0) {
                if(PairCounter == 0) {
                    highestPairValue = rank.getValue();
                }
                PairCounter += 1;
                if(PairCounter == 2) {
                    return highestPairValue;
                }
            }
            counter += 1;
            placeholder = rank;
        }
        return 0;
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
