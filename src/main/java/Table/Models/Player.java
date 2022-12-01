package Table.Models;

import CardBase.Card;

import java.io.Serializable;

public record Player(String Name, int Stack, int ID, Card[] Hand) implements Serializable {
}
