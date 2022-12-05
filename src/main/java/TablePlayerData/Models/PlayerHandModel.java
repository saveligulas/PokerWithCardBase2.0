package TablePlayerData.Models;

import CardBase.Card;
import TablePlayerData.Enums.Status;

public record PlayerHandModel(Card[] Hand, Status PlayerStatus) {
}
