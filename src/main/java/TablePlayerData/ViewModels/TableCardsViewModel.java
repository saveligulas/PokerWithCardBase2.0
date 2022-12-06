package TablePlayerData.ViewModels;

import CardBase.Card;
import SuperClasses.PrintMethods;
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

    public void printCards(TableCardsModel Model) {
        PrintMethods.printFiller(25,"-");
        System.out.println("Flop: ");
        for(Card card:Model.Flop()) {
            System.out.println(card.getName());
        }
        System.out.println("Turn: ");
        System.out.println(Model.Turn().getName());
        System.out.println("River: ");
        System.out.println(Model.River().getName());
    }
}
