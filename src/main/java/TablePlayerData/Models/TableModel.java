package TablePlayerData.Models;

import CardBase.Deck;
import SuperClasses.Player;

import java.util.ArrayList;

public record TableModel(ArrayList<Player> PlayerList, int TableID, Deck TableDeck, int TableCapacity) {
}
