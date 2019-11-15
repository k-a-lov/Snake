package com.snake;

/**
 * Main class of the game.
 * Creates instance of Snake class starting the game.
 *
 * @author Klaudia Łowicka
 * @version 1.0
 * @since recently
 */
public class Main {

    /**
     *  Creates instance of Snake class starting the game.
     *
     *  @see Snake
     * */
    public static void main(String[] args){
        Snake.snake = new Snake();
    }
}
