package SuperClasses;

import Casino.ID.IDCreator;
import TablePlayerData.Models.PlayerHandModel;
import TablePlayerData.Models.PlayerModel;
import TablePlayerData.ViewModels.PlayerHandViewModel;
import TablePlayerData.ViewModels.PlayerViewModel;

public class Player {
    private final PlayerModel Model;
    private final PlayerViewModel ViewModel = new PlayerViewModel();
    private final PlayerHandModel HandModel;
    private final PlayerHandViewModel HandViewModel = new PlayerHandViewModel();

    public Player(String Name, int Stack) {
        Model = new PlayerModel(Name,Stack, IDCreator.getUniquePlayerID());
        HandModel = PlayerHandViewModel.getEmptyHand();
    }

    public String getName() {
        return Model.Name();
    }

    public int getStack() {
        return Model.Stack();
    }

    public void printInfo() {
        PrintMethods.printFiller(25,"-");
        System.out.println();
        System.out.println(ViewModel.getInfo(Model));
        System.out.println(HandViewModel.getHand(HandModel));
    }
}
