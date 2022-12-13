package TablePlayerData.ViewModels;

import CardBase.Card;
import SuperClasses.Player;
import SuperClasses.PrintMethods;
import TablePlayerData.Models.TableCardsModel;

public class TableCardsViewModel {

    public void printCards(TableCardsModel Model) {
        PrintMethods.printFiller(25,"-");
        System.out.println("Flop: ");
        for(Card card:Model.Flop()) {
            System.out.println(card.getName());
        }
        System.out.println("Turn: ");
        System.out.println(Model.Turn()[0].getName());
        System.out.println("River: ");
        System.out.println(Model.River()[0].getName());
    }

    public void dealTableCards(TableCardsModel Model) {
        for(int i = 0; i<Model.Flop().length; i++) {
            Model.Flop()[i] = Model.TableDeck().drawTopCard();
        }
        Model.Turn()[0] = Model.TableDeck().drawTopCard();
        Model.River()[0] = Model.TableDeck().drawTopCard();
    }

    public Card[] dealCards(TableCardsModel cardsModel, Player player) {
        return new Card[]{cardsModel.TableDeck().drawTopCard(),cardsModel.TableDeck().drawTopCard()};
    }

    public void setTableCards(Card[] cards,TableCardsModel Model) {
        System.arraycopy(cards, 0, Model.Flop(), 0, Model.Flop().length);
        Model.Turn()[0] = cards[3];
        Model.River()[0] = cards[4];
    }
}
