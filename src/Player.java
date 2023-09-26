import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Player extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;
    private Image player1Image;
    private Image player2Image;

    Player(int x, int y, int PLAYER_WIDTH, int PLAYER_HEIGHT, int id){
        super(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
        this.id=id;
    }
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
        }

    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void move(){
        y = y + yVelocity;
    }

    public void draw(Graphics g){
        if(id==1) {
            System.out.println("hi");
            player1Image = new ImageIcon("Player1.png").getImage();
            g.drawImage(player1Image,x,y, width, height, null);
        }
        else {
            player2Image = new ImageIcon("Player2.png").getImage();
            g.drawImage(player2Image,x,y, width, height, null);
        }

    }
}