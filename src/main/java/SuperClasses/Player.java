package SuperClasses;

import Casino.ID.IDCreator;
import Table.Models.PlayerHandModel;
import Table.Models.PlayerModel;
import Table.ViewModels.PlayerHandViewModel;
import Table.ViewModels.PlayerViewModel;

public class Player {
    private final PlayerModel Model;
    private final PlayerViewModel ViewModel = new PlayerViewModel();
    private final PlayerHandModel HandModel;
    private final PlayerHandViewModel HandViewModel = new PlayerHandViewModel();

    public Player(String Name, int Stack) {
        Model = new PlayerModel(Name,Stack, IDCreator.getUniquePlayerID());
        HandModel = PlayerHandViewModel.getEmptyHand();
    }

    public printInfo() {

    }
}
