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
import java.util.HashMap;
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
        if(playerList.size() != 1) {
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
        winnerList.add(playerList.get(0));
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
        int idOfPlayerWhoBet = 0;
        Player playerWhoBet = null;
        ActionEnum currentActionEnum = ActionEnum.CAN_CHECK_OR_BET;
        List<Player> placeholderArrayList = new ArrayList<>();
        HashMap<Integer,Integer> IDForIndexHashMap = new HashMap<>();
        table.currentRoundPlayers = table.Model.PlayerList();
        if(table.Model.PlayerList().size() > 1) {
            for(int i = 0; i<table.Model.PlayerList().size(); i++) {
                if(table.Model.PlayerList().get(i).performAction(currentActionEnum)) {
                    moneyCommit = table.Model.PlayerList().get(i).getBetAction();
                    if(moneyCommit != 0) {
                        System.out.println(table.Model.PlayerList().get(i).getName()+" bet "+moneyCommit);
                        currentBet += moneyCommit;
                        table.pot.currentPotSize += moneyCommit;
                        if(i == 0 || i == table.Model.PlayerList().size()-1) {
                            placeholderArrayList = table.Model.PlayerList();
                            placeholderArrayList.remove(table.Model.PlayerList().get(i));
                        }
                        else {
                            placeholderArrayList = table.Model.PlayerList().subList(0,i-1);
                            placeholderArrayList.addAll(table.Model.PlayerList().subList(i+1,table.Model.PlayerList().size()-1));
                        }
                        currentActionEnum = ActionEnum.HAS_TO_CALL;
                        playerWhoBet = table.Model.PlayerList().get(i);
                        idOfPlayerWhoBet = table.Model.PlayerList().get(i).getID();
                        System.out.println(placeholderArrayList);
                        break;
                    }
                }
            }
            if(currentBet != 0) {
                table.Model.PlayerList().clear();
                for(Player value : placeholderArrayList) {
                    table.Model.PlayerList().add(value);
                }
                for(Player player:table.Model.PlayerList()) {
                    if(player.performAction(currentActionEnum) && player.getID() != idOfPlayerWhoBet ) {
                        System.out.println(player.getName()+" called.");
                        player.callAction(moneyCommit);
                        table.pot.currentPotSize += moneyCommit;
                    }
                    if(player.getID() != idOfPlayerWhoBet) {
                        System.out.println(player.getName()+" folded.");
                        placeholderArrayList.remove(player);
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
