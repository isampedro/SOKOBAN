public class Sokoban {
    private Cells[][] gameMap;
    private int x, y, playerX, playerY;

    Sokoban(int x,int y, int playerX, int playerY) {
        gameMap = new Cells[x][y];
        this.x = x;
        this.y = y;
        this.playerX = playerX;
        this.playerY = playerY;

        gameMap[playerX][playerY] = Cells.Player;

        for( int j = 0; j < y; j++) {
            gameMap[0][j] = Cells.Wall;
            gameMap[x-1][j] = Cells.Wall;
        }
        for( int i = 0; i < x; i++) {
            gameMap[i][0] = Cells.Wall;
            gameMap[i][y-1] = Cells.Wall;
        }

    }

    public boolean canMoveLeft() {
        return gameMap[playerX][playerY - 1] != Cells.Wall;
    }

    public boolean canMoveRight() {
        return gameMap[playerX][playerY + 1] != Cells.Wall;
    }

    public boolean canMoveUp() {
        return gameMap[playerX - 1][playerY] != Cells.Wall;
    }

    public boolean canMoveDown() {
        return gameMap[playerX + 1][playerY] != Cells.Wall;
    }

    public void moveLeft() {
        if( this.canMoveLeft() ) {
            gameMap[playerX][playerY] = null;
            gameMap[playerX][playerY - 1] = Cells.Player;
            this.updatePlayer(0,-1);
        }
    }

    public void moveRight() {
        if( this.canMoveRight() ) {
            gameMap[playerX][playerY] = null;
            gameMap[playerX][playerY + 1] = Cells.Player;
            this.updatePlayer(0,1);
        }
    }

    public void moveUp() {
        if( this.canMoveUp() ) {
            gameMap[playerX][playerY] = null;
            gameMap[playerX - 1][playerY] = Cells.Player;
            this.updatePlayer(-1,0);
        }
    }

    public void moveDown() {
        if( this.canMoveDown() ) {
            gameMap[playerX][playerY] = null;
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
                } else {
                    System.out.print('*');
                }
            }
            System.out.println();
        };
    }
}
