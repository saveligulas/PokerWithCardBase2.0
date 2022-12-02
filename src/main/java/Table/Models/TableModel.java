package Table.Models;

import CardBase.Deck;
import Table.ViewModels.TableViewModel;

import java.util.ArrayList;

public record TableModel(ArrayList<Player> PlayerList, int TableID, Deck TableDeck, TableCardsModel Cards, TableViewModel ViewModel) {
}
