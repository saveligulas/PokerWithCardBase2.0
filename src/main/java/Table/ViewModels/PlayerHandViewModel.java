package Table.ViewModels;

import CardBase.Card;
import Table.Enums.Status;
import Table.Models.PlayerHandModel;

public class PlayerHandViewModel {
    public static PlayerHandModel getEmptyHand(){
        return new PlayerHandModel(new Card[0], Status.STANDBY);
    }
    public String getHand(PlayerHandModel handModel){
        if(!(handModel.Hand().length == 0)) {
            return handModel.Hand()[0].getName()+", "+handModel.Hand()[1].getName();
        } else {
            return "Hand is empty";
        }
    }
}
