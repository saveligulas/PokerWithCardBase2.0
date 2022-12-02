package Casino.Data;

import FileIO.FileReaderIO;
import SuperClasses.Player;

import java.util.ArrayList;

public class SyncData {
    public void FetchPlayerSheet() {
        ArrayList<Player> PlayerArrayList = FileReaderIO.ReadAllPlayers();
    }
}
