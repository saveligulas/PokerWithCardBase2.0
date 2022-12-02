package Table.Models;

import Table.ViewModels.PlayerViewModel;

import java.io.Serializable;

public record Player(String Name, int Stack, int ID, PlayerHand Hand, PlayerViewModel ViewModel) implements Serializable {
}
