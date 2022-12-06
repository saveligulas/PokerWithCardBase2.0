package SuperClasses;

import Casino.Data.Models.CasinoModel;
import Casino.Data.ViewModels.CasinoViewModel;

import java.util.ArrayList;

public class Casino {
    private final CasinoModel Model;
    private final CasinoViewModel ViewModel;

    public Casino(String Name) {
        Model = new CasinoModel(Name,new ArrayList<>());
        ViewModel = new CasinoViewModel();
    }

    public void printInfo() {
        ViewModel.printInfo(Model);
    }
}
