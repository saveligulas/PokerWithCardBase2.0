package TablePlayerData.ViewModels;

import TablePlayerData.Models.StackModel;

public class StackViewModel {
    public void commitMoney(StackModel Model, int amount) {
        Model.CommittedMoney += amount;
    }

    public void updateTotalWinnings() {

    }

    public void updateEnteredPot() {

    }
}
