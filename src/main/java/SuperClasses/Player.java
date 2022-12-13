package SuperClasses;

import CardBase.Card;
import CardBase.Rank;
import CardBase.Suit;
import Casino.ID.IDCreator;
import TablePlayerData.Models.PlayerHandModel;
import TablePlayerData.Models.PlayerModel;
import TablePlayerData.ViewModels.PlayerHandViewModel;
import TablePlayerData.ViewModels.PlayerViewModel;

import java.util.ArrayList;

public class Player {
    private final PlayerModel Model;
    private final PlayerViewModel ViewModel;
    public final PlayerHandModel HandModel;
    private final PlayerHandViewModel HandViewModel;
    public Table assignedTable;

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

    public ArrayList<Card> getHand() {
        ArrayList<Card> placeholder = new ArrayList<>();
        placeholder.add(HandModel.Hand()[0]);
        placeholder.add(HandModel.Hand()[1]);
        return placeholder;
    }

    public void setHandAdmin(Rank rank, Suit suit,Rank rank2, Suit suit2) {
        HandViewModel.setCard(new Card[]{new Card(rank,suit),new Card(rank2,suit2)},HandModel);
    }
}
