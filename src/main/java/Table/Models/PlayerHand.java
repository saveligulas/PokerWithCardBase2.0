package Table.Models;

import CardBase.Card;
import Table.Enums.Status;
import Table.ViewModels.PlayerHandViewModel;

public record PlayerHand(Card[] Hand, Status PlayerStatus, PlayerHandViewModel ViewModel) {
}
