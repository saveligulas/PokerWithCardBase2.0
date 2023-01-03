package CardBase;

public enum Suit {
    HEARTS("Hearts", 1),SPADES("Spades", 2),DIAMONDS("Diamonds",3),CLUBS("Clubs",4), EMPTY_SUIT("",0);
    private final String suitName;
    public final int Index;

    private Suit(String suitName, int Index){
        this.suitName = suitName;
        this.Index = Index;
    }

    public String getSuitName() {
        return suitName;
    }
    public int getIndex() {
        return Index;
    }
}
