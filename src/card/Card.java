package card;
import javax.swing.ImageIcon;

import static javax.swing.text.StyleConstants.setIcon;

public class Card {
    private int id;
    private ImageIcon image;
    private boolean faceUp;

    public Card(int id, ImageIcon image) {
        this.id = id;
        this.image = image;
        this.faceUp = false; // cards are initially face down
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

}