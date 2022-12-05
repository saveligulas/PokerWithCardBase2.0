package TablePlayerData.ViewModels;

import CardBase.Card;
import TablePlayerData.Models.TableCardsModel;
import TablePlayerData.Models.TableModel;

public class TableCardsViewModel {
    public TableCardsModel getNewTableCards(TableModel TableModel, TableViewModel TableViewModel) {
        TableViewModel.shuffleDeck(TableModel);
        Card[] Flop = new Card[3];
        for(int i = 0; i<3; i++) {
            Card card = TableModel.TableDeck().drawTopCard();
            Flop[i] = card;
        }
        burnCard(TableModel);
        Card Turn = TableModel.TableDeck().drawTopCard();
        burnCard(TableModel);
        Card River = TableModel.TableDeck().drawTopCard();
        return new TableCardsModel(Flop,Turn,River);
    }
    public void burnCard(TableModel TableModel) {
        TableModel.TableDeck().drawTopCard();
    }
}
