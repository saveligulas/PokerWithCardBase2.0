import CardBase.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainCasino {
    public static void main(String[] args) {
//        ArrayList<Player> list = FileReaderIO.ReadAllPlayers();
//        System.out.println(list);
//        Casino casino = new Casino(RandomDataBaseCreator.getRandomCasinoName());
//        casino.initializeCasino(100,5,10);
//        casino.startNewRoundAtAllTables();
//        casino.printInfo();
        ArrayList<Rank> list = new ArrayList<>(Arrays.asList(Rank.values()));
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
