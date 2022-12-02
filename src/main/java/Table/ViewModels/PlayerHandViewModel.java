package Table.ViewModels;

import CardBase.Card;
import Table.Enums.Status;
import Table.Models.PlayerHandModel;

public class PlayerHandViewModel {
    Card[] DealtHand = new Card[2];
    public static PlayerHandModel getEmptyHand(){
        return new PlayerHandModel(new Card[2], Status.STANDBY,new PlayerHandViewModel());
    }
    public void printHand(){
        if(!(DealtHand == null)) {
            System.out.println(DealtHand[0].getName());
            System.out.println(DealtHand[1].getName());
        } else {
            System.out.println("Hand is empty.");
        }
    }
}
