package Table.ViewModels;

import Table.Models.PlayerModel;

public class PlayerViewModel {
    public String getInfo(PlayerModel Model) {
       return Model.Name()+" | Stack: "+Model.Stack()+"£ | ID: "+Model.ID();
    }
}
