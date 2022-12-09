package TablePlayerData.Models;

import SuperClasses.Player;

import java.util.ArrayList;

public record TableModel(ArrayList<Player> PlayerList, int TableID, int TableCapacity) {
}
