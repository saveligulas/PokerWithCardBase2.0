package FileIO;

import SuperClasses.Player;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public final class FileWriterIO {

    public static void PlayerWriter(Player player){
        String path = FileIOPath.PLAYER.getPATH();
        CSVWriter writer = null;
        try{
            writer = new CSVWriter(new FileWriter(path,true),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.RFC4180_LINE_END);
            String[] values = new String[3];
            values[0] = player.Model.Name();
            values[1] = String.valueOf(player.Model.Stack());
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
