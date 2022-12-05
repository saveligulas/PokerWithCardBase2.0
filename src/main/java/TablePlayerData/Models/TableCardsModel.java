package TablePlayerData.Models;

import CardBase.Card;
import TablePlayerData.ViewModels.TableCardsViewModel;

public record TableCardsModel(Card[] Flop, Card Turn, Card River, TableCardsViewModel ViewModel) {
}
