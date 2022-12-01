package FileIO;

import Table.Models.Player;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterIO {

    public void PlayerWriter(Player player){
        String path = FileIOPath.PLAYER.getPATH();
        CSVWriter writer = null;

        try{
            writer = new CSVWriter(new FileWriter(path),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.RFC4180_LINE_END);
            String[] values = new String[3];
            values[0] = player.Name();
            values[1] = String.valueOf(player.Stack());
            writer.writeNext(values);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void TableWriter(){

    }
    public void CasinoDataWriter(){

    }
}
