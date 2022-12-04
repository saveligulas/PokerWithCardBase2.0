package Table.ViewModels;

import CardBase.Card;
import Table.Enums.Status;
import Table.Models.PlayerHandModel;

public class PlayerHandViewModel {
    public static PlayerHandModel getEmptyHand(){
        return new PlayerHandModel(new Card[2], Status.STANDBY);
    }
    public static void printHand(PlayerHandModel handModel){
        if(!(handModel.Hand() == null)) {
            System.out.println(handModel.Hand()[0].getName());
            System.out.println(handModel.Hand()[1].getName());
        } else {
            System.out.println("Hand is empty.");
        }
    }
}
