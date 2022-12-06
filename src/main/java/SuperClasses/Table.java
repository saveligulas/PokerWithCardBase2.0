package SuperClasses;

import CardBase.Deck;
import Casino.ID.IDCreator;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;
import TablePlayerData.ViewModels.TableCardsViewModel;
import TablePlayerData.ViewModels.TableViewModel;

import java.util.ArrayList;

public class Table {
    public final TableModel Model;
    private final TableViewModel ViewModel;
    private TableCardsModel CardsModel;
    private final TableCardsViewModel CardsViewModel;
    public Table(int Capacity) {
        Model = new TableModel(new ArrayList<>(), IDCreator.getUniqueTableID(),new Deck(),Capacity);
        ViewModel = new TableViewModel();
        CardsViewModel = new TableCardsViewModel();
        CardsModel = CardsViewModel.getNewTableCards(Model,ViewModel);
    }

    public void resetCards() {
        CardsModel = CardsViewModel.getNewTableCards(Model,ViewModel);
    }

    public void printInfo() {
        ViewModel.printInfo(Model);
        CardsViewModel.printCards(CardsModel);
    }

    public void addPlayer(Player player) {
        Model.PlayerList().add(player);
    }
}
