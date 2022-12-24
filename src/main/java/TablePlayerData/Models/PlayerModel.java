package TablePlayerData.Models;

import SuperClasses.Stack;

import java.io.Serializable;

public record PlayerModel(String Name, Stack stack, int ID) implements Serializable {
}
