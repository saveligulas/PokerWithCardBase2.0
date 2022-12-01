package Table.Models;

import CardBase.Card;
import Table.Enums.Status;

public record PlayerHand(Card[] Hand, Status PlayerStatus) {
}
