import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Sokoban {
    private final Cells[][] gameMap;
    private int x = 7;
    private int y = 7;
    private  Pair player;
    private  Directions direction;
    private final List<Pair> boxesPositions, wallsPositions, objectivesPositions;
    private final int steppedObjectives;
    private  boolean movingBox;

    Sokoban( Snapshot game ) {
        this.direction = game.getDirection();
        this.movingBox = game.isMovingBox();
        boxesPositions = new LinkedList<>();
        wallsPositions = new LinkedList<>();
        int steppedObjectives = 0;
        objectivesPositions = new LinkedList<>();
        gameMap = new Cells[game.getGame().length][];
        for( int i = 0; i < game.getGame().length; i++ ) {
            gameMap[i] = new Cells[game.getGame()[i].length];
            for( int j = 0; j < game.getGame()[i].length; j++ ) {
                if( game.getGame()[i][j] != null ) {
                    switch (game.getGame()[i][j]) {
                        case '#':
                            gameMap[i][j] = Cells.Wall;
                            wallsPositions.add(new Pair(i,j));
                            break;
                        case '$':
                            gameMap[i][j] = Cells.Box;
                            boxesPositions.add(new Pair(i,j));
                            break;
                        case '.':
                            gameMap[i][j] = Cells.Objective;
                            objectivesPositions.add(new Pair(i,j));
                            break;
                        case '@':
                            gameMap[i][j] = Cells.Player;
                            player = new Pair(i,j);
                            break;
                        case 'o':
                            gameMap[i][j] = Cells.BoxOnObjective;
                            objectivesPositions.add(new Pair(i,j));
                            boxesPositions.add(new Pair(i,j));
                            steppedObjectives++;
                            break;
                        case '+':
                            gameMap[i][j] = Cells.PlayerOnObjective;
                            objectivesPositions.add(new Pair(i, j));
                            player = new Pair(i, j);
                            break;
                    }
                }
            }
        }
        this.steppedObjectives = steppedObjectives;
    }

    Sokoban(Character[][] game ) {
        boxesPositions = new LinkedList<>();
        wallsPositions = new LinkedList<>();
        int steppedObjectives = 0;
        objectivesPositions = new LinkedList<>();
        gameMap = new Cells[game.length][];
        for( int i = 0; i < game.length; i++ ) {
            gameMap[i] = new Cells[game[i].length];
            for( int j = 0; j < game[i].length; j++ ) {
                if( game[i][j] != null ) {
                    switch (game[i][j]) {
                        case '#':
                            gameMap[i][j] = Cells.Wall;
                            wallsPositions.add(new Pair(i,j));
                            break;
                        case '$':
                            gameMap[i][j] = Cells.Box;
                            boxesPositions.add(new Pair(i,j));
                            break;
                        case '.':
                            gameMap[i][j] = Cells.Objective;
                            objectivesPositions.add(new Pair(i,j));
                            break;
                        case '@':
                            gameMap[i][j] = Cells.Player;
                            player = new Pair(i,j);
                            break;
                        case '+':
                            gameMap[i][j] = Cells.PlayerOnObjective;
                            objectivesPositions.add(new Pair(i,j));
                            player = new Pair(i,j);
                            break;
                        case 'o':
                            gameMap[i][j] = Cells.BoxOnObjective;
                            objectivesPositions.add(new Pair(i,j));
                            boxesPositions.add(new Pair(i, j));
                            steppedObjectives++;
                            break;
                    }
                }
            }
        }
        this.steppedObjectives = steppedObjectives;
    }

    private Sokoban( List<Pair> walls, List<Pair> boxes, List<Pair> objectives, Pair player, int x, int y, Directions direction, boolean movingBox ) {
        this.gameMap = new Cells[x][y];
        this.player = player;
        this.boxesPositions = boxes;
        this.wallsPositions = walls;
        this.objectivesPositions = objectives;
        this.direction = direction;
        this.movingBox = movingBox;
        int steppedObjectives = 0;

        for (Pair wall : walls) {
            this.gameMap[wall.getX()][wall.getY()] = Cells.Wall;
        }
        for( Pair box : boxes ) {
            this.gameMap[box.getX()][box.getY()] = Cells.Box;
        }
        for( Pair objective : objectives ) {
            if( isBox(gameMap[objective.getX()][objective.getY()]) ) {
                this.gameMap[objective.getX()][objective.getY()] = Cells.BoxOnObjective;
                steppedObjectives++;
            } else {
                this.gameMap[objective.getX()][objective.getY()] = Cells.Objective;
            }
        }
        this.steppedObjectives = steppedObjectives;

        if( isObjective(gameMap[player.getX()][player.getY()]) ) {
            this.gameMap[player.getX()][player.getY()] = Cells.PlayerOnObjective;
        } else {
            this.gameMap[player.getX()][player.getY()] = Cells.Player;
        }
    }

    Sokoban( List<Pair> walls, List<Pair> boxes, List<Pair> objectives, Pair player, int x, int y ) {
        this.x = x;
        this.y = y;
        this.gameMap = new Cells[x][y];
        this.player = player;
        this.direction = null;
        this.movingBox = false;
        this.boxesPositions = boxes;
        this.wallsPositions = walls;
        this.objectivesPositions = objectives;
        int steppedObjectives = 0;

        for (Pair wall : walls) {
            this.gameMap[wall.getX()][wall.getY()] = Cells.Wall;
        }
        for( Pair box : boxes ) {
            this.gameMap[box.getX()][box.getY()] = Cells.Box;
        }
        for( Pair objective : objectives ) {
            if( isBox(this.gameMap[objective.getX()][objective.getY()]) ){
                this.gameMap[objective.getX()][objective.getY()] = Cells.BoxOnObjective;
                steppedObjectives++;
            } else {
                this.gameMap[objective.getX()][objective.getY()] = Cells.Objective;
            }
        }
        this.steppedObjectives = steppedObjectives;

        if( isObjective(gameMap[player.getX()][player.getY()]) ) {
            this.gameMap[player.getX()][player.getY()] = Cells.PlayerOnObjective;
        } else {
            this.gameMap[player.getX()][player.getY()] = Cells.Player;
        }
    }

    public Snapshot snapshot() {
        Character[][] game = new Character[gameMap.length][];
        for( int i = 0; i < gameMap.length; i++ ) {
            game[i] = new Character[gameMap[i].length];
            for( int j = 0; j < gameMap[i].length; j++ ) {
                if( gameMap[i][j] != null ) {
                    switch (gameMap[i][j]) {
                        case Wall:
                            game[i][j] = '#';
                            break;
                        case Box:
                            game[i][j] = '$';
                            break;
                        case BoxOnObjective:
                            game[i][j] = 'o';
                            break;
                        case Objective:
                            game[i][j] = '.';
                            break;
                        case Player:
                            game[i][j] = '@';
                            break;
                        case PlayerOnObjective:
                            game[i][j] = '+';
                            break;
                    }
                }
            }
        }
        return new Snapshot(game, direction, movingBox);
    }

    private boolean isBoxAndObjective( Cells cell ) {
        return cell == Cells.Box || cell == Cells.BoxOnObjective;
    }

    private boolean isBox( Cells cell ) {
        return cell == Cells.Box;
    }

    private boolean isPath( Cells cell ) {
        return cell == null;
    }
    
    private boolean isObjective( Cells cell ) {
        return cell == Cells.Objective;
    }
    
    private boolean isPlayerOnObjective( Cells cell ) {
        return cell == Cells.PlayerOnObjective;
    }
    
    public Directions getDirection() {
        return direction;
    }

    public boolean canMoveLeft() {
        return isPath(gameMap[player.getX()][player.getY() - 1]) 
                || (isBoxAndObjective(gameMap[player.getX()][player.getY() - 1]) && (isPath(gameMap[player.getX()][player.getY() - 2]) || isObjective(gameMap[player.getX()][player.getY() - 2])));
    }
    
    public boolean canMoveRight() {
        return isPath(gameMap[player.getX()][player.getY() + 1])
                || (isBoxAndObjective(gameMap[player.getX()][player.getY() + 1]) && (isPath(gameMap[player.getX()][player.getY() + 2]) || isObjective(gameMap[player.getX()][player.getY() + 2])));
    }

    public boolean canMoveUp() {
        return isPath(gameMap[player.getX() - 1][player.getY()])
                || (isBoxAndObjective(gameMap[player.getX() - 1][player.getY()]) && (isPath(gameMap[player.getX() - 2][player.getY()]) || isObjective(gameMap[player.getX() - 2][player.getY()])));
    }

    public boolean canMoveDown() {
        return isPath(gameMap[player.getX() + 1][player.getY()])
                || (isBoxAndObjective(gameMap[player.getX() + 1][player.getY()]) && (isPath(gameMap[player.getX() + 2][player.getY()]) || isObjective(gameMap[player.getX() + 2][player.getY()])));
    }

    public Snapshot moveLeft() {
        if( canMoveLeft() ) {
            Pair playerAux = new Pair(player.getX(), player.getY()-1);
            if( isBoxAndObjective(gameMap[player.getX()][player.getY() - 1]) ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX(), player.getY()-1));
                boxesAux.add(new Pair(player.getX(), player.getY()-2));
                return new Sokoban(wallsPositions, boxesAux, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Left, true).snapshot();
            }
            return new Sokoban(wallsPositions, boxesPositions, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Left, false).snapshot();
        }
        return this.snapshot();
    }

    public Snapshot moveRight() {
        if( canMoveRight() ) {
            Pair playerAux = new Pair(player.getX(), player.getY()+1);
            if( isBoxAndObjective(gameMap[player.getX()][player.getY() + 1]) ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX(), player.getY()+1));
                boxesAux.add(new Pair(player.getX(), player.getY()+2));
                return new Sokoban(wallsPositions, boxesAux, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Right, true).snapshot();
            }
            return new Sokoban(wallsPositions, boxesPositions, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Right, false).snapshot();
        }
        return this.snapshot();
    }


    public Snapshot moveUp() {
        if( this.canMoveUp() ) {
            Pair playerAux = new Pair(player.getX()-1, player.getY());
            if( isBoxAndObjective(gameMap[player.getX() - 1][player.getY()]) ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX()-1, player.getY()));
                boxesAux.add(new Pair(player.getX()-2, player.getY()));
                return new Sokoban(wallsPositions, boxesAux, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Up, true).snapshot();
            }
            return new Sokoban(wallsPositions, boxesPositions, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Up, false).snapshot();
        }
        return this.snapshot();
    }

    public Snapshot moveDown() {
        if( this.canMoveDown() ) {
            Pair playerAux = new Pair(player.getX()+1, player.getY());
            if( isBoxAndObjective(gameMap[player.getX() + 1][player.getY()]) ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX()+1, player.getY()));
                boxesAux.add(new Pair(player.getX()+2, player.getY()));
                return new Sokoban(wallsPositions, boxesAux, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Down, true).snapshot();
            }
            return new Sokoban(wallsPositions, boxesPositions, objectivesPositions, playerAux, gameMap.length, maxYLength(), Directions.Down, false).snapshot();
        }
        return this.snapshot();
    }

    public List<Snapshot> getPossibleMoves() {
        List<Snapshot> moves = new LinkedList<>();
        if( canMoveLeft() ) {
            moves.add(moveLeft());
        }
        if( canMoveRight()) {
            moves.add(moveRight());
        }
        if( canMoveUp() ) {
            moves.add(moveUp());
        }
        if( canMoveDown() ) {
            moves.add(moveDown());
        }

        return moves;
    }
    
    public Pair getPlayer() {
        return player;
    }

    public boolean isOver() {
        return boxesPositions.size() == steppedObjectives;
    }

    public void show() {
        for (Cells[] cells : gameMap) {
            for (Cells cell : cells) {
                System.out.print(' ');
                if (cell == null) {
                    System.out.print(' ');
                } else {
                    switch (cell) {
                        case Player:
                            System.out.print('@');
                            break;
                        case Box:
                            System.out.print('$');
                            break;
                        case Objective:
                            System.out.print('.');
                            break;
                        case BoxOnObjective:
                            System.out.print('o');
                            break;
                        case PlayerOnObjective:
                            System.out.println('+');
                            break;
                        default:
                            System.out.print('#');
                            break;
                    }
                }
            }
            System.out.println();
        }
        ;
    }

    public List<Pair> getBoxesPositions() {
        return boxesPositions;
    }

    public boolean isMovingBox() {
        return movingBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sokoban sokoban = (Sokoban) o;
        return  steppedObjectives == sokoban.steppedObjectives &&
                movingBox == sokoban.movingBox &&
                Objects.equals(player, sokoban.player) &&
                Objects.equals(boxesPositions, sokoban.boxesPositions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y, player, direction, boxesPositions, steppedObjectives, movingBox);
        result = 31 * result + Arrays.hashCode(gameMap);
        return result;
    }

    private int maxYLength() {
        int max = gameMap[0].length;
        for (Cells[] cells : gameMap) {
            if (cells.length > max) {
                max = cells.length;
            }
        }
        return max;
    }
}
