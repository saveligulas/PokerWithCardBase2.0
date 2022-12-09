package SuperClasses;

import CardBase.Card;
import Casino.ID.IDCreator;
import TablePlayerData.Models.PlayerHandModel;
import TablePlayerData.Models.PlayerModel;
import TablePlayerData.ViewModels.PlayerHandViewModel;
import TablePlayerData.ViewModels.PlayerViewModel;

public class Player {
    private final PlayerModel Model;
    private final PlayerViewModel ViewModel;
    public final PlayerHandModel HandModel;
    private final PlayerHandViewModel HandViewModel;

    public Player(String Name, int Stack) {
        Model = new PlayerModel(Name,Stack, IDCreator.getUniquePlayerID());
        HandModel = PlayerHandViewModel.getEmptyHand();
        HandViewModel = new PlayerHandViewModel();
        ViewModel = new PlayerViewModel();
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

    public void setHand(Card[] DealtHand) {
        HandViewModel.setCard(DealtHand,HandModel);
    }
}
