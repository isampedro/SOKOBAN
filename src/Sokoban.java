import java.util.LinkedList;
import java.util.List;

public class Sokoban {
    private final Cells[][] gameMap;
    private final int x, y;
    private final Pair player;
    private final List<Pair> boxesPositions;
    private int steppedObjectives;
    
    private Sokoban(List<Pair> boxes, Pair player) {
        x = 7;
        y = 7;
        gameMap = new Cells[x][y];
        this.player = player;
        gameMap[player.getX()][player.getY()] = Cells.Player;

        this.boxesPositions = boxes;
        boxes.forEach((box) -> {
            gameMap[box.getX()][box.getY()] = Cells.Box;
        });

        if(boxesPositions.contains(new Pair(1, 1))) {
            gameMap[1][1] = Cells.SteppedObjective;
        } else {
            gameMap[1][1] = Cells.Objective;
        }
        if(boxesPositions.contains(new Pair(1, y-2))) {
            gameMap[1][y-2] = Cells.SteppedObjective;
        } else {
            gameMap[1][y-2] = Cells.Objective;
        }
        if(boxesPositions.contains(new Pair(x-2, 1))) {
            gameMap[x-2][1] = Cells.SteppedObjective;
        } else {
            gameMap[x-2][1] = Cells.Objective;
        }
        if(boxesPositions.contains(new Pair(x-2, y-2))) {
            gameMap[x-2][y-2] = Cells.SteppedObjective;
        } else {
            gameMap[x-2][y-2] = Cells.Objective;
        }

        for( int j = 0; j < y; j++) {
            gameMap[0][j] = Cells.Wall; gameMap[x-1][j] = Cells.Wall;
        }
        for( int i = 0; i < x; i++) {
            gameMap[i][0] = Cells.Wall; gameMap[i][y-1] = Cells.Wall;
        }

    }
    
    Sokoban() {
        x = 7;
        y = 7;
        gameMap = new Cells[x][y];
        player = new Pair(x/2, y/2);

        gameMap[player.getX()][player.getY()] = Cells.Player;

        boxesPositions = new LinkedList<>();
        boxesPositions.add( new Pair(player.getX()+1, player.getY()+1));
        boxesPositions.add( new Pair(player.getX()-1, player.getY()+1));
        boxesPositions.add( new Pair(player.getX()-1, player.getY()-1));
        boxesPositions.add( new Pair(player.getX()+1, player.getY()-1));
        gameMap[player.getX()+1][player.getY()+1] = Cells.Box;
        gameMap[player.getX()-1][player.getY()+1] = Cells.Box;
        gameMap[player.getX()+1][player.getY()-1] = Cells.Box;
        gameMap[player.getX()-1][player.getY()-1] = Cells.Box;

        gameMap[1][1] = Cells.Objective;
        gameMap[1][y-2] = Cells.Objective;
        gameMap[x-2][1] = Cells.Objective;
        gameMap[x-2][y-2] = Cells.Objective;
        
        steppedObjectives = 0;

        for( int j = 0; j < y; j++) {
            gameMap[0][j] = Cells.Wall; gameMap[x-1][j] = Cells.Wall;
        }
        for( int i = 0; i < x; i++) {
            gameMap[i][0] = Cells.Wall; gameMap[i][y-1] = Cells.Wall;
        }

    }

    public boolean canMoveLeft() {
        return gameMap[player.getX()][player.getY() - 1] == null
                || (gameMap[player.getX()][player.getY() - 1] == Cells.Box && (gameMap[player.getX()][player.getY() - 2] == null || gameMap[player.getX()][player.getY() - 2] == Cells.Objective));
    }

    public boolean canMoveRight() {
        return gameMap[player.getX()][player.getY() + 1] == null
                || (gameMap[player.getX()][player.getY() + 1] == Cells.Box && (gameMap[player.getX()][player.getY() + 2] == null || gameMap[player.getX()][player.getY() + 2] == Cells.Objective));
    }

    public boolean canMoveUp() {
        return gameMap[player.getX() - 1][player.getY()] == null
                || (gameMap[player.getX() - 1][player.getY()] == Cells.Box && (gameMap[player.getX() - 2][player.getY()] == null || gameMap[player.getX() - 2][player.getY()] == Cells.Objective));
    }

    public boolean canMoveDown() {
        return gameMap[player.getX() + 1][player.getY()] == null
                || (gameMap[player.getX() + 1][player.getY()] == Cells.Box && (gameMap[player.getX() + 2][player.getY()] == null || gameMap[player.getX() + 2][player.getY()] == Cells.Objective));
    }

