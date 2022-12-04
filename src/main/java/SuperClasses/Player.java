package SuperClasses;

import Casino.ID.IDCreator;
import Table.Models.PlayerHandModel;
import Table.Models.PlayerModel;
import Table.ViewModels.PlayerHandViewModel;

public class Player {
    public PlayerModel Model;
    public PlayerHandModel HandModel;
    public Player(String Name, int Stack) {
        Model = new PlayerModel(Name,Stack, IDCreator.getUniquePlayerID());
        HandModel = PlayerHandViewModel.getEmptyHand();
    }
}
