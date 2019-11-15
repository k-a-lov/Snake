package com.snake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Describes main properties of the board (size, visibility etc.)
 * and implements KeyListener interface for processing keyboard events
 * (user presses arrow keys on keyboard to manipulate the snake).
 *
 * @author Klaudia Łowicka
 * @version 1.0
 * @since recently
 */
public class Board implements KeyListener {

    /** Board object. */
    public static Board board;

    /** JFrame object.
     *
     * @see JFrame
     * */
    public JFrame jframe = new JFrame();

    /** RenderPanel object.
     *
     * @see RenderPanel
     * */
    public RenderPanel renderPanel = new RenderPanel();

    /**
     * Class constructor - sets board title, size, location,
     * visibility and default close operation.
     * Adds KeyListener to jframe object.
     *
     * @see KeyListener
     * */
    public Board(){
        jframe.setTitle("Snake");
        jframe.setVisible(true);
        jframe.setSize(800, 800);
        jframe.setLocationRelativeTo(null);
        jframe.add(renderPanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Changes direction of snake based on the arrow key pressed by user.
     *
     * @param e object of KeyEvent class
     *
     * @see KeyEvent
     * */
    @Override
    public void keyReleased(KeyEvent e) {
        int i = e.getKeyCode();
        if ((i == KeyEvent.VK_LEFT) && Snake.snake.direction != Snake.RIGHT)
        {
            Snake.snake.direction = Snake.LEFT;
        }

        if ((i == KeyEvent.VK_RIGHT) && Snake.snake.direction != Snake.LEFT)
        {
            Snake.snake.direction = Snake.RIGHT;
        }

        if ((i == KeyEvent.VK_UP) && Snake.snake.direction != Snake.DOWN)
        {
            Snake.snake.direction = Snake.UP;
        }

        if ((i == KeyEvent.VK_DOWN) && Snake.snake.direction != Snake.UP)
        {
            Snake.snake.direction = Snake.DOWN;
        }
        if (i == KeyEvent.VK_SPACE && Snake.snake.gameOver)
        {
            Snake.snake.startGame();
        }
    }
}
