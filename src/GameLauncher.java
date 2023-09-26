import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameLauncher extends JFrame {
    private GamePanel gamePanel;

    public GameLauncher() {

        setTitle("Game Launcher");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Choose a game to play:");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);


        JButton simonButton = new JButton("Simon");
        JButton cardButton = new JButton("CardFlipper");
        JButton pacButton = new JButton("PacMan");
        JButton hockeyButton = new JButton("Hockey");
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(simonButton);
        buttonPanel.add(cardButton);
        buttonPanel.add(pacButton);
        buttonPanel.add(hockeyButton);
        add(buttonPanel, BorderLayout.CENTER);


        simonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Simon();
            }
        });
        cardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MemoryGame(); //yeha new main method vako class name
            }
        });
       pacButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pacman pacman = new Pacman();
                pacman.setVisible(true);
            }
        });

        hockeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                getContentPane().removeAll();
                gamePanel = new GamePanel();

                getContentPane().add(gamePanel);

                revalidate();
                repaint();

                gamePanel.gameStarted = true;
                gamePanel.gameOver = false;
                gamePanel.gameThread = new Thread(gamePanel);
                gamePanel.gameThread.start();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GameLauncher();
    }
}

