package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class which initiates the game board, creates a snake, manages its moves,
 * adds points and checks whether the game is over.
 * Implements ActionListener interface for receiving action events.
 *
 * @author Klaudia Łowicka
 * @version 1.0
 * @since recently
 */
public class Snake implements ActionListener {

    /** Snake object. */
    public static Snake snake;

    /** Timer object for measuring time during one round of the game (in seconds).
     * @see java.util.Timer
     */
    private Timer timer = new Timer(0, this);

    /** Array of points making up body of the snake. */
    public ArrayList<Point> snakeParts = new ArrayList<Point>();

    /** Point indicating head of the snake. */
    public Point head;

    /** Point indicating an apple - snake's snack.
     * Eating an apple makes snake grow.
     */
    public Point apple;

    /** Instance of Random class to generate a stream of pseudorandom numbers.
     * Numbers are then used for placing an apple on the board.
     * @see Random
     */
    private Random random;

    /** Possible moves of the snake: UP, DOWN, LEFT and RIGHT
     * Snake is directed by the user pressing arrow keys.
     */
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    /** Integer used for controlling speed of the game. */
    public int ticks = 0;

    /** Variable which stores current direction of the snake. */
    public int direction;

    /** Value of scores obtained by user. */
    public int score;

    /** Length of the snake - i.e. number of points making up the whole snake - body and head. */
    public int tailLength;

    /** Variable which stores number of seconds which have passed since the beginning of the game. */
    public int time;

    /** Indicates whether the game is over.
     * The game is over when either:
     * - snake hits the wall - end of the board,
     * - head of the snake hits body of the snake.
     */
    public boolean gameOver;

    /** Constructor - initiates the board and starts the game. */
    public Snake(){
        Board.board = new Board();
        startGame();
    }

    /** Starts the game by setting the values of all the initial variables:
     * Gameover is <code>false</code>, direction is DOWN, number of scores is 0,
     * snake has head at a certain point (right corner of the board) and body length of 2,
     * time is 0 (and starts counting seconds) and an apple is placed in a random point.
     * */
    public void startGame(){
        gameOver = false;
        score = 0;
        tailLength = 2;
        time = 0;
        direction = DOWN;
        snakeParts.clear();
        head = new Point(1,3);
        random = new Random();
        apple = new Point(random.nextInt(79), random.nextInt(75 + 1 - 3) + 3);
        timer.start();
    }


    /**
     * Implementation of function from ActionListener interface.
     * An action is every repaint of a board - i.e. every move of the snake.
     * With every move a point on board is added at the beginning of the snake (the head)
     * and one is removed from the end of the snake.
     * Function also checks if each move is valid and the game is not over.
     * When a move leads to snake eating an apple user gets 10 scores
     * and the snake's length is increased.
     * @see ActionListener
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Board.board.renderPanel.repaint();
        ticks++;

// && head != null
        if (ticks % 5 == 0 && !gameOver){

            time++;

            snakeParts.add(new Point(head.x, head.y));

            changeDirection();

            if (snakeParts.size() > tailLength)
                snakeParts.remove(0);

            if (apple != null & head.equals(apple)){
                    score += 10;
                    tailLength += 2;
                    apple.setLocation(random.nextInt(79), random.nextInt(75 + 1 - 3) + 3);
            }
        }
    }

    /**
     * Control of snake's movement and direction changes.
     * When user changes snake's direction the function is called
     * and checks whether the movement is valid.
     * If it is valid snake's direction is changed and the head appears
     * in a proper place on the board.
     * If the direction is not valid value of gameOver variable is changed to
     * <code>true</code>.
     * */
    private void changeDirection(){
        switch(direction)
        {
            case UP : if (head.y - 1 >= 3  && noTailAt(head.x, head.y - 1))
                head = new Point(head.x, head.y - 1);
            else gameOver = true;
                break;
            case DOWN : if (head.y + 1 < 76  && noTailAt(head.x, head.y + 1))
                head = new Point(head.x, head.y + 1);
            else gameOver = true;
                break;
            case LEFT : if (head.x - 1 >= 0  && noTailAt(head.x - 1, head.y))
                head = new Point(head.x - 1, head.y);
            else gameOver = true;
                break;
            case RIGHT : if (head.x + 1 < 80  && noTailAt(head.x + 1, head.y))
                head = new Point(head.x + 1, head.y);
            else gameOver = true;
                break;
        }
    }

    /**
     * Checks if head of the snake is not hitting its body.
     * @param x the x-coordinate of the snake's head new position
     *          (after direction change)
     * @param y the y-coordinate of the snake's head new position
     *          (after direction change)
     * */
    private boolean noTailAt(int x, int y)
    {
        for (Point point : snakeParts)
        {
            if (point.equals(new Point(x, y)))
            {
                return false;
            }
        }
        return true;
    }

}
