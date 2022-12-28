package TablePlayerData.ViewModels;

import Rules.HoldEm.HoldEmHandCheckerViewModel;
import SuperClasses.Player;
import SuperClasses.PrintMethods;
import SuperClasses.Table;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TableViewModel {
    private AtomicInteger atomicInteger = new AtomicInteger(1000);
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
        int currentHighestHandStrength = 0;
        for(Player player: Model.PlayerList()) {
            if(player.HandStrength.StrengthEnum().getValue() >= currentHighestHandStrength) {
                if(player.HandStrength.StrengthEnum().getValue() == currentHighestHandStrength) {
                    if(player.HandStrength.Value()[0] > winnerList.get(0).HandStrength.Value()[0]) {
                        winnerList.clear();
                        winnerList.add(player);
                    } else {
                        for(int i = 1; i<player.HandStrength.Value().length; i++) {
                            if(player.HandStrength.Value()[i] > winnerList.get(0).HandStrength.Value()[i]) {
                                winnerList.clear();
                                winnerList.add(player);
                                break;
                            }
                            if(player.HandStrength.Value()[i] < winnerList.get(0).HandStrength.Value()[i]) {
                                break;
                            }
                        }
                    }
                } else {
                    currentHighestHandStrength = player.HandStrength.StrengthEnum().getValue();
                    winnerList.clear();
                    winnerList.add(player);
                }
            }
        }
        return winnerList;
    }

    public void setupPotIds(HashMap<Integer,ArrayList<Player>> hashMap,TableModel Model) {
        hashMap.clear();
        hashMap.put(atomicInteger.getAndIncrement(),Model.PlayerList());
    }

    public void checkForWinner(Table table) {
        ArrayList<Player> list = checkHandsAndGetWinnerList(table.Model,table.HandCheckerViewModel);
        for(Player player:list) {
            System.out.println(player.getName());
            System.out.println(player.HandStrength);
            for(Integer i:player.HandStrength.Value()) {
                System.out.println(i);
            }
        }
    }
}
