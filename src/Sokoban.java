public class Sokoban {
    final private Cells[][] gameMap;
    final private int x,y;
    private int playerX, playerY;
    private int steppedObjectives;
    private int objectives;

    Sokoban() {
        x = 15;
        y = 15;
        gameMap = new Cells[x][y];
        playerX = x/2;
        playerY = y/2;

        gameMap[playerX][playerY] = Cells.Player;

        gameMap[playerX+1][playerY+1] = Cells.Box;
        gameMap[playerX-1][playerY+1] = Cells.Box;
        gameMap[playerX+1][playerY-1] = Cells.Box;
        gameMap[playerX-1][playerY-1] = Cells.Box;

        gameMap[1][1] = Cells.Objective;
        gameMap[1][y-2] = Cells.Objective;
        gameMap[x-2][1] = Cells.Objective;
        gameMap[x-2][y-2] = Cells.Objective;

        objectives = 4;
        steppedObjectives = 0;

        for( int j = 0; j < y; j++) {
            gameMap[0][j] = Cells.Wall; gameMap[x-1][j] = Cells.Wall;
        }
        for( int i = 0; i < x; i++) {
            gameMap[i][0] = Cells.Wall; gameMap[i][y-1] = Cells.Wall;
        }

    }

    public boolean canMoveLeft() {
        return gameMap[playerX][playerY - 1] == null
                || (gameMap[playerX][playerY - 1] == Cells.Box && (gameMap[playerX][playerY - 2] == null || gameMap[playerX][playerY - 2] == Cells.Objective));
    }

    public boolean canMoveRight() {
        return gameMap[playerX][playerY + 1] == null
                || (gameMap[playerX][playerY + 1] == Cells.Box && (gameMap[playerX][playerY + 2] == null || gameMap[playerX][playerY + 2] == Cells.Objective));
    }

    public boolean canMoveUp() {
        return gameMap[playerX - 1][playerY] == null
                || (gameMap[playerX - 1][playerY] == Cells.Box && (gameMap[playerX - 2][playerY] == null || gameMap[playerX - 2][playerY] == Cells.Objective));
    }

    public boolean canMoveDown() {
        return gameMap[playerX + 1][playerY] == null
                || (gameMap[playerX + 1][playerY] == Cells.Box && (gameMap[playerX + 2][playerY] == null || gameMap[playerX + 2][playerY] == Cells.Objective));
    }

    public void moveLeft() {
        if( this.canMoveLeft() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX][playerY - 1] == Cells.Box ){
                if( gameMap[playerX][playerY - 2] == Cells.Objective ) {
                    gameMap[playerX][playerY - 2] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[playerX][playerY - 2] = Cells.Box;
                }
            }
            gameMap[playerX][playerY - 1] = Cells.Player;
            this.updatePlayer(0,-1);
        }
    }

    public void moveRight() {
        if( this.canMoveRight() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX][playerY + 1] == Cells.Box ){
                if( gameMap[playerX][playerY + 2] == Cells.Objective ) {
                    gameMap[playerX][playerY + 2] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[playerX][playerY + 2] = Cells.Box;
                }
            }
            gameMap[playerX][playerY + 1] = Cells.Player;
            this.updatePlayer(0,1);
        }
    }

    public void moveUp() {
        if( this.canMoveUp() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX - 1][playerY] == Cells.Box ){
                if( gameMap[playerX - 2][playerY] == Cells.Objective ) {
                    gameMap[playerX - 2][playerY] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[playerX - 2][playerY] = Cells.Box;
                }
            }
            gameMap[playerX - 1][playerY] = Cells.Player;
            this.updatePlayer(-1,0);
        }
    }

    public void moveDown() {
        if( this.canMoveDown() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX + 1][playerY] == Cells.Box ){
                if( gameMap[playerX + 2][playerY] == Cells.Objective ) {
                    gameMap[playerX + 2][playerY] = Cells.SteppedObjective;
                    steppedObjectives++;
                } else {
                    gameMap[playerX + 2][playerY] = Cells.Box;
                }
            }
            gameMap[playerX + 1][playerY] = Cells.Player;
            this.updatePlayer(1, 0);
        }
    }

    private void updatePlayer(int xDiff, int yDiff ) {
        this.playerX += xDiff;
        this.playerY += yDiff;
    }

    public boolean isOver() {
        return objectives == steppedObjectives;
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
