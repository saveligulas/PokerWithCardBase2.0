package TablePlayerData.ViewModels;

import TablePlayerData.Models.PlayerModel;

public class PlayerViewModel {
    public String getInfo(PlayerModel Model) {
       return Model.Name()+" | Stack: "+Model.Stack()+"£ | ID: "+Model.ID();
    }
}
