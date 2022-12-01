package Casino.Data;

import FileIO.FileReaderIO;
import Table.Models.Player;

import java.util.ArrayList;

public class SyncData {
    public void FetchPlayerSheet() {
        ArrayList<Player> PlayerArrayList = FileReaderIO.ReadAllPlayers();
    }
}
