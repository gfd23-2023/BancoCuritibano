package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String Args[]) {
        JFrame window = new JFrame();   
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha no X
        window.setResizable(false);
        window.setTitle("Monopoly");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // Ajusta o JFrame ao tamanho do JPanel

        window.setLocationRelativeTo(null); //window placed on the center of the screen
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
