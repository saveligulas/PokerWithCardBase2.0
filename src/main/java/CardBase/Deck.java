package CardBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Deck {
    private final Suit[] Suits = Suit.values();
    private final Rank[] Ranks = Rank.values();
    private final ArrayList<Card> CardArrayList = new ArrayList<Card>();
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
}
