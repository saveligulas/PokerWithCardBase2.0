package Casino.Data.Models;

import TablePlayerData.Models.TableModel;

import java.util.ArrayList;

public record CasinoModel(String Name, ArrayList<TableModel> TableArrayList) {
}
