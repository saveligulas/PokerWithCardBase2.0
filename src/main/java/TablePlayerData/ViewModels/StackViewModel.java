package TablePlayerData.ViewModels;

import TablePlayerData.Models.StackModel;

public class StackViewModel {
    public void commitMoney(StackModel Model, int amount) {
        Model.CommittedMoney += amount;
    }

    public void updateTotalWinnings(StackModel Model, int amount) {
        Model.TotalWinnings += amount;
    }

    public void updateEnteredPot(StackModel Model, int newId) {
        Model
    }
}
