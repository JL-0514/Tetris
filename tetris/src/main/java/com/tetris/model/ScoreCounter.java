package com.tetris.model;

public class ScoreCounter {

    private int myScore;

    private int myLevel;

    private int myLine;

    /** Delay between each drop of the block in milliseconds. */
    private int mySpeed;

    public ScoreCounter() {
        super();
        myScore = 0;
        myLevel = 0;
        myLine = 0;
        mySpeed = 790;
    }

    public int getScore() {
        return myScore;
    }

    public int getLevel() {
        return myLevel;
    }

    public int getLine() {
        return myLine;
    }

    public int getSpeed() {
        return mySpeed;
    }

    public void addScore(final int theScore) {
        myScore += theScore;
    }

    public void addLine(final int theLine) {
        myLine += theLine;
        if (myLine % 10 == 0) {
            incrementLevel();
        }
    }

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
