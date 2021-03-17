package Sokoban;
public class Boards {
    private Character[][] boardTest = new Character[][]{
                {'#', '#', '#', '#', '#'},
                {'#', '.', null, '.', '#'},
                {'#', null, null, null, '#'},
                {'#', '$', null, '$', '#'},
                {'#', null, '@', null, '#'},
                {'#', '#', '#', '#', '#'},};
    //DimX = 5, DimY = 6, Boxes = 2

    private Character[][] board1 = new Character[][]{
                {null, null, null, null, null, null, '#', '#', '#'},
                {null, null, null, null, null, null, '#', '.', '#'},
                {null, null, '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#'},
                {null, '#', '#', null, null, null, null, null, null, null, null, null, '#', '#'},
                {'#', '#', null, null, '#', null, '#', null, '#', null, '#', null, null, '#', '#'},
                {'#', null, null, '#', '#', null, null, null, null, null, '#', '#', null, null, '#'},
                {'#', null, '#', '#', null, null, '#', null, '#', null, null, '#', '#', null, '#'},
                {'#', null, null, null, null, null, '$', '@', '$', null, null, null, null, null, '#'},
                {'#', '#', '#', '#', null, null, '#', '#', '#', null, null, '#', '#', '#', '#'},
                {null, null, null, '#', '#', '#', '#', null, '#', '#', '#', '#'}};
    //DimX = 10, DimY = 15, Boxes = 2

    private Character[][] board2 = new Character[][]{
            {null,null,null,null,null,'#','#','#','#','#'},
            {null,null,null,null,null,'#',null,null,null,'#'},
            {null,null,null,null,null,'#',null,null,null,'#'},
            {'#','#','#','#',null,'#',null,null,null,'#'},
            {'#',null,null,'#','#','#','#','$','#','#','#','#','#'},
            {'#',null,null,null,null,null,null,null,null,null,null,null,'#'},
            {'#',null,'.','#','#',null,'#',null,'#','#','.',null,'#'},
            {'#',null,null,null,null,null,null,null,null,null,null,null,'#'},
            {'#','#','#','#','#','$','#','#','#','#',null,null,'#'},
            {null,null,null,'#',null,null,null,'#',null,'#','#','#','#'},
            {null,null,null,'#',null,'@',null,'#',null,null,null,null,null},
            {null,null,null,'#',null,null,null,'#',null,null,null,null,null},
            {null,null,null,'#','#','#','#','#',null,null,null,null,null}
    };
    //DimX = 13, DimY = 13, Boxes = 2

    private Character[][] board3 = new Character[][]{
            {null,'#','#','#','#','#'},
            {'#','#',null,null,null,'#'},
            {'#',null,null,'.',null,'#'},
            {'#',null,'$','.','$','#','#','#'},
            {'#','#','$','.','$',null,null,'#'},
            {null,'#',null,'.','@',null,null,'#'},
            {null,'#','#','#','#','#','#','#'}
    };
    //DimX = 7, DimY = 8, Boxes = 4

    public Character[][] getBoard3() {
        return board3;
    }

    public void setBoard3(Character[][] board3) {
        this.board3 = board3;
    }

    public Character[][] getBoardTest() {
        return boardTest;
    }

    public void setBoardTest(Character[][] boardTest) {
        this.boardTest = boardTest;
    }

    public Character[][] getBoard1() {
        return board1;
    }

    public void setBoard1(Character[][] board1) {
        this.board1 = board1;
    }

    public Character[][] getBoard2() {
        return board2;
    }

    public void setBoard2(Character[][] board2) {
        this.board2 = board2;
    }
}