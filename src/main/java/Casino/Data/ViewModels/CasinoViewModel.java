package Casino.Data.ViewModels;

import Casino.Data.Models.CasinoModel;
import Casino.ID.RandomDataBaseCreator;
import FileIO.FileReaderIO;
import SuperClasses.Casino;
import SuperClasses.Player;
import SuperClasses.PrintMethods;
import SuperClasses.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class CasinoViewModel {
    private final ArrayList<Player> playerArrayList = FileReaderIO.ReadAllPlayers();
    private final Scanner scanner = new Scanner(System.in);
    public void printInfo(CasinoModel Model) {
        PrintMethods.printFiller(25, "-");
        System.out.println(Model.Name());
        for(Table table:Model.TableArrayList()) {
            table.printInfo();
        }
    }
    public void createCasinoWithTablesAndLoadPlayerDataSheet(Casino casino,int tables,int tableCapMin,int tableCapMax) {
        ArrayList<Player> list = playerArrayList;
        int listLength = playerArrayList.size();
        int counter = 0;
        ArrayList<Table> tableArrayList = RandomDataBaseCreator.createRandomTablesList(tables, tableCapMin, tableCapMax);
        for (int i = 0; i<tableArrayList.size(); i++) {
            if (!list.isEmpty() && list.size()>tableArrayList.get(i).Model.TableCapacity()) {
                for (int j = 0; j < tableArrayList.get(i).Model.TableCapacity(); j++) {
                    System.out.println(i);
                    tableArrayList.get(i).addPlayer(list.get(0));
                    list.remove(0);
                    counter += 1;
                }
            }
        }
        PrintMethods.printFiller();
        int answer = 0;
        if (list.isEmpty()) {
            System.out.println("All Players could be assigned to a Table.");
        } else {
            System.out.println("Not all Players could be assigned to a Table.");
            System.out.println("Do you want to save the remaining Players to assign them later? (1 for Yes)");
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            if (answer == 1) {
                casino.addPlayersWithoutTable(list);
                System.out.println(list);
            }
        }
        for (Table table : tableArrayList) {
            casino.addTable(table);
        }
    }

    public void startNewRoundAtTable(Table table) {
        table.startNewRound();
    }
}
