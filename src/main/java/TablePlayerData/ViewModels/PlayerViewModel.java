package TablePlayerData.ViewModels;

import SuperClasses.PrintMethods;
import SuperClasses.Table;
import TablePlayerData.Models.PlayerHandModel;
import TablePlayerData.Models.PlayerModel;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerViewModel {
    private ArrayList<Boolean> chanceArrayList = new ArrayList<>();
    public String getInfo(PlayerModel Model) {
       return Model.Name()+" | Stack: "+Model.stack().getMoney()+"£ | ID: "+Model.ID();
    }

    public void betMoney(PlayerModel Model, Table table, int amount) {
        if(amount <= Model.stack().getMoney()) {
            Model.stack().betCallMoney(amount);
        }
    }

    public void printInfo(PlayerModel Model, PlayerHandViewModel HandViewModel, PlayerHandModel HandModel) {
        PrintMethods.printFiller(25,"-");
        System.out.println();
        System.out.println(getInfo(Model));
        System.out.println(HandViewModel.getHand(HandModel));
    }

    public boolean wantsToBet() {
        updateChanceArrayList(33);
        return chanceArrayList.get(0);
    }

    public boolean wantsToCall() {
        updateChanceArrayList(66);
        return chanceArrayList.get(0);
    }

    public void updateChanceArrayList(int chance) {
        chanceArrayList.clear();
        for(int i = 0; i<chance; i++) {
            chanceArrayList.add(true);
        }
        for(int j = 0; j<100-chance; j++) {
            chanceArrayList.add(false);
        }
        Collections.shuffle(chanceArrayList);
    }
}
