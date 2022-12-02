package Table.Models;

import java.io.Serializable;

public record Player(String Name, int Stack, int ID,PlayerHandModel HandModel) implements Serializable {
}
