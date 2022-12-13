package SuperClasses;

import CardBase.Card;
import CardBase.Deck;
import Casino.ID.IDCreator;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;
import TablePlayerData.ViewModels.TableCardsViewModel;
import TablePlayerData.ViewModels.TableViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    public TableModel Model;
    private TableViewModel ViewModel;
    private TableCardsModel CardsModel;
    private TableCardsViewModel CardsViewModel;
    public Table(int Capacity) {
        Model = new TableModel(new ArrayList<>(), IDCreator.getUniqueTableID(),Capacity);
        ViewModel = new TableViewModel();
        CardsViewModel = new TableCardsViewModel();
        CardsModel = new TableCardsModel(new Card[3],new Card[1],new Card[1],new Deck());
    }

    public void printInfo() {
        ViewModel.printInfo(Model);
        CardsViewModel.printCards(CardsModel);
    }

    public void addPlayer(Player player) {
        Model.PlayerList().add(player);
    }

    public void startNewRound() {
        CardsModel.TableDeck().shuffleDeck();
        CardsViewModel.dealTableCards(CardsModel);
        dealCardsToAllPlayers();
    }

    public void dealCardsToAllPlayers() {
        for(Player player:Model.PlayerList()) {
            player.setHand(CardsViewModel.dealCards(CardsModel,player));
        }
    }
    public ArrayList<Card> getAllCards() {
        ArrayList<Card> placeholder = new ArrayList<>(Arrays.asList(CardsModel.Flop()));
        placeholder.add(CardsModel.Turn()[0]);
        placeholder.add(CardsModel.River()[0]);
        return placeholder;
    }
}
