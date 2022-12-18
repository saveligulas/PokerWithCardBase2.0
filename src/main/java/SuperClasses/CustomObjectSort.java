package SuperClasses;

import java.util.ArrayList;
import java.util.Comparator;

public final class CustomObjectSort {
    public static ArrayList<Player> sortByName(ArrayList<Player> playerList) {

        playerList.sort(new Comparator<Player>() {

            @Override
            public int compare(Player player1, Player player2) {
                Integer value1 = player1.HandStrength.Value();
                Integer value2 = player2.HandStrength.Value();
                return value1.compareTo(value2);
            }

        });
        return playerList;
    }
}
