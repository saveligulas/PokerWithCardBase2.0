package SuperClasses;

import CardBase.Card;
import CardBase.Deck;
import CardBase.Rank;
import CardBase.Suit;
import Casino.ID.IDCreator;
import Rules.HoldEm.HoldEmHandCheckerViewModel;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;
import TablePlayerData.ViewModels.TableCardsViewModel;
import TablePlayerData.ViewModels.TableViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Table {
    public TableModel Model;
    private TableViewModel ViewModel;
    private TableCardsModel CardsModel;
    private TableCardsViewModel CardsViewModel;
    public HoldEmHandCheckerViewModel HandCheckerViewModel;
    private HashMap<Integer[],ArrayList<Player>> potIdPlayerHashMap = new HashMap<>();
    public Table(int Capacity) {
        Model = new TableModel(new ArrayList<>(), IDCreator.getUniqueTableID(),Capacity,new Player[2]);
        ViewModel = new TableViewModel();
        CardsViewModel = new TableCardsViewModel();
        CardsModel = new TableCardsModel(new Card[3],new Card[1],new Card[1],new Deck());
        HandCheckerViewModel = new HoldEmHandCheckerViewModel();
    }

    public void printInfo() {
        ViewModel.printInfo(Model);
        CardsViewModel.printCards(CardsModel);
    }

    public void addPlayer(Player player) {
        Model.PlayerList().add(player);
        player.assignedTable = this;
    }

    public void startNewRound() {
        ViewModel.setupPotIds(potIdPlayerHashMap,Model);
        CardsModel.TableDeck().shuffleDeck();
        CardsViewModel.dealFlop(CardsModel);
        CardsViewModel.dealTurn(CardsModel);
        CardsViewModel.dealRiver(CardsModel);
        dealCardsToAllPlayers();
    }

    public void dealCardsToAllPlayers() {
        for(Player player:Model.PlayerList()) {
            player.setHand(CardsViewModel.dealCards(CardsModel,player));
        }
    }

    public void checkForWinner() {
        ViewModel.checkForWinner(this);
    }
    public ArrayList<Card> getAllCards() {
        ArrayList<Card> placeholder = new ArrayList<>(Arrays.asList(CardsModel.Flop()));
        placeholder.add(CardsModel.Turn()[0]);
        placeholder.add(CardsModel.River()[0]);
        return placeholder;
    }

    public void setCardsAdmin(Rank rank1, Suit suit1, Rank rank2, Suit suit2, Rank rank3, Suit suit3, Rank rank4, Suit suit4, Rank rank5, Suit suit5) {
        CardsViewModel.setTableCards(new Card[]{new Card(rank1,suit1),new Card(rank2,suit2),new Card(rank3,suit3),new Card(rank4,suit4),new Card(rank5,suit5)},CardsModel);
    }

    public void updateAssignedTableOnPlayers() {
        for(Player player:Model.PlayerList()) {
            player.assignedTable = this;
        }
    }
}
