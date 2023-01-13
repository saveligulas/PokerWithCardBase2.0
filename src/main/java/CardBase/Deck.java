package CardBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Deck {
    private Suit[] Suits = new Suit[4];
    private Rank[] Ranks = new Rank[13];
    {
        for(int i = 0; i<Suit.values().length-1; i++) {
            Suits[i] = Suit.values()[i];
        }
        for(int j = 1; j<Rank.values().length; j++) {
            Ranks[j-1] = Rank.values()[j];
        }
    }
    public ArrayList<Card> CardArrayList = new ArrayList<Card>();
    HashMap<Rank,AtomicInteger> atomicIntegerHashMap = new HashMap<>();
    {
        for (Rank rank : Ranks) {
            atomicIntegerHashMap.put(rank,new AtomicInteger(rank.getValue() * 100000));
        }
    }
    public Deck(int size) {
        for(int i = 0; i<size; i++) {
            initializeStandardDeck();
        }
    }

    public Deck() {
        initializeStandardDeck();
    }

    public void resetDeck(){
        CardArrayList.clear();
        for(Suit suit:Suits) {
            for(Rank rank:Ranks) {
                Card card = new Card(rank,suit);
                assignID(card);
                CardArrayList.add(card);
            }
        }
    }

    private void initializeStandardDeck(){
        for(Suit suit:Suits) {
            for(Rank rank:Ranks) {
                Card card = new Card(rank,suit);
                assignID(card);
                CardArrayList.add(card);
            }
        }
    }

    private void assignID(Card card) {
        int ID = atomicIntegerHashMap.get(card.getRank()).getAndIncrement();
        ID = ID+(card.getSuit().getIndex()*10000);
        card.ID = ID;
    }

    public void printCards() {
        for(Card card:CardArrayList) {
            System.out.println(card.getName());
            System.out.println(card.ID);
        }
    }
    public Card drawTopCard() {
        Card placeholder = CardArrayList.get(0);
        CardArrayList.remove(0);
        return placeholder;
    }
    public void shuffleDeck() {
        Collections.shuffle(CardArrayList);
    }
}
