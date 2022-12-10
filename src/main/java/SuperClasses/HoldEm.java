package SuperClasses;

import CardBase.Card;
import Rules.HoldEm.HoldEmModel;
import Rules.HoldEm.HoldEmViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class HoldEm {
    private HoldEmModel Model;
    private final HoldEmViewModel ViewModel;
    public HoldEm(Table table, Player player) {
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(player.HandModel.Hand()));
        cards.addAll(table.getAllCards());
        Model = new HoldEmModel(cards);
        ViewModel = new HoldEmViewModel();
    }
}
