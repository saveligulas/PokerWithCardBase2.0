package CardBase;

public class Card {
    private final Rank Rank;
    private final Suit Suit;
    private final String Name;
    public int ID;
    public Card(Rank Rank,Suit Suit) {
        this.Rank = Rank;
        this.Suit = Suit;
        Name = Rank.getName(Rank)+" of "+Suit.getSuitName();
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

    public Card() {
        this.Rank = CardBase.Rank.ACE;
        this.Suit = CardBase.Suit.SPADES;
        Name = Rank.getName(CardBase.Rank.ACE)+" of "+CardBase.Suit.SPADES.getSuitName();
    }

    public Card(boolean emptyCard) {
        ID = 0;
        Rank = CardBase.Rank.EMPTY_RANK;
        Suit = CardBase.Suit.EMPTY_SUIT;
        Name = Rank.getValue()+" of Empty";
    }
}
