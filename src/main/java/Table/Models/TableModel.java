package Table.Models;

import CardBase.Deck;
import Table.ViewModels.TableViewModel;

import java.util.ArrayList;

public record TableModel(ArrayList<PlayerModel> PlayerList, int TableID, Deck TableDeck, TableCardsModel Cards, TableViewModel ViewModel) {
}
