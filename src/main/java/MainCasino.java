import FileIO.FileReaderIO;
import Table.Models.Player;

import java.util.ArrayList;

public class MainCasino {
    public static void main(String[] args) {
        ArrayList<Player> list = FileReaderIO.ReadAllPlayers();
        System.out.println(list);
    }
}
