package TablePlayerData.ViewModels;

import SuperClasses.Player;
import SuperClasses.PrintMethods;
import TablePlayerData.Models.TableModel;

public class TableViewModel {
    public void shuffleDeck(TableModel Model) {
        Model.TableDeck().shuffleDeck();
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

    public void dealCards(TableModel Model) {
        for(Player player: Model.PlayerList()) {

            player.setHand();
        }
    }
}
