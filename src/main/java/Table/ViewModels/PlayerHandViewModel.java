package Table.ViewModels;

import CardBase.Card;
import Table.Enums.Status;
import Table.Models.PlayerHand;

public class PlayerHandViewModel {
    public static PlayerHand getEmptyHand(){
        return new PlayerHand(new Card[2], Status.STANDBY);
    }
}
