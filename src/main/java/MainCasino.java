import Casino.ID.RandomDataBaseCreator;
import FileIO.FileReaderIO;
import SuperClasses.Casino;
import SuperClasses.Player;
import SuperClasses.Table;

import java.util.ArrayList;

public class MainCasino {
    public static void main(String[] args) {
        RandomDataBaseCreator rand = new RandomDataBaseCreator();
        ArrayList<Player> list = FileReaderIO.ReadAllPlayers();
        ArrayList<Table> tableArrayList = rand.createRandomTablesList(100,6,12);
        for(int i = 0; i<tableArrayList.size(); i++) {
            if(!list.isEmpty()) {
                for(int j = 0; j<tableArrayList.get(i).Model.TableCapacity(); j++) {
                    tableArrayList.get(i).addPlayer(list.get(0));
                    list.remove(0);
                }
            }
        }
        Casino casino = new Casino(rand.getRandomCasinoName());
        for(Table table:tableArrayList) {
            casino.addTable(table);
        }
        casino.printInfo();
    }
}
