package SuperClasses;

import Casino.Data.Models.CasinoModel;
import Casino.Data.ViewModels.CasinoViewModel;

import java.util.ArrayList;

public class Casino {
    private final CasinoModel Model;
    private final CasinoViewModel ViewModel;
    private final ArrayList<Player> PlayersWithoutTableList = new ArrayList<>();

    public Casino(String Name) {
        Model = new CasinoModel(Name,new ArrayList<>());
        ViewModel = new CasinoViewModel();
    }

    public void printInfo() {
        ViewModel.printInfo(Model);
    }

    public void addTable(Table table) {
        Model.TableArrayList().add(table);
    }
    public void addPlayersWithoutTable(ArrayList<Player> list) {
        PlayersWithoutTableList.addAll(list);
    }

    public void initializeCasino(int tableAmount,int tableCapacityRangeMin,int tableCapacityRangeMax) {
        ViewModel.createCasinoWithTablesAndLoadPlayerDataSheet(this,tableAmount,tableCapacityRangeMin,tableCapacityRangeMax);
    }

    public void startNewRoundAtAllTables() {
        for(Table table:Model.TableArrayList()) {
            ViewModel.startNewRoundAtTable(table);
        }
    }
}
