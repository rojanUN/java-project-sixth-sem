package card;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
//import res.*;

public class CardGame extends JButton implements ActionListener {
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int CARD_COUNT = 8;
    boolean allCardsMatched = false;

    int score = 0;
    String[] path;
    private List<Card> cards;
    private CardButton[][] cardButtons;
    private CardButton firstCard;
    private CardButton secondCard;




    public CardGame() {
        cards = createCards();
        cardButtons = new CardButton[ROWS][COLS];
        setLayout(new GridLayout(ROWS, COLS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                CardButton button = new CardButton(row, col);
                button.addActionListener(this);
                add(button);
                cardButtons[row][col] = button;
            }
        }
        shuffleCards();
        updateButtonIcons();
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<Card>();

        path = new String[]{"D:\\Java Memory Game\\Java-Memory-Games\\res\\card_0.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_1.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_2.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_3.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_4.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_5.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_6.png","D:\\Java Memory Game\\Java-Memory-Games\\res\\card_7.png"};
        for (int i = 0;i<CARD_COUNT;i++){
            ImageIcon img = new ImageIcon(path[i]);

            cards.add(new Card(i,img));
            cards.add(new Card(i,img));
        }
        return cards;
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    private void updateButtonIcons() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                CardButton button = cardButtons[row][col];
                Card card = cards.get(row * COLS + col);
                button.setCard(card);
                button.setFaceUp(card.isFaceUp());
            }
        }
    }

    private void checkCards() {
        if (firstCard.getCard() == null || secondCard.getCard() == null) {
            return;
        }
        if (firstCard.getCard().getId() == secondCard.getCard().getId()) {
            firstCard.setEnabled(false);
            secondCard.setEnabled(false);
            score += 5;
            checkWin();
        }else {
            score-=1;
            firstCard.setFaceUp(false);
            secondCard.setFaceUp(false);
        }

        firstCard = null;
        secondCard = null;
    }


    private void checkWin() {
        boolean allCardsMatched = true;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cardButtons[row][col].isEnabled()) {
                    allCardsMatched = false;
                    break;
                }
            }
        }
        if (allCardsMatched) {
            JOptionPane.showMessageDialog(this, "YOU WON!!.. Your score is " + score);
//            JFrame gameWindow = (JFrame) SwingUtilities.getWindowAncestor(this);
//            gameWindow.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    System.exit(0);
//                }
//            });
//            gameWindow.dispatchEvent(new WindowEvent(gameWindow, WindowEvent.WINDOW_CLOSING));
//
        }
    }

//    private void restart() {
//        int response = JOptionPane.showConfirmDialog(this, "Play again?", "Game over", JOptionPane.YES_NO_OPTION);
//        if (response == JOptionPane.YES_OPTION) {
//
//            cards = createCards();
//            shuffleCards();
//            updateButtonIcons();
//            score = 0;
//        } else {
//            System.exit(0);
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardButton button = (CardButton) e.getSource();
        System.out.println("Button clicked: " + button.getRow() + ", " + button.getCol());
        System.out.println("Card: " + button.getCard());
        if (firstCard == null) {
            firstCard = button;
            firstCard.setFaceUp(true);
        } else if (secondCard == null && button != firstCard) {
            secondCard = button;
            secondCard.setFaceUp(true);
            checkCards();
        } else {
            return;
        }
        if (secondCard != null) {
            getRootPane().setDefaultButton(null);
        }
    }
}



//
//        for (int i = 0; i < CARD_COUNT; i++) {
//            String fileName = "card_" + i + ".png";
////            URL url = getClass().getClassLoader().getResource("res/" + fileName);
////            if (url == null) {
////                System.err.println("Error: could not load image " + fileName);
////            } else {
//                ImageIcon image = new ImageIcon(fileName);
//                System.out.println("Loaded image " + fileName);
//                cards.add(new Card(i, image));
//                cards.add(new Card(i, image));
////            }
//        }
//        return cards;