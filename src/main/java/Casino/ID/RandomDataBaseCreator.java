package Casino.ID;

import SuperClasses.Player;
import SuperClasses.Table;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Random;

public class RandomDataBaseCreator {
    private final Random rnd = new Random();
    private final Faker faker = new Faker();
    private final Random rand = new Random();
    public ArrayList<Player> createRandomPlayerList(int amount) {
        ArrayList<Player> playerArrayList = new ArrayList<>();
        for(int i = 0; i<amount; i++) {
            String name = faker.name().firstName();
            int stack = rand.nextInt(100, 50000);
            int ID = IDCreator.getUniquePlayerID();
            Player player = new Player(name,stack);
            playerArrayList.add(player);
        }
        return playerArrayList;
    }

    public ArrayList<Table> createRandomTablesList(int amount, int capMin, int capMax) {
        ArrayList<Table> placeholderList = new ArrayList<>();
        for(int i = 0; i<amount; i++) {
            placeholderList.add(new Table(rnd.nextInt(capMin,capMax)));
        }
        return placeholderList;
    }

    public String getRandomCasinoName() {
        return faker.hacker().adjective()+" Casino";
    }
}
