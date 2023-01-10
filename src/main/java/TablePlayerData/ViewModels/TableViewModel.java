package TablePlayerData.ViewModels;

import Rules.HoldEm.HoldEmHandCheckerViewModel;
import SuperClasses.Player;
import SuperClasses.Pot;
import SuperClasses.PrintMethods;
import SuperClasses.Table;
import TablePlayerData.Enums.ActionEnum;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public ArrayList<Player> checkHandsAndGetWinnerList(ArrayList<Player> playerList, HoldEmHandCheckerViewModel HandChecker) {
        ArrayList<Player> winnerList = new ArrayList<>();
        for(Player player:playerList) {
            player.setHandStrength(HandChecker);
        }
        int currentHighestHandStrength = 0;
        for(Player player: playerList) {
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

    public void setupPot(TableModel Model, Pot pot) {
        pot.cleanse();
    }

    public void checkForWinner(Table table) {
        ArrayList<Player> list = checkHandsAndGetWinnerList(table.currentRoundPlayers,table.HandCheckerViewModel);
        for(Player player:list) {
            System.out.println(player.getName());
            System.out.println(player.HandStrength);
            for(Integer i:player.HandStrength.Value()) {
                System.out.println(i);
            }
        }
    }

    public void takeTurn(Table table) {
        int currentBet = 0;
        int moneyCommit = 0;
        int index = 0;
        Player playerWhoBet = null;
        ActionEnum currentActionEnum = ActionEnum.CAN_CHECK_OR_BET;
        List<Player> placeholderArrayList = new ArrayList<>();
        if(table.currentRoundPlayers.size() > 1) {
            for(int i = 0; i<table.currentRoundPlayers.size(); i++) {
                if(table.currentRoundPlayers.get(i).performAction(currentActionEnum)) {
                    moneyCommit = table.currentRoundPlayers.get(i).getBetAction();
                    if(moneyCommit != 0) {
                        System.out.println(table.currentRoundPlayers.get(i).getName()+" bet "+moneyCommit);
                        currentBet += moneyCommit;
                        table.pot.currentPotSize += moneyCommit;
                        if(i == 0 || i == table.currentRoundPlayers.size()-1) {
                            placeholderArrayList = table.currentRoundPlayers;
                            placeholderArrayList.remove(table.currentRoundPlayers.get(i));
                        }
                        else {
                            placeholderArrayList = table.currentRoundPlayers.subList(0,i-1);
                            placeholderArrayList.addAll(table.currentRoundPlayers.subList(i+1,table.currentRoundPlayers.size()-1));
                        }
                        currentActionEnum = ActionEnum.HAS_TO_CALL;
                        playerWhoBet = table.currentRoundPlayers.get(i);
                        index = i;
                        break;
                    }
                }
            }
            if(currentBet != 0) {
                ArrayList<Player> foldedPlayersList = new ArrayList<>();
                for(Player player:placeholderArrayList) {
                    if(player.performAction(currentActionEnum)) {
                        System.out.println(player.getName()+" called.");
                        player.callAction(moneyCommit);
                        table.pot.currentPotSize += moneyCommit;
                    }
                    else {
                        System.out.println(player.getName()+" folded.");
                        foldedPlayersList.add(player);
                    }
                }
                table.currentRoundPlayers.clear();
                for(int j = 0; j<placeholderArrayList.size(); j++) {
                    if(j == index) {
                        table.currentRoundPlayers.add(playerWhoBet);
                    }
                    table.currentRoundPlayers.add(placeholderArrayList.get(j));
                }
            }
        }
    }
}
