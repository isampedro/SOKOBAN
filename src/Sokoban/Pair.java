package Sokoban;
import java.util.Objects;

public class Pair {
    private Integer x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {

    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Pair subtract(Pair p ) {
        return new Pair(x - p.getX(), y - p.getY());
    }

    public Directions direction( Pair p) {
        Pair vector = this.subtract(p);
        if( vector.getX() > 0 && vector.getY() == 0 ){
            return Directions.Down;
        } else if ( vector.getX() < 0 && vector.getY() == 0 ) {
            return Directions.Up;
        } else if( vector.getX() == 0 && vector.getY() > 0 ) {
            return Directions.Right;
        } else {
            return Directions.Left;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x.equals(pair.x) &&
                y.equals(pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }
}
