package mvc.controller;
import mvc.view.GamePanel;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //pack() causes this window to be sized to fit the preferred size and layouts of its subcomponents (=GamePanel)

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
