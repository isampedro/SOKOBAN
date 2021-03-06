import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    public static void main(String[] args) {
        Sokoban game = new Sokoban();
        game.show();
        JFrame frame = new JFrame("Key Listener");
        Container contentPane = frame.getContentPane();
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (Character.toLowerCase(e.getKeyChar())) {
                    case 'a':
                        game.moveLeft();
                        break;
                    case 'w':
                        game.moveUp();
                        break;
                    case 'd':
                        game.moveRight();
                        break;
                    case 's':
                        game.moveDown();
                        break;
                }
                game.show();
                if(game.isOver()) {
                    System.out.println("You Won!");
                    System.exit(0);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        JTextField textField = new JTextField();
        textField.addKeyListener(listener);
        contentPane.add(textField, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
}
