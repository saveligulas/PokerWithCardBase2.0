package SuperClasses;

import TablePlayerData.Models.StackModel;
import TablePlayerData.ViewModels.StackViewModel;

public class Stack {
    StackModel Model;
    StackViewModel ViewModel;
    public Stack(int money) {
        Model = new StackModel(money);
        ViewModel = new StackViewModel();
    }

    public int getMoney() {
        return Model.Money;
    }
}
