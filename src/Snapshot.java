import java.util.Arrays;
import java.util.Objects;

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

    public void setMovingBox(boolean movingBox) {
        this.movingBox = movingBox;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snapshot snapshot = (Snapshot) o;
        return movingBox == snapshot.movingBox &&
               direction == snapshot.direction &&
                new Sokoban(game).equals(new Sokoban(snapshot.game));
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(movingBox, direction);
        result = 31 * result + Arrays.hashCode(game);
        return result;
    }
}
