package Table.Models;

import CardBase.Card;
import Table.ViewModels.TableCardsViewModel;

public record TableCardsModel(Card[] Flop, Card Turn, Card River, TableCardsViewModel ViewModel) {
}
