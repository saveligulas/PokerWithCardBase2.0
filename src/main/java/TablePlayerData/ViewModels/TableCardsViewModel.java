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
        System.out.println(Model.Turn().getName());
        System.out.println("River: ");
        System.out.println(Model.River().getName());
    }

    public void dealCards(TableCardsModel cardsModel, Player player) {
        Card[] HandFromDeck = new Card[]{cardsModel.TableDeck().drawTopCard(),cardsModel.TableDeck().drawTopCard()};
        player.setHand(HandFromDeck);
    }
}
