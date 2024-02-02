package com.tetris.model;

/**
 * The score counter used to keep track of the total score, current level,
 * number of line cleared, and current speed.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class ScoreCounter {

    /** The initial speed of the game. */
    public static final int INIT_SPEED = 790;

    /** The total score. */
    private int myScore;

    /** The current level. */
    private int myLevel;

    /** The total number of lines cleared. */
    private int myLine;

    /** Delay between each drop of the piece in milliseconds. */
    private int mySpeed;

    /**
     * Create a score counter.
     */
    public ScoreCounter() {
        super();
        myScore = 0;
        myLevel = 0;
        myLine = 0;
        mySpeed = INIT_SPEED;
    }

    /**
     * Get the total score.
     * 
     * @return The total score.
     */
    public int getScore() {
        return myScore;
    }

    /**
     * Get the level reached.
     * 
     * @return The level reached.
     */
    public int getLevel() {
        return myLevel;
    }

    /**
     * Get the number of lines cleared.
     * 
     * @return The number of lines cleared.
     */
    public int getLine() {
        return myLine;
    }

    /**
     * Get the delay between each drop in milliseconds.
     * 
     * @return The delay between each drop in milliseconds.
     */
    public int getSpeed() {
        return mySpeed;
    }

    /**
     * Add some scores to the total score.
     * 
     * @param theScore The scores to add.
     */
    public void addScore(final int theScore) {
        myScore += theScore;
    }

    /**
     * Add a number of lines cleared.
     * 
     * @param theLine Number of lines cleared.
     */
    public void addLine(final int theLine) {
        myLine += theLine;
        if (myLine % 10 == 0) {
            incrementLevel();
        }
    }

    /**
     * Increment level by one for each 10 lines cleared.
     */
    private void incrementLevel() {
        myLevel++;
        if (myLevel < 9) {
            mySpeed -= 80;
        } else if (myLevel == 9) {
            mySpeed -= 30;
        } else if (myLevel == 10 || myLevel == 13 || myLevel == 16 || myLevel == 19 || myLevel == 29) {
            myScore -= 20;
        }
    }
    
}
