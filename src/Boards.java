public class Boards {
    public static void boards() {
        Character[][] board;
        Character[][] boardTest = new Character[][]{
                {'#','#','#','#','#'},
                {'#','.',null,'.','#'},
                {'#',null,null,null,'#'},
                {'#','$',null,'$','#'},
                {'#',null,'@',null,'#'},
                {'#','#','#','#','#'},};
        board = new Character[][]{
                {null,null,null,null,null,null,'#','#','#'},
                {null,null,null,null,null,null,'#','.','#'},
                {null,null,'#','#','#','#','#','.','#','#','#','#','#'},
                {null,'#','#',null,null,null,null,null,null,null,null,null,'#','#'},
                {'#','#',null,null,'#',null,'#',null,'#',null,'#',null,null,'#','#'},
                {'#',null,null,'#','#',null,null,null,null,null,'#','#',null,null,'#'},
                {'#',null,'#','#',null,null,'#',null,'#',null,null,'#','#',null,'#'},
                {'#',null,null,null,null,null,'$','@','$',null,null,null,null,null,'#'},
                {'#','#','#','#',null,null,'#','#','#',null,null,'#','#','#','#'},
                {null,null,null,'#','#','#','#',null,'#','#','#','#'}};

    }
}
