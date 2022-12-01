package Casino.Data.Models;

import Table.Models.Table;

import java.util.ArrayList;

public record CasinoRecord(String Name, ArrayList<Table> TableArrayList) {
}
