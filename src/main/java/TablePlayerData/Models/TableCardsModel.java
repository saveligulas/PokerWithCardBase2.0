package TablePlayerData.Models;

import CardBase.Card;
import CardBase.Deck;

public record TableCardsModel(Card[] Flop, Card Turn, Card River, Deck TableDeck) {
}
