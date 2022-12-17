package SuperClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class CustomObjectSort {
    public static ArrayList<Player> sortByName(ArrayList<Player> playerList) {

        Collections.sort(playerList, new Comparator<Player>() {

            @Override
            public int compare(Player player1, Player player2) {
                Integer value1 = player1.HandStrength.Value();
                Integer value2 = player2.HandStrength.Value();
                return player1.HandStrength.Value().compareTo(player2.HandStrength.Value());
            }

        });
        return playerList;
    }
}
