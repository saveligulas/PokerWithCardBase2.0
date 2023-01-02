package TablePlayerData.ViewModels;

import SuperClasses.PrintMethods;
import SuperClasses.Table;
import TablePlayerData.Models.PlayerHandModel;
import TablePlayerData.Models.PlayerModel;

public class PlayerViewModel {
    public String getInfo(PlayerModel Model) {
       return Model.Name()+" | Stack: "+Model.stack().getMoney()+"£ | ID: "+Model.ID();
    }

    public void betMoney(PlayerModel Model, Table table, int amount) {
        if(amount <= Model.stack().getMoney()) {
            Model.stack().betCallMoney(amount);
        }
    }

    public void printInfo(PlayerModel Model, PlayerHandViewModel HandViewModel, PlayerHandModel HandModel) {
        PrintMethods.printFiller(25,"-");
        System.out.println();
        System.out.println(getInfo(Model));
        System.out.println(HandViewModel.getHand(HandModel));
    }
}
