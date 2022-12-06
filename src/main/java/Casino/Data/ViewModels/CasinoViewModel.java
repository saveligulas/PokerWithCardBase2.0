package Casino.Data.ViewModels;

import Casino.Data.Models.CasinoModel;
import SuperClasses.PrintMethods;
import SuperClasses.Table;

public class CasinoViewModel {
    public void printInfo(CasinoModel Model) {
        PrintMethods.printFiller(25, "-");
        System.out.println(Model.Name());
        for(Table table:Model.TableArrayList()) {
            table.printInfo();
        }
    }
}
