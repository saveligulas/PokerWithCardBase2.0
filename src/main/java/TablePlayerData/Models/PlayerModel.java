package TablePlayerData.Models;

import java.io.Serializable;

public record PlayerModel(String Name, int Stack, int ID) implements Serializable {
}
