public class Boards {
    public static void boards() {
        Character[][] boardTest, board1, board2;
        boardTest = new Character[][]{
                {'#', '#', '#', '#', '#'},
                {'#', '.', null, '.', '#'},
                {'#', null, null, null, '#'},
                {'#', '$', null, '$', '#'},
                {'#', null, '@', null, '#'},
                {'#', '#', '#', '#', '#'},};
        board1 = new Character[][]{
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
        board2 = new Character[][]{
                {null, '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', null},
                {'#', '#', null, null, null, null, null, null, null, null, null, '#', '#'},
                {'#', null, null, '$', null, null, null, null, null, '$', null, null, '#'},
                {'#', null, '$', '#', null, '#', '.', '#', null, '#', '$', null, '#'},
                {'#', null, null, null, null, '#', 'o', '#', null, null, null, null, '#', '#', '#', '#', '#'},
                {'#', null, null, '#', '#', '#', '.', '#', '#', '#', null, null, '#', null, null, null, '#'},
                {'#', null, null, '.', '*', '.', '@', '.', '*', '.', null, null, null, null, null, null, '#'},
                {'#', null, null, '#', '#', '#', '.', '#', '#', '#', null, null, '#', null, null, null, '#'},
                {'#', null, null, null, null, '#', '*', '#', null, null, null, null, '#', '#', '#', '#', '#'},
                {'#', null, '$', '#', null, '#', '.', '#', null, '#', '$', null, '#'},
                {'#', null, null, '$', null, null, null, null, null, '$', null, null, '#'},
                {'#', '#', null, null, null, null, null, null, null, null, null, '#', '#'},
                {null, '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', null}};
    }
}