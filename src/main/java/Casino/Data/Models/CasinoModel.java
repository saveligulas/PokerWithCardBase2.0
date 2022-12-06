package Casino.Data.Models;

import SuperClasses.Table;

import java.util.ArrayList;

public record CasinoModel(String Name, ArrayList<Table> TableArrayList) {
}
