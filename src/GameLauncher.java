import card.CardGameActionLIstener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameLauncher extends JFrame {

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
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(simonButton);
        buttonPanel.add(cardButton);
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
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameLauncher();
    }
}

