package Table.Models;

import CardBase.Deck;

import java.util.ArrayList;

public record Table(ArrayList<Player> PlayerList, int TableID, Deck TableDeck) {
}
