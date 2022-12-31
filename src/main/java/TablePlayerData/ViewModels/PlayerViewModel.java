package TablePlayerData.ViewModels;

import SuperClasses.Table;
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
}
