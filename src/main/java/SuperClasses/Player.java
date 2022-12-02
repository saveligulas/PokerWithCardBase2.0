package SuperClasses;

import Casino.ID.IDCreator;
import Table.Enums.Status;
import Table.Models.PlayerHandModel;
import Table.ViewModels.PlayerHandViewModel;
import Table.ViewModels.PlayerViewModel;

public class Player {
    public Table.Models.Player Model;
    PlayerViewModel ViewModel;
    public Player(String Name, int Stack) {
        Model = new Table.Models.Player(Name,Stack, IDCreator.getUniquePlayerID(),new PlayerHandModel(PlayerHandViewModel.getEmptyHand()));
        ViewModel = new PlayerViewModel();
    }
}
