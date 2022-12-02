package FileIO;

import Casino.ID.IDCreator;
import Table.Models.Player;
import Table.ViewModels.PlayerHandViewModel;
import Table.ViewModels.PlayerViewModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class FileReaderIO {
    public static Player ReadPlayer(){
        CSVReader reader = null;
        String path = FileIOPath.PLAYER.getPATH();
        try {
            reader = new CSVReader(new FileReader(path));
            try {
                String[] values = reader.readNext();
                String Name = values[0];
                int Stack = Integer.parseInt(values[1]);
                int ID = Integer.parseInt(values[2]);
                return new Player(Name,Stack,ID,PlayerHandViewModel.getEmptyHand(),new PlayerViewModel());
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Player> ReadAllPlayers() {
        CSVReader reader = null;
        String path = FileIOPath.PLAYER.getPATH();
        ArrayList<Player> playerArrayList = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(path));
            String[] line;
            try {
                while((line = reader.readNext()) != null) {
                    String Name = line[0];
                    int Stack = Integer.parseInt(line[1]);
                    Player player = new Player(Name,Stack,IDCreator.getUniquePlayerID(),PlayerHandViewModel.getEmptyHand(),new PlayerViewModel());
                    playerArrayList.add(player);
                }
                return(playerArrayList);
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void ReadTables(){

    }
    public void ReadCasinoData(){

    }
}
