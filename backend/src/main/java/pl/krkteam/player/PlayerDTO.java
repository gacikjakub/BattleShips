package pl.krkteam.player;

public class PlayerDTO {

    public String name;

    @Override
    public String toString() {
        return "Player: {name: " + name + "}";
    }

    public PlayerDTO(String name) {
        this.name = name;
    }

    public PlayerDTO() {
    }


}
