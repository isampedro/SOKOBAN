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
    private int steppedObjectives = 0;
    private  boolean movingBox;

    Sokoban( Snapshot game ) {
        this.direction = game.getDirection();
        this.movingBox = game.isMovingBox();
        boxesPositions = new LinkedList<>();
        wallsPositions = new LinkedList<>();
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
                    }
                }
            }
        }
    }

    Sokoban(Character[][] game ) {
        boxesPositions = new LinkedList<>();
        wallsPositions = new LinkedList<>();
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
                    }
                }
            }
        }
    }

    private Sokoban( List<Pair> walls, List<Pair> boxes, List<Pair> objectives, Pair player, int x, int y, Directions direction, boolean movingBox ) {
        this.gameMap = new Cells[x][y];
        this.player = player;
        this.boxesPositions = boxes;
        this.wallsPositions = walls;
        this.objectivesPositions = objectives;
        this.direction = direction;
        this.movingBox = movingBox;

        for (Pair wall : walls) {
            this.gameMap[wall.getX()][wall.getY()] = Cells.Wall;
        }
        for( Pair box : boxes ) {
            this.gameMap[box.getX()][box.getY()] = Cells.Box;
        }
        for( Pair objective : objectives ) {
            for( Pair box : boxes ) {
                if( box.getX().equals(objective.getX()) && box.getY().equals(objective.getY()) ) {
                    this.gameMap[objective.getX()][objective.getY()] = Cells.SteppedObjective;
                    this.steppedObjectives++;
                } else {
                    this.gameMap[objective.getX()][objective.getY()] = Cells.Objective;
                }
            }
        }

        this.gameMap[player.getX()][player.getY()] = Cells.Player;
    }

    Sokoban( List<Pair> walls, List<Pair> boxes, List<Pair> objectives, Pair player, int x, int y ) {
        this.x = x;
        this.y = y;
        this.gameMap = new Cells[x][y];
        this.player = player;
        this.direction = null;
        this.movingBox = false;
        this.gameMap[player.getX()][player.getY()] = Cells.Player;
        this.boxesPositions = boxes;
        this.wallsPositions = walls;
        this.objectivesPositions = objectives;

        for (Pair wall : walls) {
            this.gameMap[wall.getX()][wall.getY()] = Cells.Wall;
        }
        for( Pair box : boxes ) {
            this.gameMap[box.getX()][box.getY()] = Cells.Box;
        }
        for( Pair objective : objectives ) {
            this.gameMap[objective.getX()][objective.getY()] = Cells.Objective;
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
                        case Objective:
                            game[i][j] = '.';
                            break;
                        case Player:
                            game[i][j] = '@';
                            break;
                    }
                }
            }
        }
        Snapshot snapshot = new Snapshot(game, direction, movingBox);
        return snapshot;
    }

    public boolean canMoveLeft() {
        return gameMap[player.getX()][player.getY() - 1] == null
                || ((gameMap[player.getX()][player.getY() - 1] == Cells.Box || gameMap[player.getX()][player.getY() - 1] == Cells.SteppedObjective) && (gameMap[player.getX()][player.getY() - 2] == null || gameMap[player.getX()][player.getY() - 2] == Cells.Objective || gameMap[player.getX()][player.getY() - 2] == Cells.SteppedObjective));
    }

    public Directions getDirection() {
        return direction;
    }

    public boolean canMoveRight() {
        return gameMap[player.getX()][player.getY() + 1] == null
                || ((gameMap[player.getX()][player.getY() + 1] == Cells.Box || gameMap[player.getX()][player.getY() + 1] == Cells.SteppedObjective) && (gameMap[player.getX()][player.getY() + 2] == null || gameMap[player.getX()][player.getY() + 2] == Cells.Objective || gameMap[player.getX()][player.getY() + 2] == Cells.SteppedObjective));
    }

    public boolean canMoveUp() {
        return gameMap[player.getX() - 1][player.getY()] == null
                || ((gameMap[player.getX() - 1][player.getY()] == Cells.Box || gameMap[player.getX() - 1][player.getY()] == Cells.SteppedObjective ) && (gameMap[player.getX() - 2][player.getY()] == null || gameMap[player.getX() - 2][player.getY()] == Cells.Objective || gameMap[player.getX() - 2][player.getY()] == Cells.SteppedObjective));
    }

    public boolean canMoveDown() {
        return gameMap[player.getX() + 1][player.getY()] == null
                || ((gameMap[player.getX() + 1][player.getY()] == Cells.Box || gameMap[player.getX() + 1][player.getY()] == Cells.SteppedObjective) && (gameMap[player.getX() + 2][player.getY()] == null || gameMap[player.getX() + 2][player.getY()] == Cells.Objective || gameMap[player.getX() + 2][player.getY()] == Cells.SteppedObjective));
    }

    public Snapshot moveLeft() {
        if( this.canMoveLeft() ) {
            Pair playerAux = new Pair(player.getX(), player.getY()-1);
            if( gameMap[player.getX()][player.getY() - 1] == Cells.Box || gameMap[player.getX()][player.getY() - 1] == Cells.SteppedObjective ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX(), player.getY()-1));
                boxesAux.add(new Pair(player.getX(), player.getY()-2));
                return this.snapshot();
            }
            return this.snapshot();
        }
        return this.snapshot();
    }

    public Snapshot moveRight() {
        if( this.canMoveRight() ) {
            Pair playerAux = new Pair(player.getX(), player.getY()+1);
            if( gameMap[player.getX()][player.getY() + 1] == Cells.Box || gameMap[player.getX()][player.getY() + 1] == Cells.SteppedObjective ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX(), player.getY()+1));
                boxesAux.add(new Pair(player.getX(), player.getY()+2));
                return this.snapshot();
            }
            return this.snapshot();
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

    public Snapshot moveUp() {
        if( this.canMoveUp() ) {
            Pair playerAux = new Pair(player.getX()-1, player.getY());
            if( gameMap[player.getX() - 1][player.getY()] == Cells.Box || gameMap[player.getX() - 1][player.getY()] == Cells.SteppedObjective ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX()-1, player.getY()));
                boxesAux.add(new Pair(player.getX()-2, player.getY()));
                return this.snapshot();
            }
            return this.snapshot();
        }
        return this.snapshot();
    }

    public Snapshot moveDown() {
        if( this.canMoveDown() ) {
            Pair playerAux = new Pair(player.getX()+1, player.getY());
            if( gameMap[player.getX() + 1][player.getY()] == Cells.Box || gameMap[player.getX() + 1][player.getY()] == Cells.SteppedObjective ){
                List<Pair> boxesAux = new LinkedList<>(boxesPositions);
                boxesAux.remove(new Pair(player.getX()+1, player.getY()));
                boxesAux.add(new Pair(player.getX()+2, player.getY()));
                return this.snapshot();
            }
            return this.snapshot();
        }
        return this.snapshot();
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
                        case SteppedObjective:
                            System.out.print('o');
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

//    private Sokoban(List<Pair> boxes, Pair player, Directions direction, boolean movedBox) {
//        gameMap = new Cells[x][y];
//        this.player = player;
//        System.out.println(player);
//        gameMap[player.getX()][player.getY()] = Cells.Player;
//        steppedObjectives = 0;
//        this.direction = direction;
//
//        this.boxesPositions = boxes;
//        boxes.forEach((box) -> {
//            gameMap[box.getX()][box.getY()] = Cells.Box;
//        });
//
//        if(boxesPositions.contains(new Pair(1, 1))) {
//            gameMap[1][1] = Cells.SteppedObjective;
//            steppedObjectives++;
//        } else {
//            gameMap[1][1] = Cells.Objective;
//        }
//        if(boxesPositions.contains(new Pair(1, y-2))) {
//            gameMap[1][y-2] = Cells.SteppedObjective;
//            steppedObjectives++;
//        } else {
//            gameMap[1][y-2] = Cells.Objective;
//        }
////        if(boxesPositions.contains(new Pair(x-2, 1))) {
////            gameMap[x-2][1] = Cells.SteppedObjective;
////            steppedObjectives++;
////        } else {
////            gameMap[x-2][1] = Cells.Objective;
////        }
////        if(boxesPositions.contains(new Pair(x-2, y-2))) {
////            gameMap[x-2][y-2] = Cells.SteppedObjective;
////            steppedObjectives++;
////        } else {
////            gameMap[x-2][y-2] = Cells.Objective;
////        }
//
//        movingBox = movedBox;
//
//        for( int j = 0; j < y; j++) {
//            gameMap[0][j] = Cells.Wall; gameMap[x-1][j] = Cells.Wall;
//        }
//        for( int i = 0; i < x; i++) {
//            gameMap[i][0] = Cells.Wall; gameMap[i][y-1] = Cells.Wall;
//        }
//
//    }
//Sokoban() {
//    gameMap = new Cells[x][y];
//    player = new Pair(x/2, y/2);
//    direction = null;
//    movingBox = false;
//
//    gameMap[player.getX()][player.getY()] = Cells.Player;
//
//    boxesPositions = new LinkedList<>();
//    wallsPositions = new LinkedList<>();
//    objectivesPositions = new LinkedList<>();
////        boxesPositions.add( new Pair(player.getX()+1, player.getY()+1));
//    boxesPositions.add( new Pair(player.getX()-1, player.getY()+1));
//    boxesPositions.add( new Pair(player.getX()-1, player.getY()-1));
////        boxesPositions.add( new Pair(player.getX()+1, player.getY()-1));
////        gameMap[player.getX()+1][player.getY()+1] = Cells.Box;
//    gameMap[player.getX()-1][player.getY()+1] = Cells.Box;
////        gameMap[player.getX()+1][player.getY()-1] = Cells.Box;
//    gameMap[player.getX()-1][player.getY()-1] = Cells.Box;
//
//    gameMap[1][1] = Cells.Objective;
//    gameMap[1][y-2] = Cells.Objective;
////        gameMap[x-2][1] = Cells.Objective;
////        gameMap[x-2][y-2] = Cells.Objective;
//
//    steppedObjectives = 0;
//
//    for( int j = 0; j < y; j++) {
//        gameMap[0][j] = Cells.Wall; gameMap[x-1][j] = Cells.Wall;
//    }
//    for( int i = 0; i < x; i++) {
//        gameMap[i][0] = Cells.Wall; gameMap[i][y-1] = Cells.Wall;
//    }
//
//}
}
