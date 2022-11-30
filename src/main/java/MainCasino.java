import Casino.ID.IDCreator;
import FileIO.FileWriterIO;
import Table.Models.Player;

public class MainCasino {
    public static void main(String[] args) {
        Player testPlayer = new Player("Iva",5000, IDCreator.getUniquePlayerID());
        FileWriterIO fileWriterIO = new FileWriterIO();
        fileWriterIO.PlayerWriter(testPlayer);
    }
}
