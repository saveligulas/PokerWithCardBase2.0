package FileIO;

import Table.Models.*;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWriter {
    private final String CasinoDataSheetPath = "src/main/resources/CasinoDataSheet.csv";
    private final String PlayerSheetPath = "src/main/resources/PlayerSheet.csv";
    private final String TableSheetPath = "src/main/resources/TableSheet.csv";
    public void PlayerWriter(Player player){
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(PlayerSheetPath), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
            String[] values = new String[3];
            values[0] = player.Name();
            values[1] = String.valueOf(player.Stack());
            values[2] = String.valueOf(player.ID());
            writer.writeNext(values,false);
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
