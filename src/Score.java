import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int scorePlayer1;
    int scorePlayer2;

    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH=GAME_WIDTH;
        Score.GAME_HEIGHT=GAME_HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN,60));

        g.drawString(String.valueOf(scorePlayer1), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(scorePlayer2), (GAME_WIDTH/2)+20, 50);
    }
}