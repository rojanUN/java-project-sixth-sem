package card;

import javax.swing.*;

public class CardButton extends JButton {
    private int row;
    private int col;
    private Card card;
    private boolean faceUp;

    public CardButton(int row, int col) {
        this.row = row;
        this.col = col;

        this.faceUp = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        if (faceUp) {
            setIcon(card.getImage());
        } else {
            setIcon(null);
        }
        card.setFaceUp(faceUp);
    }
}
