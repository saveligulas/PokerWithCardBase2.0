package SuperClasses;

import Casino.Data.Models.CasinoModel;
import Casino.Data.ViewModels.CasinoViewModel;

import java.util.ArrayList;

public class Casino {
    private final CasinoModel Model;
    private final CasinoViewModel ViewModel;

    public Casino() {
        Model = new CasinoModel("Starlet-Casino",new ArrayList<>());
        ViewModel = new CasinoViewModel();
    }
}
