package SuperClasses;

import CardBase.Deck;
import Casino.ID.IDCreator;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;
import TablePlayerData.ViewModels.TableCardsViewModel;
import TablePlayerData.ViewModels.TableViewModel;

import java.util.ArrayList;

public class Table {
    private final TableModel Model;
    private final TableViewModel ViewModel;
    private final TableCardsModel CardsModel;
    private final TableCardsViewModel CardsViewModel;
    public Table() {
        Model = new TableModel(new ArrayList<>(), IDCreator.getUniqueTableID(),new Deck());
        ViewModel = new TableViewModel();
        CardsViewModel = new TableCardsViewModel();
        CardsModel = CardsViewModel.getNewTableCards(Model,ViewModel);
    }
}
