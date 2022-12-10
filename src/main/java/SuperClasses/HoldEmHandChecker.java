package SuperClasses;

import CardBase.Card;
import Rules.HoldEm.HoldEmHandCheckerViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class HoldEmHandChecker {
    private final HoldEmHandCheckerViewModel ViewModel;
    private final ArrayList<Card> cardArrayList;
    public HoldEmHandChecker(Table table, Player player) {
        cardArrayList = new ArrayList<>(Arrays.asList(player.HandModel.Hand()));
        cardArrayList.addAll(table.getAllCards());
        ViewModel = new HoldEmHandCheckerViewModel();
    }
}
