package TablePlayerData.ViewModels;

import SuperClasses.Player;
import SuperClasses.Table;
import TablePlayerData.Enums.ActionEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ActionViewModel {
    private final Player player;
    private final Table table;
    private final ActionEnum anEnum;
    private int amountToCall = 0;
    private ArrayList<Boolean> chanceArrayList;
    private Random random = new Random();

    public ActionViewModel(ActionEnum anEnum, Player player, Table table) {
        this.anEnum = anEnum;
        this.table = table;
        this.player = player;
    }

    public ActionViewModel(int amount, ActionEnum anEnum, Player player, Table table) {
        this.anEnum = anEnum;
        this.table = table;
        this.player = player;
        amountToCall = amount;
    }

    public ActionEnum getAnEnum() {
        return anEnum;
    }

    public int canCheckOrBet() {
        setArrayListForChances(20);
        if(chanceArrayList.get(0)) {
            return random.nextInt(1,player.getStack()/4);
        }
        return 0;
    }

    public int hasToCall() {
        setArrayListForChances(50);
        if(chanceArrayList.get(0)) {
            if(chanceArrayList.get(1)) {
                return random.nextInt(amountToCall,player.getStack());
            }
            return amountToCall;
        }
        return 0;
    }

    public void setArrayListForChances(int split) {
        ArrayList<Boolean> placeholder = new ArrayList<>();
        for(int i = 0; i<split; i++) {
            placeholder.add(true);
        }
        for(int j = 0; j<100-split; j++) {
            placeholder.add(false);
        }
        Collections.shuffle(placeholder);
        chanceArrayList = placeholder;
    }
}

