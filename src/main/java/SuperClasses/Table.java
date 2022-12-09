package SuperClasses;

import CardBase.Card;
import CardBase.Deck;
import Casino.ID.IDCreator;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;
import TablePlayerData.ViewModels.TableCardsViewModel;
import TablePlayerData.ViewModels.TableViewModel;

import java.util.ArrayList;

public class Table {
    public TableModel Model;
    private TableViewModel ViewModel;
    private TableCardsModel CardsModel;
    private TableCardsViewModel CardsViewModel;
    public Table(int Capacity) {
        Model = new TableModel(new ArrayList<>(), IDCreator.getUniqueTableID(),Capacity);
        ViewModel = new TableViewModel();
        CardsViewModel = new TableCardsViewModel();
        CardsModel = new TableCardsModel(new Card[2],new Card(),new Card(),new Deck());
    }

    public void printInfo() {
        ViewModel.printInfo(Model);
        CardsViewModel.printCards(CardsModel);
    }

    public void addPlayer(Player player) {
        Model.PlayerList().add(player);
    }

    public void dealCardsToAllPlayers() {
        for(Player player:Model.PlayerList()) {
            CardsViewModel.dealCards(CardsModel,player);
        }
    }
}
