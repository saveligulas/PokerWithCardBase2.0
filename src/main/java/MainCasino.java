import CardBase.Rank;
import CardBase.Suit;
import Rules.HoldEm.HoldEmHandCheckerViewModel;
import SuperClasses.Player;
import SuperClasses.Table;

public class MainCasino {
    public static void main(String[] args) {
//        ArrayList<Player> list = FileReaderIO.ReadAllPlayers();
//        System.out.println(list);
//        Casino casino = new Casino(RandomDataBaseCreator.getRandomCasinoName());
//        casino.initializeCasino(100,5,10);
//        casino.startNewRoundAtAllTables();
//        casino.printInfo();
        Table table = new Table(5);
        Player player = new Player("Iva",5000);
        table.addPlayer(player);
        table.startNewRound();
        HoldEmHandCheckerViewModel test = new HoldEmHandCheckerViewModel();
        player.setHandAdmin(Rank.ACE, Suit.HEARTS,Rank.FIVE,Suit.HEARTS);
        table.setCardsAdmin(Rank.TWO,Suit.HEARTS,Rank.THREE,Suit.HEARTS,Rank.FOUR,Suit.HEARTS,Rank.FIVE,Suit.CLUBS,Rank.SEVEN,Suit.CLUBS);
        table.printInfo();
        System.out.println(test.checkAndGetHandValue(player,table));
    }
}
