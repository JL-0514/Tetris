package com.tetris.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

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
     * The record for back-to-back. 
     * The first index keep the previous type of line clear; 1, 2, 3 corresponding to mini T-spin, Tetris, and T-spin.
     * The second index keep how many line cleared in previous line clear.
     * */
    private int[] myBackToBack;

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
        myBackToBack = new int[2];
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
     * Decide the type of current line clear and call the corresponding methods to get the score to update.
     * 
     * @param theLine Number of lines cleared.
     * @param theMiniT Whether a mini T-spin is performed.
     * @param theFullT Whether a full T-spin is performed.
     * @param theKick Whether a wall kick is performed.
     */
    public void addLine(final int theLine, final boolean theMiniT, final boolean theFullT, final boolean theKick) {
        if (theLine == 0 && !theFullT) { return; }
        int[] record = recordType(theLine, theMiniT, theFullT);
        // Back to back
        if (record[0] != 0 && myBackToBack[0] != 0) {
            addScore(scoreBackToBack(record, theKick));
        // Mini or full T-spin
        } else if (record[0] == 1 || record[0] == 3) {
            addScore(scoreTSpin(record, theKick));
        // Regular line clear or Tetris
        } else if (record[0] == 0 || record[0] == 2) {
            addScore(scoreRegular(theLine));
        }
        myBackToBack = record.clone();
        // Increment level
        if (myLine / 10 < (myLine + theLine) / 10) {
            incrementLevel();
        }
        myLine += theLine;
    }

    /**
     * Record the type of line clear.
     * 
     * @param theLine Number of lines cleared.
     * @param theMiniT Whether a mini T-spin is performed.
     * @param theFullT Whether a full T-spin is performed.
     * @return The record for the type of line clear.
     */
    private int[] recordType(final int theLine, final boolean theMiniT, final boolean theFullT) {
        int[] record = new int[2];
        if (theMiniT) {                 // Mini T-spin
            record[0] = 1;
        } else if (theLine == 4) {      // Tetris
            record[0] = 2;
        } else if (theFullT) {          // Full T-spin
            record[0] = 3;
        }
        record[1] = theLine;
        return record;
    }

    /**
     * Get the score for regular line clear.
     * 
     * @param theLine Number of lines cleared.
     * @return The score to add after cleared given number of lines.
     */
    private int scoreRegular(final int theLine) {
        int score = 0;
        switch (theLine) {
            case 1:     // Single
                score = (myLevel + 1) * 100;
                break;
            case 2:     // Double
                score = (myLevel + 1) * 300;
                break;
            case 3:     // Triple
                score = (myLevel + 1) * 500;
                break;
            case 4:     // Tetris
                score = (myLevel + 1) * 800;
                break;
            default:
                break;
        }
        return score;
    }

    /**
     * Get the score for T-spin (both mini and full).
     * 
     * @param theRecord The record for current line clear.
     * @param theKick Whether a wall kick is performed.
     * @return The score for T-spin.
     */
    private int scoreTSpin(final int[] theRecord, final boolean theKick) {
        int score = 0;
        if (theRecord[0] == 2 && theRecord[1] == 2) {
            score = (myLevel + 1) * 400;    // Mini T-spin Double
        } else {    // Full T-spin
            switch (theRecord[1]) {
                case 0:
                    if (theKick) {
                        score = (myLevel + 1) * 100;    // No line and kick
                    } else {
                        score = (myLevel + 1) * 400;    // No line and no kick
                    }
                    break;
                case 1:
                    if (theKick) {
                        score = (myLevel + 1) * 200;    // Single and kick
                    } else {
                        score = (myLevel + 1) * 800;    // Single and no kick
                    }
                    break;
                case 2:
                    score = (myLevel + 1) * 1200;       // Double
                    break;
                case 3:
                    score = (myLevel + 1) * 1600;       // Triple
                    break;
                default:
                    break;
            }
        }
        System.out.println(score);
        return score;
    }

    /**
     * Get the score for back-to-back.
     * 
     * @param theRecord The record for current line clear.
     * @param theKick Whether a wall kick is performed.
     * @return The score for back-to-back.
     */
    private int scoreBackToBack(final int[] theRecord, final boolean theKick) {
        int score = 0;
        // Not same type as previous line clear
        if (!Arrays.equals(theRecord, myBackToBack)) {
            // Full and mini T-spin
            if ((theRecord[0] == 1 && theRecord[1] == 2) || theRecord[0] == 3) {
                score = (int) (scoreTSpin(theRecord, theKick) * 1.5);
            // Tetris
            } else {
                score = (int) (scoreRegular(theRecord[1]) * 1.5);
            }
        // Same type
        } else {
            switch (theRecord[0]) {
                case 1:     // Mini T-spin
                    score = 300 * theRecord[1];
                    break;
                case 2:     // Tetris
                    score = 1200;
                    break;
                case 3:     // Full T-spin
                    score = 1200 + 600 * (theRecord[1] - 1);
                    break;
                default:
                    break;
            }
        }
        return score;
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
