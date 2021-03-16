package Sokoban;

public class Snapshot {
    private  Character[][] game;
    private  boolean movingBox;
    private  Directions direction;

    public Snapshot(Character[][] snapshot, Directions direction, boolean movingBox) {
        this.game = snapshot;
        this.direction = direction;
        this.movingBox = movingBox;
    }

    public Character[][] getGame() {
        return game;
    }

    public void setGame(Character[][] game) {
        this.game = game;
    }

    public boolean isMovingBox() {
        return movingBox;
    }

    public Directions getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snapshot snapshot = (Snapshot) o;
        Sokoban gameThis = new Sokoban(game), gameThat = new Sokoban(snapshot.game);
        return gameThis.getBoxesPositions().equals(gameThat.getBoxesPositions())
                && gameThis.getPlayer().equals(gameThat.getPlayer());
    }

    @Override
    public int hashCode() {
        Sokoban gameThis = new Sokoban(game);
        return 31*gameThis.getBoxesPositions().hashCode() + gameThis.getPlayer().hashCode();
    }
}
