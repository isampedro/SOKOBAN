public class Sokoban {
    final private Cells[][] gameMap;
    final private int x,y;
    private int playerX, playerY;

    Sokoban() {
        x = 15;
        y = 15;
        gameMap = new Cells[x][y];
        playerX = x/2;
        playerY = y/2;

        gameMap[playerX][playerY] = Cells.Player;

        gameMap[playerX+1][playerY+1] = Cells.Box; gameMap[playerX-1][playerY+1] = Cells.Box;
        gameMap[playerX+1][playerY-1] = Cells.Box; gameMap[playerX-1][playerY-1] = Cells.Box;

        for( int j = 0; j < y; j++) {
            gameMap[0][j] = Cells.Wall; gameMap[x-1][j] = Cells.Wall;
        }
        for( int i = 0; i < x; i++) {
            gameMap[i][0] = Cells.Wall; gameMap[i][y-1] = Cells.Wall;
        }

    }

    public boolean canMoveLeft() {
        return gameMap[playerX][playerY - 1] == null || (gameMap[playerX][playerY - 1] == Cells.Box && gameMap[playerX][playerY - 2] == null );
    }

    public boolean canMoveRight() {
        return gameMap[playerX][playerY + 1] == null || (gameMap[playerX][playerY + 1] == Cells.Box && gameMap[playerX][playerY + 2] == null );
    }

    public boolean canMoveUp() {
        return gameMap[playerX - 1][playerY] == null || (gameMap[playerX - 1][playerY] == Cells.Box && gameMap[playerX - 2][playerY] == null );
    }

    public boolean canMoveDown() {
        return gameMap[playerX + 1][playerY] == null || (gameMap[playerX + 1][playerY] == Cells.Box && gameMap[playerX + 2][playerY] == null );
    }

    public void moveLeft() {
        if( this.canMoveLeft() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX][playerY - 1] == Cells.Box ){
                gameMap[playerX][playerY - 2] = Cells.Box;
            }
            gameMap[playerX][playerY - 1] = Cells.Player;
            this.updatePlayer(0,-1);
        }
    }

    public void moveRight() {
        if( this.canMoveRight() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX][playerY + 1] == Cells.Box ){
                gameMap[playerX][playerY + 2] = Cells.Box;
            }
            gameMap[playerX][playerY + 1] = Cells.Player;
            this.updatePlayer(0,1);
        }
    }

    public void moveUp() {
        if( this.canMoveUp() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX - 1][playerY] == Cells.Box ){
                gameMap[playerX - 2][playerY] = Cells.Box;
            }
            gameMap[playerX - 1][playerY] = Cells.Player;
            this.updatePlayer(-1,0);
        }
    }

    public void moveDown() {
        if( this.canMoveDown() ) {
            gameMap[playerX][playerY] = null;
            if( gameMap[playerX + 1][playerY] == Cells.Box ){
                gameMap[playerX + 2][playerY] = Cells.Box;
            }
            gameMap[playerX + 1][playerY] = Cells.Player;
            this.updatePlayer(1, 0);
        }
    }

    private void updatePlayer(int xDiff, int yDiff ) {
        this.playerX += xDiff;
        this.playerY += yDiff;
    }

    public void show() {
        for(int i = 0; i < x; i++) {
            for( int j = 0; j < y; j++) {
                if( gameMap[i][j] == null ) {
                    System.out.print(' ');
                } else if( gameMap[i][j] == Cells.Player) {
                    System.out.print('p');
                } else if( gameMap[i][j] == Cells.Box ) {
                    System.out.print('b');
                } else {
                    System.out.print('*');
                }
            }
            System.out.println();
        };
    }
}
