package TablePlayerData.Models;

import CardBase.Deck;

import java.util.ArrayList;

public record TableModel(ArrayList<PlayerModel> PlayerList, int TableID, Deck TableDeck) {
}
