package TablePlayerData.ViewModels;

import Rules.HoldEm.HoldEmHandCheckerViewModel;
import SuperClasses.Player;
import SuperClasses.PrintMethods;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;

import java.util.ArrayList;
import java.util.Collections;

public class TableViewModel {
    public void shuffleDeck(TableCardsModel Model) {
        Collections.shuffle(Model.TableDeck().CardArrayList);
    }

    public void printInfo(TableModel Model) {
        System.out.println();
        PrintMethods.printFiller(25,"-");
        System.out.println("Table-ID: "+Model.TableID());
        System.out.println("Table Capacity: "+Model.PlayerList().size()+"/"+Model.TableCapacity());
        for(Player player:Model.PlayerList()) {
            player.printInfo();
        }
    }

    public ArrayList<Player> checkHandsAndGetWinnerList(TableModel Model, HoldEmHandCheckerViewModel HandChecker) {
        ArrayList<Player> winnerList = new ArrayList<>();
        for(Player player:Model.PlayerList()) {
            player.setHandStrength(HandChecker);
        }
        winnerList = Model.PlayerList();
        winnerList.sort();
        return winnerList;
    }
}
