package CardBase;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Card {
    private final Rank Rank;
    private final Suit Suit;
    private final String Name;
    public int ID;
    public Card(Rank Rank,Suit Suit) {
        this.Rank = Rank;
        this.Suit = Suit;
        Name = Rank.getName(Rank)+" of "+Suit.getSuitName();
        switch(Suit){

        }
    }

    public String getName() {
        return Name;
    }

    public CardBase.Suit getSuit() {
        return Suit;
    }

    public CardBase.Rank getRank() {
        return Rank;
    }

    public int getID() {
        return ID;
    }
}
