package main;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {

    public static void main(String args[]) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pixel Jump");

        GamePanel gamePanel = null;
        try {
            gamePanel = new GamePanel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
