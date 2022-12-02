package Table.Models;

import CardBase.Card;
import Table.Enums.Status;
import Table.ViewModels.PlayerHandViewModel;

public record PlayerHandModel(Card[] Hand, Status PlayerStatus, PlayerHandViewModel ViewModel) {
    public PlayerHandModel(PlayerHandModel emptyHand) {
        this.Hand = emptyHand.Hand();
        this.PlayerStatus = emptyHand.PlayerStatus;
        this.ViewModel = emptyHand.ViewModel();
    }
}
