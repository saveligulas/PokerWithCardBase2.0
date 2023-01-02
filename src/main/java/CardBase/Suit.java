package CardBase;

import java.util.concurrent.atomic.AtomicInteger;

public enum Suit {
    HEARTS("Hearts", 1),SPADES("Spades", 2),DIAMONDS("Diamonds",3),CLUBS("Clubs",4), EMPTY_SUIT("",0);
    private final String suitName;
    private AtomicInteger atomicInteger = new AtomicInteger(-1);
    public final int Index;

    private Suit(String suitName, int Index){
        if(Index == 0) {
            this.Index = atomicInteger.decrementAndGet();
            this.suitName = "";
        } else {
            this.suitName = suitName;
            this.Index = Index;
        }
    }

    public String getSuitName() {
        return suitName;
    }
    public int getIndex() {
        return Index;
    }
}
