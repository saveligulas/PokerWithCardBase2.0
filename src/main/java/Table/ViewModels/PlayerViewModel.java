package Table.ViewModels;

import SuperClasses.Player;

public class PlayerViewModel {
    public String getInfo(Player player) {
       return player.Model.Name()+" | "+player.Model.Stack()+" | "+player.Model.ID();
    }
}
