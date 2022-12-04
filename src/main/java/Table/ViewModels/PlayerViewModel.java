package Table.ViewModels;

import SuperClasses.Player;

public class PlayerViewModel {
    public static void printInfo(Player player) {
        System.out.println(player.Model.Name()+" | "+player.Model.Stack()+" | "+player.Model.ID());
    }
}
