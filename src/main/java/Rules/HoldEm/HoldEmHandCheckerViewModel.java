package Rules.HoldEm;

import CardBase.Card;
import CardBase.Rank;
import CardBase.Suit;
import SuperClasses.Player;
import SuperClasses.Table;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HoldEmHandCheckerViewModel {
    private int value;
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private ArrayList<Rank> rankList = new ArrayList<>();
    private ArrayList<Suit> suitList = new ArrayList<Suit>();
    private ArrayList<Rank> rankListWithoutDuplicates = new ArrayList<>();
    private HashMap<Integer, Card> integerCardHashMap = new HashMap<>();
    private HashMap<Card,Integer> cardIntegerHashMap = new HashMap<>();
    private HashMap<Integer,Rank> integerRankHashMap = new HashMap<>();
    private HashMap<Integer,Suit> integerSuitHashMap = new HashMap<>();
    private AtomicInteger atomicInteger = new AtomicInteger(100);
    private HashMap<Rank,ArrayList<Integer>> idsForRanksHashMaps = new HashMap<>();

    public HandStrengthModel checkAndGetHandValue(Player player, Table table) {
        getHandAndInitializeLists(player,table);
        int value = 0;
        value = checkForRoyalFlush();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.ROYAL_FLUSH);
        }
        value = checkForStraightFlush();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.STRAIGHT_FLUSH);
        }
        value = checkForFourOfAKind();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.FOUR_OF_A_KIND);
        }
        value = checkForFullHouse();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.FULL_HOUSE);
        }
        value = checkForFlush();
        if(value != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.FLUSH);
        }
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
        return new HandStrengthModel(rankList.get(0).getValue(true),HandStrengthEnum.HIGH_CARD);
    }

    public void getHandAndInitializeLists(Player player, Table table) {
        cardList = table.getAllCards();
        cardList.addAll(player.getHand());
        for(Card card:cardList) {
            rankList.add(card.getRank());
            suitList.add(card.getSuit());
            int id = atomicInteger.incrementAndGet();
            cardIntegerHashMap.put(card,id);
            integerCardHashMap.put(id,card);
            integerRankHashMap.put(id,card.getRank());
            integerSuitHashMap.put(id,card.getSuit());
            if(idsForRanksHashMaps.containsKey(card.getRank())) {
                idsForRanksHashMaps.get(card.getRank()).add(id);
            } else {
                idsForRanksHashMaps.put(card.getRank(),new ArrayList<>());
                idsForRanksHashMaps.get(card.getRank()).add(id);
            }
        }
        Set<Rank> rankSetList = new HashSet<>(rankList);
        rankListWithoutDuplicates.clear();
        rankListWithoutDuplicates.addAll(rankSetList);
        Collections.sort(rankListWithoutDuplicates);
        Collections.reverse(rankListWithoutDuplicates);
        if(rankListWithoutDuplicates.contains(Rank.ACE)) {
            rankListWithoutDuplicates.remove(Rank.ACE);
            rankListWithoutDuplicates.add(Rank.ACE);
            rankListWithoutDuplicates.add(0,Rank.ACE);
        }
        Collections.sort(rankList);
        Collections.reverse(rankList);
        System.out.println(rankList);
        Collections.sort(suitList);
        System.out.println(suitList);
        System.out.println(rankListWithoutDuplicates);
    }

    public int checkForPair() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        for(Rank rank:rankList) {
            if(rank.getValue() == placeholder.getValue() && counter!= 0) {
                return rank.getValue(true);
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
                    highestPairValue = rank.getValue(true);
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
            if(counter != rankList.size()-1) {
                if (rankList.get(i).getValue(true) == placeholder.getValue(true) && counter != 0 && rankList.get(i + 1).getValue(true) == placeholder.getValue(true)) {
                    return rankList.get(i).getValue(true);
                }
                counter += 1;
                placeholder = rankList.get(i);
            }
        }
        return 0;
    }

    public int checkForStraight() {
        if(rankListWithoutDuplicates.size()>=5) {
            for (int i = 0; i < (rankListWithoutDuplicates.size() - 4); i++) {
                int value = rankListWithoutDuplicates.get(i).getValue(true);
                if (rankListWithoutDuplicates.get(i + 1).getValue() == (value - 1) || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 12)) {
                    if (rankListWithoutDuplicates.get(i + 2).getValue() == value - 2 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 11)) {
                        if (rankListWithoutDuplicates.get(i + 3).getValue() == value - 3 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 10)) {
                            if (rankListWithoutDuplicates.get(i + 4).getValue() == value - 4 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 9)) {
                                return value;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public int checkForFlush() {
        int counter = 0;
        for(int i = 0; i<suitList.size()-4; i++) {
            Suit placeholder = suitList.get(i);
            if(suitList.get(i+1) == placeholder) {
                if(suitList.get(i+2) == placeholder) {
                    if(suitList.get(i+3) == placeholder) {
                        if(suitList.get(i+4) == placeholder) {
                            int maxValue = 0;
                            for(Card card : cardList) {
                                if(card.getSuit() == placeholder && card.getRank().getValue(true) > maxValue) {
                                    maxValue = card.getRank().getValue(true);
                                }
                            }
                            return maxValue;
                        }
                    }
                }
            }

        }
        return 0;
    }

    public int checkForFullHouseValueWithTwos() {
        ArrayList<Rank> placeholder = new ArrayList<>();
        if(checkForThreeOfAKind() != 0) {
            int value = checkForThreeOfAKind();
            for(int i = 0; i<3; i++) {
                placeholder.add(Rank.getEnum(value));
                rankList.remove(Rank.getEnum(value));
            }
            rankList.remove(Rank.getEnum(value));
            System.out.println(rankList);
            if(checkForPair() != 0) {
                value += checkForPair();
                rankList.addAll(placeholder);
                Collections.sort(rankList);
                Collections.reverse(rankList);
                System.out.println(rankList);
                return value;
            }
        }
        return 0;
    }

    public int checkForFullHouse() {
        ArrayList<Rank> placeholder = new ArrayList<>();
        if(checkForThreeOfAKind() != 0) {
            int value = checkForThreeOfAKind();
            for(int i = 0; i<3; i++) {
                placeholder.add(Rank.getEnum(value));
                rankList.remove(Rank.getEnum(value));
            }
            rankList.remove(Rank.getEnum(value));
            System.out.println(rankList);
            if(checkForPair() != 0) {
                rankList.addAll(placeholder);
                Collections.sort(rankList);
                Collections.reverse(rankList);
                System.out.println(rankList);
                return value;
            }
        }
        return 0;
    }

    public int checkForFourOfAKind() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        for(int i = 0; i<rankList.size(); i++) {
            if(counter != rankList.size()-1) {
                if (rankList.get(i).getValue(true) == placeholder.getValue(true) && counter != 0 && rankList.get(i + 1).getValue(true) == placeholder.getValue(true) && rankList.get(i+2).getValue(true) == placeholder.getValue(true)) {
                    return rankList.get(i).getValue(true);
                }
                counter += 1;
                placeholder = rankList.get(i);
            }
        }
        return 0;
    }

    public int checkForStraightFlush() {
        ArrayList<Rank> straightRanks = new ArrayList<>();
        for (int i = 0; i < (rankListWithoutDuplicates.size() - 4); i++) {
            int value = rankListWithoutDuplicates.get(i).getValue(true);
            straightRanks.clear();
            straightRanks.add(rankListWithoutDuplicates.get(i));
            if ((rankListWithoutDuplicates.get(i + 1).getValue() == (value - 1)) || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 12)) {
                straightRanks.add(rankListWithoutDuplicates.get(i+1));
                if (rankListWithoutDuplicates.get(i + 2).getValue() == value - 2 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 11)) {
                    straightRanks.add(rankListWithoutDuplicates.get(i+2));
                    if (rankListWithoutDuplicates.get(i + 3).getValue() == value - 3 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 10)) {
                        straightRanks.add(rankListWithoutDuplicates.get(i+3));
                        if (rankListWithoutDuplicates.get(i + 4).getValue() == value - 4 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 9)) {
                            straightRanks.add(rankListWithoutDuplicates.get(i+4));
                            int suitCounter;
                            Suit suit;
                            System.out.println();
                            Rank initialRank = straightRanks.get(0);
                            straightRanks.remove(0);
                            for(Integer id:idsForRanksHashMaps.get(initialRank)) {
                                suitCounter = 0;
                                suit = integerSuitHashMap.get(id);
                                for(Rank rank:straightRanks) {
                                    for(Integer id2:idsForRanksHashMaps.get(rank)) {
                                        if(integerSuitHashMap.get(id2) == suit) {
                                            suitCounter += 1;
                                        }
                                    }
                                }
                                if(suitCounter == 4) {
                                    return value;
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int checkForRoyalFlush() {
        ArrayList<Rank> straightRanks = new ArrayList<>();
        Rank[] royalFlushRanks = new Rank[] {Rank.ACE,Rank.KING,Rank.QUEEN,Rank.JACK,Rank.TEN};
        for (int i = 0; i < (rankListWithoutDuplicates.size() - 4); i++) {
            int value = rankListWithoutDuplicates.get(i).getValue(true);
            straightRanks.clear();
            straightRanks.add(rankListWithoutDuplicates.get(i));
            if ((rankListWithoutDuplicates.get(i + 1).getValue() == (value - 1)) || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 12)) {
                straightRanks.add(rankListWithoutDuplicates.get(i+1));
                if (rankListWithoutDuplicates.get(i + 2).getValue() == value - 2 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 11)) {
                    straightRanks.add(rankListWithoutDuplicates.get(i+2));
                    if (rankListWithoutDuplicates.get(i + 3).getValue() == value - 3 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 10)) {
                        straightRanks.add(rankListWithoutDuplicates.get(i+3));
                        if (rankListWithoutDuplicates.get(i + 4).getValue() == value - 4 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 9)) {
                            ArrayList<Rank> straightFlushRanks = new ArrayList<>();
                            straightRanks.add(rankListWithoutDuplicates.get(i+4));
                            int suitCounter;
                            Suit suit;
                            Rank initialRank = straightRanks.get(0);
                            straightRanks.remove(0);
                            for(Integer id:idsForRanksHashMaps.get(initialRank)) {
                                suitCounter = 0;
                                straightFlushRanks.clear();
                                straightFlushRanks.add(initialRank);
                                suit = integerSuitHashMap.get(id);
                                for(Rank rank:straightRanks) {
                                    straightFlushRanks.add(rank);
                                    for(Integer id2:idsForRanksHashMaps.get(rank)) {
                                        if(integerSuitHashMap.get(id2) == suit) {
                                            suitCounter += 1;
                                        }
                                    }
                                }
                                Collections.sort(straightFlushRanks);
                                Collections.reverse(straightFlushRanks);
                                ArrayList<Rank> royalFlushRankArraylist = new ArrayList<>(List.of(royalFlushRanks));
                                Collections.sort(royalFlushRankArraylist);
                                Collections.reverse(royalFlushRankArraylist);
                                if(suitCounter == 4 && straightFlushRanks.equals(royalFlushRankArraylist)) {
                                    return value;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}