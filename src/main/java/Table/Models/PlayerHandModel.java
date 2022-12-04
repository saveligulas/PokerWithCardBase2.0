package Table.Models;

import CardBase.Card;
import Table.Enums.Status;

public record PlayerHandModel(Card[] Hand, Status PlayerStatus) {
}
