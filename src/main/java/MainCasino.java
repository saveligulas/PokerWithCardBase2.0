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
        table.printInfo();
        System.out.println(test.checkAndGetHandValue(player,table));
    }
}
