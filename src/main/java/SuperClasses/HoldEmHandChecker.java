package SuperClasses;

import CardBase.Card;
import Rules.HoldEm.HoldEmHandCheckerViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class HoldEmHandChecker {
    private final HoldEmHandCheckerViewModel ViewModel;
    private final ArrayList<Player> PlayerList = new ArrayList<>();
    public HoldEmHandChecker(Table table, Player player) {
        ViewModel = new HoldEmHandCheckerViewModel();
    }

    public Player checkForWinner() {

    }
}
