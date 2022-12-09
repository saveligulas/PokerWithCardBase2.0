package TablePlayerData.ViewModels;

import CardBase.Card;
import TablePlayerData.Enums.Status;
import TablePlayerData.Models.PlayerHandModel;

public class PlayerHandViewModel {
    public static PlayerHandModel getEmptyHand(){
        return new PlayerHandModel(new Card[2], Status.STANDBY);
    }
    public String getHand(PlayerHandModel handModel){
        if(!(handModel.Hand().length == 0)) {
            return handModel.Hand()[0].getName()+", "+handModel.Hand()[1].getName();
        } else {
            return "*Hand is empty*";
        }
    }

    public void setCard(Card[] cards,PlayerHandModel Model) {
                           Model.Hand()[0] = cards[0];
        Model.Hand()[1] = cards[1];
    }
}
