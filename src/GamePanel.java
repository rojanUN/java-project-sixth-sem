import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;     //static:so that all the instances made of GamePanel will be sharing same game width; final:is a little bit faster nothing other specific
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.6000));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PLAYER_WIDTH = 100;
    static final int PLAYER_HEIGHT = 150;
    File scoreFile = new File("score.wav");
    File gameSoundFile = new File("gameSound.wav");
    File gameOverFile = new File("gameOver.wav");
    boolean gameStarted = false;
    boolean gameOver = false;
    JButton playButton = new JButton("Play");

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Player player1;
    Player player2;
    Ball ball;
    Score score;
    private Image backgroundImage;


    GamePanel(){
        gameStarted = true;
        gameOver = false;

        playSound(gameSoundFile);
        newPaddles();
        newBall();

        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActListner());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void gameOver(){
        gameStarted= false;
        gameOver = true;
    }

    private void playSound(File soundFileName){
        File file = new File(soundFileName.toURI());
        Clip clip = null;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        clip.start();
    }
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles(){
        player1 = new Player(0,(GAME_HEIGHT/2)-(PLAYER_HEIGHT/2), PLAYER_WIDTH, PLAYER_HEIGHT,1);
        player2 = new Player(GAME_WIDTH-PLAYER_WIDTH,(GAME_HEIGHT/2)-(PLAYER_HEIGHT/2), PLAYER_WIDTH, PLAYER_HEIGHT,2);
    }

    public void paint(Graphics g){
        if(gameStarted) {
            backgroundImage = new ImageIcon("hockeyGround.jpg").getImage();
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            draw(g);
        }

        if(gameOver){
            backgroundImage = new ImageIcon("gameOver.jpg").getImage();
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            draw(g);

            playButton.setLabel("Play Again");
            this.add(playButton);
            playButton.setBounds((GAME_WIDTH/2)-50,(GAME_HEIGHT/2)+100,100,50);
            playButton.setForeground(Color.white);
            playButton.setBackground(Color.blue);
            playButton.setFocusable(false);
            playButton.setVisible(true);
        }
    }

    public void draw(Graphics g){
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        player1.move();
        player2.move();
        ball.move();
    }

    public void checkCollision(){
        //bounce ball off top & bottom window edges
        if(ball.y<=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        //bounces ball off paddles
        if(ball.intersects(player1)){       //cause ball extends all the properties of rectangle so we can use intersects function
            ball.xVelocity=Math.abs(ball.xVelocity);
            ball.xVelocity++;       //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++;   //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(player2)){       //cause ball extends all the properties of rectangle so we can use intersects function
            ball.xVelocity=Math.abs(ball.xVelocity);
            ball.xVelocity++;       //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++;   //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //stops paddles at window edges
        if(player1.y<=0)                                //upside
            player1.y=0;
        if(player1.y>=(GAME_HEIGHT-PLAYER_HEIGHT))      //downside
            player1.y=GAME_HEIGHT-PLAYER_HEIGHT;

        if(player2.y<=0)
            player2.y=0;
        if(player2.y>=(GAME_HEIGHT-PLAYER_HEIGHT))
            player2.y=GAME_HEIGHT-PLAYER_HEIGHT;

        //give a player 1 point and create new paddles & ball
        if(ball.x<=0){
            playSound(scoreFile);
            score.scorePlayer2++;
            if(score.scorePlayer2>=1){
                playSound(gameOverFile);
                gameOver();
            }
            newPaddles();
            newBall();
        }

        if(ball.x>=GAME_WIDTH-BALL_DIAMETER){
            playSound(scoreFile);
            score.scorePlayer1++;
            if(score.scorePlayer1>=1){
                playSound(gameOverFile);
                gameOver();
            }
            newPaddles();
            newBall();
        }
    }
    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSeconds = 1000000000/amountOfTicks;
        double delta = 0;
        while(gameStarted==true&&gameOver==false){
            long now = System.nanoTime();
            delta+=(now-lastTime)/nanoSeconds;
            lastTime = now;
            if(delta >=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class ActListner extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            player1.keyPressed(e);
            player2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}
