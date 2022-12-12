package SuperClasses;

import Rules.HoldEm.HoldEmHandCheckerViewModel;

import java.util.ArrayList;

public class HoldEmHandChecker {
    private final HoldEmHandCheckerViewModel ViewModel;
    private final ArrayList<Player> PlayerList = new ArrayList<>();
    public HoldEmHandChecker(Table table, Player player) {
        ViewModel = new HoldEmHandCheckerViewModel();
    }

    public Player checkForWinner() {
        return new Player("Test",5555);
    }
}
