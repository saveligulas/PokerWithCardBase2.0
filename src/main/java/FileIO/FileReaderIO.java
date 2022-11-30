package FileIO;

import Table.Models.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderIO {
    public Player ReadPlayer(){
        CSVReader reader = null;
        String path = FileIOPath.PLAYER.getPATH();
        try {
            reader = new CSVReader(new FileReader(path));
            try {
                String[] values = reader.readNext();
                String Name = values[0];
                int Stack = Integer.parseInt(values[1]);
                int ID = Integer.parseInt(values[2]);
                return new Player(Name,Stack,ID);
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
