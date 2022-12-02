package SuperClasses;

import Casino.ID.IDCreator;
import Table.ViewModels.PlayerViewModel;

public class Player {
    public Table.Models.Player Model;
    PlayerViewModel ViewModel;
    public Player(String Name, int Stack) {
        Model = new Table.Models.Player(Name,Stack, IDCreator.getUniquePlayerID());
        ViewModel = new PlayerViewModel();
    }
}
