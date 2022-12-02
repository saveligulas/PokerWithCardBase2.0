package Casino.Data.Models;

import Table.Models.TableModel;

import java.util.ArrayList;

public record CasinoRecord(String Name, ArrayList<TableModel> TableArrayList) {
}
