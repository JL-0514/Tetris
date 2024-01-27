package com.tetris.model;

import java.util.Random;

/**
 * Abstract game block.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class AbstractBlock implements Block {

    /** Used to select random shape from the block. */
    private static Random myRand = new Random();

    /** All possible shape for the block. */
    private int[][][] myAllShapes;

    /** The current shape of the block. */
    private int myCurrentShape;

    /**
     * Constructor of abstract block. Only called by its child classes.
     * 
     * @param theShapes All possible shapes of the block.
     */
    protected AbstractBlock(final int[][][] theShapes) {
        super();
        myAllShapes = theShapes;
        myCurrentShape = myRand.nextInt(theShapes.length);
    }

    /**
     * Get the current shape of the block.
     * 
     * @return The current shape of the block.
     */
    public int[][] getCurrentShape() {
        return myAllShapes[myCurrentShape].clone();
    }

    /**
     * Rotate the block clockwise by 90 degrees.
     */
    public void rotateClockwise() {
        myCurrentShape++;
        if (myCurrentShape == myAllShapes.length) {
            myCurrentShape = 0;
        }
    }

    /**
     * Rotate the block counterclockwise by 90 degrees.
     */
    public void rotateCounterclockwise() {
        myCurrentShape--;
        if (myCurrentShape == -1) {
            myCurrentShape = myAllShapes.length - 1;
        }
    }
    
}
