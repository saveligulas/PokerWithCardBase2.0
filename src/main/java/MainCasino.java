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
        Player player1 = new Player("Iva",5000);
        Player player2 = new Player("Saveli",5000);
        Player player3 = new Player("Marfa",5000);
        table.addPlayer(player1);
        table.addPlayer(player2);
        table.addPlayer(player3);
        table.startNewRound();
        table.printInfo();
        table.checkForWinner();
    }
}
