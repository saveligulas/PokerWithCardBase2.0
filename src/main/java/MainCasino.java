import Casino.ID.RandomDataBaseCreator;
import FileIO.FileReaderIO;
import SuperClasses.Casino;
import SuperClasses.Player;

import java.util.ArrayList;

public class MainCasino {
    public static void main(String[] args) {
        ArrayList<Player> list = FileReaderIO.ReadAllPlayers();
        System.out.println(list);
        Casino casino = new Casino(RandomDataBaseCreator.getRandomCasinoName());
        casino.initializeCasino(100,5,100);
        casino.printInfo();
    }
}
