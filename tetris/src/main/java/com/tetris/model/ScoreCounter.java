package com.tetris.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    /** Property change support for setting changes. */
    private final PropertyChangeSupport myPCS;

    /**
     * Create a score counter.
     */
    public ScoreCounter() {
        super();
        myScore = 0;
        myLevel = 0;
        myLine = 0;
        mySpeed = INIT_SPEED;
        myPCS = new PropertyChangeSupport(this);
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
        myPCS.firePropertyChange("score", myScore - theScore, myScore);
    }

    /**
     * Add a number of lines cleared and update the score and level.
     * 
     * @param theLine Number of lines cleared.
     */
    public void addLine(final int theLine) {
        // Add score
        switch (theLine) {
            case 1:
                addScore((myLevel + 1) * 100);
                break;
            case 2:
                addScore((myLevel + 1) * 300);
                break;
            case 3:
                addScore((myLevel + 1) * 500);
                break;
            case 4:
                addScore((myLevel + 1) * 800);
                break;
            default:
                break;
        }
        // Increment level
        if (myLine / 10 < (myLine + theLine) / 10) {
            incrementLevel();
        }
        myLine += theLine;
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
            mySpeed -= 20;
        }
        myPCS.firePropertyChange("level", myLevel - 1, myLevel);
    }

    /**
     * Reset the setting.
     */
    public void reset() {
        myLevel = 0;
        myLine = 0;
        myScore = 0;
        mySpeed = INIT_SPEED;
    }


    /**
     * Add property change listener to the property change support.
     * 
     * @param theListener The property change listener for setting
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * Remove property change listener to the property change support.
     * 
     * @param theListener The property change listener for setting
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
    
}