    public Sokoban moveLeft() {
        if( this.canMoveLeft() ) {
            Pair playerAux = new Pair(player.getX(), player.getY()-1);
            gameMap[player.getX()][player.getY()] = null;
            if( gameMap[player.getX()][player.getY() - 1] == Cells.Box ){
                if( gameMap[player.getX()][player.getY() - 2] == Cells.Objective ) {
                    gameMap[player.getX()][player.getY() - 2] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[player.getX()][player.getY() - 2] = Cells.Box;
                }
                List<Pair> boxesAux = boxesPositions;
                boxesAux.remove(new Pair(player.getX(), player.getY()-1));
                boxesAux.add(new Pair(player.getX(), player.getY()-2));
                return new Sokoban(boxesAux, playerAux);
            }
            gameMap[player.getX()][player.getY() - 1] = Cells.Player;
            return new Sokoban(boxesPositions, playerAux);
        }
        return this;
    }

    public Sokoban moveRight() {
        if( this.canMoveRight() ) {
            Pair playerAux = new Pair(player.getX(), player.getY()+1);
            gameMap[player.getX()][player.getY()] = null;
            if( gameMap[player.getX()][player.getY() + 1] == Cells.Box ){
                if( gameMap[player.getX()][player.getY() + 2] == Cells.Objective ) {
                    gameMap[player.getX()][player.getY() + 2] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[player.getX()][player.getY() + 2] = Cells.Box;
                }
                List<Pair> boxesAux = boxesPositions;
                boxesAux.remove(new Pair(player.getX(), player.getY()+1));
                boxesAux.add(new Pair(player.getX(), player.getY()+2));
                return new Sokoban(boxesAux, playerAux);
            }
            gameMap[player.getX()][player.getY() + 1] = Cells.Player;
            return new Sokoban(boxesPositions, playerAux);
        }

        return this;
    }

    public Sokoban moveUp() {
        if( this.canMoveUp() ) {
            Pair playerAux = new Pair(player.getX()-1, player.getY());
            gameMap[player.getX()][player.getY()] = null;
            if( gameMap[player.getX() - 1][player.getY()] == Cells.Box ){
                if( gameMap[player.getX() - 2][player.getY()] == Cells.Objective ) {
                    gameMap[player.getX() - 2][player.getY()] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[player.getX() - 2][player.getY()] = Cells.Box;
                }
                List<Pair> boxesAux = boxesPositions;
                boxesAux.remove(new Pair(player.getX()-1, player.getY()));
                boxesAux.add(new Pair(player.getX()-2, player.getY()));
                return new Sokoban(boxesAux, playerAux);
            }
            gameMap[player.getX() - 1][player.getY()] = Cells.Player;
            return new Sokoban(boxesPositions, playerAux);
        }
        return this;
    }

    public Sokoban moveDown() {
        if( this.canMoveDown() ) {
            Pair playerAux = new Pair(player.getX()+1, player.getY());
            gameMap[player.getX()][player.getY()] = null;
            if( gameMap[player.getX() + 1][player.getY()] == Cells.Box ){
                if( gameMap[player.getX() + 2][player.getY()] == Cells.Objective ) {
                    gameMap[player.getX() + 2][player.getY()] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[player.getX() + 2][player.getY()] = Cells.Box;
                }
                List<Pair> boxesAux = boxesPositions;
                boxesAux.remove(new Pair(player.getX()+1, player.getY()));
                boxesAux.add(new Pair(player.getX()+2, player.getY()));
                return new Sokoban(boxesAux, playerAux);
            }
            gameMap[player.getX() + 1][player.getY()] = Cells.Player;
            return new Sokoban(boxesPositions, playerAux);
        }
        return this;
    }

    private void updatePlayer(int xDiff, int yDiff ) {
        player.setX(player.getX() + xDiff);
        player.setY(player.getY() + yDiff);
    }

    public boolean isOver() {
        return boxesPositions.size() == steppedObjectives;
    }

    public void show() {
        for(int i = 0; i < x; i++) {
            for( int j = 0; j < y; j++) {
                if( gameMap[i][j] == null ) {
                    System.out.print(' ');
                } else {
                    switch (gameMap[i][j]) {
                        case Player:
                            System.out.print('p');
                            break;
                        case Box:
                            System.out.print('b');
                            break;
                        case Objective:
                            System.out.print('x');
                            break;
                        case SteppedObjective:
                            System.out.print('o');
                            break;
                        default:
                            System.out.print('*');
                            break;
                    }
                }
            }
            System.out.println();
        };
    }
}
