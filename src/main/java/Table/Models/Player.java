package Table.Models;

import java.io.Serializable;

public record Player(String Name, int Stack, int ID,PlayerHand Hand) implements Serializable {
}
