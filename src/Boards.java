public class Boards {
    private Character[][] boardTest = new Character[][]{
                {'#', '#', '#', '#', '#'},
                {'#', '.', null, '.', '#'},
                {'#', null, null, null, '#'},
                {'#', '$', null, '$', '#'},
                {'#', null, '@', null, '#'},
                {'#', '#', '#', '#', '#'},};

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

    private Character[][] board2 = new Character[][]{
                {null, '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', null},
                {'#', '#', null, null, null, null, null, null, null, null, null, '#', '#'},
                {'#', null, null, '$', null, null, null, null, null, '$', null, null, '#'},
                {'#', null, '$', '#', null, '#', '.', '#', null, '#', '$', null, '#'},
                {'#', null, null, null, null, '#', 'o', '#', null, null, null, null, '#', '#', '#', '#', '#'},
                {'#', null, null, '#', '#', '#', '.', '#', '#', '#', null, null, '#', null, null, null, '#'},
                {'#', null, null, '.', 'o', '.', '@', '.', 'o', '.', null, null, null, null, null, null, '#'},
                {'#', null, null, '#', '#', '#', '.', '#', '#', '#', null, null, '#', null, null, null, '#'},
                {'#', null, null, null, null, '#', 'o', '#', null, null, null, null, '#', '#', '#', '#', '#'},
                {'#', null, '$', '#', null, '#', '.', '#', null, '#', '$', null, '#'},
                {'#', null, null, '$', null, null, null, null, null, '$', null, null, '#'},
                {'#', '#', null, null, null, null, null, null, null, null, null, '#', '#'},
                {null, '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', null}};

    private Character[][] board3 = new Character[][]{
            {null,null,'#','#','#','#','#',null,null},
            {'#','#','#',null,null,'.','#',null,null},
            {'#','.','@','$',null,null,'#','#',null},
            {'#','#','#','#','#',null,null,'#','#'},
            {'#','.',null,'.','#',null,'$',null,'#'},
            {'#','$',null,'$','#',null,null,null,'#'},
            {'#',null,null,null,null,null,null,null,'#'},
            {'#','#','#','#','#','#','#','#','#'}
    };

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