package card;


import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardGameActionLIstener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame cardFrame = new JFrame("CardGame");
        cardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        cardFrame.setExtendedState(cardFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
//        cardFrame.setResizable(false);

        cardFrame.setSize(1024, 768);

        cardFrame.setLocationRelativeTo(null);
        cardFrame.add(new CardGame());
        cardFrame.setVisible(true);
    }
}