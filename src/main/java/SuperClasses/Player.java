package SuperClasses;

import CardBase.Card;
import CardBase.Rank;
import CardBase.Suit;
import Casino.ID.IDCreator;
import Rules.HoldEm.HandStrengthModel;
import Rules.HoldEm.HoldEmHandCheckerViewModel;
import TablePlayerData.Enums.ActionEnum;
import TablePlayerData.Models.PlayerHandModel;
import TablePlayerData.Models.PlayerModel;
import TablePlayerData.ViewModels.PlayerHandViewModel;
import TablePlayerData.ViewModels.PlayerViewModel;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final PlayerModel Model;
    private final PlayerViewModel ViewModel;
    public final PlayerHandModel HandModel;
    private final PlayerHandViewModel HandViewModel;
    public Table assignedTable;
    public HandStrengthModel HandStrength;

    public Player(String Name, int Money) {
        Model = new PlayerModel(Name,new Stack(Money), IDCreator.getUniquePlayerID());
        HandModel = PlayerHandViewModel.getEmptyHand();
        HandViewModel = new PlayerHandViewModel();
        ViewModel = new PlayerViewModel();
    }

    public String getName() {
        return Model.Name();
    }

    public int getStack() {
        return Model.stack().getMoney();
    }

    public void printInfo() {
        ViewModel.printInfo(Model,HandViewModel,HandModel);
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

    public void setHandStrength(HoldEmHandCheckerViewModel ViewModel) {
        HandStrength = ViewModel.checkAndGetHandValue(this,assignedTable);
    }

    public boolean performAction(ActionEnum actionEnum) {
        if(actionEnum == ActionEnum.CAN_CHECK_OR_BET) {
            return ViewModel.wantsToBet();
        }
        if(actionEnum == ActionEnum.HAS_TO_CALL) {
            return ViewModel.wantsToCall();
        }
        return false;
    }

    public int getBetAction() {
        Random random = new Random();
        return random.nextInt(Model.stack().getMoney());
    }

    public int getCallActionMoney() {
        Random random = new Random();
    }

}
