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
    private final ArrayList<Rank> rankList = new ArrayList<>();
    private final ArrayList<Suit> suitList = new ArrayList<Suit>();
    private final ArrayList<Rank> rankListWithoutDuplicates = new ArrayList<>();
    private final HashMap<Integer, Card> integerCardHashMap = new HashMap<>();
    private final HashMap<Card,Integer> cardIntegerHashMap = new HashMap<>();
    private final HashMap<Integer,Rank> integerRankHashMap = new HashMap<>();
    private final HashMap<Integer,Suit> integerSuitHashMap = new HashMap<>();
    private AtomicInteger atomicInteger = new AtomicInteger(100);
    private final HashMap<Rank,ArrayList<Integer>> idsForRanksHashMaps = new HashMap<>();

    public HandStrengthModel checkAndGetHandValue(Player player, Table table) {
        getHandAndInitializeLists(player,table);
        Integer[] value;
        value = checkForRoyalFlush();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.ROYAL_FLUSH);
        }
        value = checkForStraightFlush();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.STRAIGHT_FLUSH);
        }
        value = checkForFourOfAKind();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.FOUR_OF_A_KIND);
        }
        value = checkForFullHouse();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.FULL_HOUSE);
        }
        value = checkForFlush();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.FLUSH);
        }
        value = checkForStraight();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.STRAIGHT);
        }
        value = checkForThreeOfAKind();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.THREE_OF_A_KIND);
        }
        value = checkForTwoPair();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.TWO_PAIR);
        }
        value = checkForPair();
        if(value[0] != 0) {
            return new HandStrengthModel(value,HandStrengthEnum.PAIR);
        }
        return new HandStrengthModel(getRankListOfBestRemainingCards(new ArrayList<Rank>(Collections.singletonList(rankList.get(0))),4,new int[] {rankList.get(0).getValue(true)}).toArray(new Integer[0]),HandStrengthEnum.HIGH_CARD);
    }

    public void clearAllLists() {
        cardList.clear();
        rankList.clear();
        suitList.clear();
        rankListWithoutDuplicates.clear();
        integerCardHashMap.clear();
        cardIntegerHashMap.clear();
        integerRankHashMap.clear();
        integerSuitHashMap.clear();
        atomicInteger = new AtomicInteger(100);
        idsForRanksHashMaps.clear();
    }

    public void getHandAndInitializeLists(Player player, Table table) {
        clearAllLists();
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
        rankListWithoutDuplicates.removeIf(rank -> rank.getValue() <= 0);
        Collections.sort(rankListWithoutDuplicates);
        Collections.reverse(rankListWithoutDuplicates);
        if(rankListWithoutDuplicates.contains(Rank.ACE)) {
            rankListWithoutDuplicates.remove(Rank.ACE);
            rankListWithoutDuplicates.add(Rank.ACE);
            rankListWithoutDuplicates.add(0,Rank.ACE);
        }
        Collections.sort(rankList);
        Collections.reverse(rankList);
        Collections.sort(suitList);

    }

    private ArrayList<Integer> getRankListOfBestRemainingCards(ArrayList<Rank> usedCards, int remainingSlots, int[] bestHandValue) {
        for(Rank rank:rankList) {
            if(rank.getValue() < 0) {
                rankList.remove(rank);
            }
        }
        ArrayList<Integer> placeholderList = new ArrayList<>();
        for(int integer:bestHandValue) {
            placeholderList.add(integer);
        }
        int finalSize = placeholderList.size() + remainingSlots;
        for (Rank rank : rankList) {
            if (!usedCards.contains(rank) && placeholderList.size()<finalSize) {
                placeholderList.add(rank.getValue(true));
            } else {
                usedCards.remove(rank);
            }
        }
        placeholderList.removeIf(integer -> integer <= 0);
        return placeholderList;
    }

    public Integer[] checkForPair() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        for(Rank rank:rankList) {
            if(rank.getValue() == placeholder.getValue() && counter!= 0) {
                ArrayList<Rank> usedCards = new ArrayList<>() {{
                    add(rank);
                    add(rank);
                }};
                return getRankListOfBestRemainingCards(usedCards,4,new int[] {rank.getValue(true)}).toArray(new Integer[0]);
            }
            counter += 1;
            placeholder = rank;
        }
        return new Integer[] {0};
    }

    public Integer[] checkForTwoPair() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        int PairCounter = 0;
        int highestPairValue = 0;
        int secondPairValue = 0;
        ArrayList<Rank> usedCards = new ArrayList<>();
        for(Rank rank: rankList) {
            if(rank.getValue() == placeholder.getValue() && counter!= 0) {
                PairCounter += 1;
                usedCards.add(rank);
                usedCards.add(rank);
                if(PairCounter == 1) {
                    highestPairValue = rank.getValue(true);
                }

                if(PairCounter == 2) {
                    secondPairValue = rank.getValue(true);
                }
                if(PairCounter == 2) {
                    return getRankListOfBestRemainingCards(usedCards,1,new int[]{highestPairValue,secondPairValue}).toArray(new Integer[0]);
                }
            }
            counter += 1;
            placeholder = rank;
        }
        return new Integer[] {0};
    }

    public Integer[] checkForThreeOfAKind() {
        Rank placeholder = rankList.get(0);
        int counter = 0;
        for(int i = 0; i<rankList.size(); i++) {
            if(counter != rankList.size()-1) {
                if (rankList.get(i).getValue(true) == placeholder.getValue(true) && counter != 0 && rankList.get(i + 1).getValue(true) == placeholder.getValue(true)) {
                    ArrayList<Rank> usedCards = new ArrayList<>();
                    usedCards.add(rankList.get(i));
                    usedCards.add(rankList.get(i));
                    usedCards.add(rankList.get(i));
                    return getRankListOfBestRemainingCards(usedCards,2,new int[] {rankList.get(i).getValue(true),rankList.get(i).getValue(true),rankList.get(i).getValue(true)}).toArray(new Integer[0]);
                }
                counter += 1;
                placeholder = rankList.get(i);
            }
        }
        return new Integer[] {0};
    }

    public Integer[] checkForStraight() {
        if(rankListWithoutDuplicates.size()>=5) {
            for (int i = 0; i < (rankListWithoutDuplicates.size() - 4); i++) {
                int value = rankListWithoutDuplicates.get(i).getValue(true);
                if (rankListWithoutDuplicates.get(i + 1).getValue() == (value - 1) || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 12)) {
                    if (rankListWithoutDuplicates.get(i + 2).getValue() == value - 2 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 11)) {
                        if (rankListWithoutDuplicates.get(i + 3).getValue() == value - 3 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 10)) {
                            if (rankListWithoutDuplicates.get(i + 4).getValue() == value - 4 || rankListWithoutDuplicates.get(i + 1).getValue() == (value + 9)) {
                                return new Integer[] {value};
                            }
                        }
                    }
                }
            }
        }
        return new Integer[] {0};
    }

    public Integer[] checkForFlush() {
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
                            return new Integer[] {value};
                        }
                    }
                }
            }

        }
        return new Integer[] {0};
    }


    public Integer[] checkForFullHouse() {
        ArrayList<Rank> placeholder = new ArrayList<>();
        if(checkForThreeOfAKind()[0] != 0) {
            int value = checkForThreeOfAKind()[0];
            for(int i = 0; i<3; i++) {
                placeholder.add(Rank.getEnum(value));
                rankList.remove(Rank.getEnum(value));
            }
            rankList.remove(Rank.getEnum(value));
            if(checkForPair()[0] != 0) {
                rankList.addAll(placeholder);
                int secondValue = checkForPair()[0];
                Collections.sort(rankList);
                Collections.reverse(rankList);
                return new Integer[] {placeholder.get(0).getValue(true),secondValue};
            }
        }
        return new Integer[] {0};
    }

    public Integer[] checkForFourOfAKind() {
        Rank placeholder;
        for(int i = 0; i<rankList.size()-3; i++) {
            placeholder = rankList.get(i);
            if (rankList.get(i + 1).getValue(true) == placeholder.getValue(true) && rankList.get(i+2).getValue(true) == placeholder.getValue(true) && rankList.get(i+3).getValue(true) == placeholder.getValue(true)) {
                return new Integer[] {rankList.get(i).getValue(true)};
            }
        }
        return new Integer[] {0};
    }

    public Integer[] checkForStraightFlush() {
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
                                    return new Integer[] {value};
                                }
                            }
                        }
                    }
                }
            }
        }

        return new Integer[] {0};
    }

    public Integer[] checkForRoyalFlush() {
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
                                    return new Integer[] {value};
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Integer[] {0};
    }
}