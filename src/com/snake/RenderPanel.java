package com.snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.Color.*;

/**
 * Class responsible for painting all elements of the game
 * (snake, apple, texts, numbers etc.) on board.
 * Inherits from JPanel class.
 *
 * @author Klaudia Łowicka
 * @version 1.0
 * @since recently
 */
public class RenderPanel extends JPanel {

    /** Color of the board. */
    private static Color screenColor = new Color(92, 155, 209);
    /** Color of the snake. */
    private static Color snakeColor = new Color(255, 255, 255);
    /** Font type and size for displaying game's statistics. */
    private Font fStats = new Font("Montserrat", Font.BOLD, 18);
    /** Font type and size for displaying GAME OVER text. */
    private Font fGameOver = new Font("Montserrat", Font.BOLD, 25);
    /** Font type and size for displaying the text below GAME OVER. */
    private Font fPressSpace = new Font("Montserrat", Font.PLAIN, 20);

    /**
     * Implementation of function from JPanel class.
     * Paints all element on board.
     *
     * @param g graphics object
     * @see JPanel
     * @see Graphics
     * */
    @Override
    protected void paintComponent(Graphics g) {

        // Paint 800x800 screen with blue
        g.setColor(screenColor);
        g.fillRect(0, 0, 800, 800);

        // Call function to paint snake and apple
        try {
            paintSnakeAndApple(g);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Call function to paint statistics - texts and numbers
        drawStatistics(g);

    }


    /**
     * Paints snake (its body and head) with white color
     * and an apple with image from project sources.
     *
     * @param g graphics object
     * @throws IOException if apple image was not found in project sources
     */
    private void paintSnakeAndApple(Graphics g) throws IOException {

        Snake snake = Snake.snake;

        // Paint snake with white
        g.setColor(snakeColor);

        // Body of the snake
        for (Point point : snake.snakeParts)
        {
            g.fillRect(point.x * 10, point.y * 10, 9, 9);
        }
        // Head of the snake
        g.fillRect(snake.head.x * 10, snake.head.y * 10, 9, 9);

        // Paint 'apple' with image
        try
        {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("square.png"));
            g.drawImage(image, snake.apple.x * 10, snake.apple.y * 10, 10, 10, this);
        }
        catch(Exception e)
        {
            System.err.println("Can't find image. " + e);
        }

    }

    /**
     * Paint all texts and numbers
     * including statistics at the top of the board
     * (time, score and snake's length)
     * and GAME OVER text at the end of the game
     * with small instruction to press SPACE to play again.
     *
     * @param g graphics object
     */
    private void drawStatistics(Graphics g){

        Snake snake = Snake.snake;

        String sStats = "Score: " + snake.score + ", Length: " + snake.tailLength/2 + ", Time: " + snake.time / 20;

        // Add white text and line at the top of the screen
        g.setColor(WHITE);
        g.setFont(fStats);
        g.drawString(sStats, 260, 22);
        g.drawLine(0, 30, 800, 30);

        // Display text when the game is over
        if (snake.gameOver) {
            g.setFont(fGameOver);
            String sGameOver = "GAME OVER!";
            g.drawString(sGameOver, 310, 320);
            g.setFont(fPressSpace);
            String sPressSpace = "Press SPACE to play again";
            g.drawString(sPressSpace, 270, 360);
        }
    }
}
