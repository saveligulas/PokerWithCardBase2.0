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

    public HandStrengthModel checkAndGetHandValue(Player player, Table table) {
        getHandAndInitializeLists(player,table);
        int value = 0;
        value = checkForStraight();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.STRAIGHT);
        }
        value = checkForThreeOfAKind();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.THREE_OF_A_KIND);
        }
        value = checkForTwoPair();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.TWO_PAIR);
        }
        value = checkForPair();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.PAIR);
        }
        return new HandStrengthModel(rankList.get(0).getValue(),HandStrengthEnum.HIGH_CARD);
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

    public int checkForThreeOfAKind() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        for(int i = 0; i<rankList.size(); i++) {
            if (rankList.get(i).getValue() == placeholder.getValue() && counter != 0 && rankList.get(i + 1).getValue() == placeholder.getValue() && counter != rankList.size()-1) {
                return rankList.get(i).getValue();
            }
            counter += 1;
            placeholder = rankList.get(i);
        }
        return 0;
    }

    public int checkForStraight() {
        for(int i = 0; i<(rankList.size()-4); i++) {
            int value = rankList.get(i).getValue();
            if(rankList.get(i+1).getValue() == value-1) {
                if(rankList.get(i+2).getValue() == value-2) {
                    if(rankList.get(i+3).getValue() == value-3) {
                        if(rankList.get(i+4).getValue() == value-4) {
                            return value;
                        }
                    }
                }
            }
        }
        return 0;
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
