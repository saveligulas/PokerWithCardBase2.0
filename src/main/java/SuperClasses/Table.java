package SuperClasses;

import CardBase.Deck;
import Casino.ID.IDCreator;
import TablePlayerData.Models.TableModel;
import TablePlayerData.ViewModels.TableViewModel;

import java.util.ArrayList;

public class Table {
    private final TableModel Model;
    private final TableViewModel ViewModel;
    public Table() {
        Model = new TableModel(new ArrayList<>(), IDCreator.getUniqueTableID(),new Deck());
        ViewModel = new TableViewModel();
    }
}
