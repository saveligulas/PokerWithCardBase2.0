package TablePlayerData.Models;

import CardBase.Card;

public record TableCardsModel(Card[] Flop, Card Turn, Card River) {
}
