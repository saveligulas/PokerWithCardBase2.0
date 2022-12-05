package TablePlayerData.ViewModels;

import TablePlayerData.Models.TableModel;

public class TableViewModel {
    public void shuffleDeck(TableModel Model) {
        Model.TableDeck().shuffleDeck();
    }
}
