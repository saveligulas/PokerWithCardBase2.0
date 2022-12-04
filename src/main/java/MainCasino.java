import FileIO.FileReaderIO;
import SuperClasses.Player;
import Table.ViewModels.PlayerViewModel;

import java.util.ArrayList;

public class MainCasino {
    public static void main(String[] args) {
        ArrayList<Player> list = FileReaderIO.ReadAllPlayers();
        for(Player player:list) {
            PlayerViewModel.printInfo(player);
        }
    }
}
