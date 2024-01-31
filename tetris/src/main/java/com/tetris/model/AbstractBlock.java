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

    /** The next random shape. */
    private int myNextShape;

    /**
     * Constructor of abstract block. Only called by its child classes.
     * 
     * @param theShapes All possible shapes of the block.
     */
    protected AbstractBlock(final int[][][] theShapes) {
        super();
        myAllShapes = theShapes;
        myCurrentShape = myRand.nextInt(theShapes.length);
        myNextShape = myRand.nextInt(theShapes.length);
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
     * Get the next random shape of the block.
     * 
     * @return The next random shape of the block.
     */
    public int[][] getNextShape() {
        return myAllShapes[myNextShape].clone();
    }

    /**
     * Replace the current shape with the next random shape
     * and set a new next random shape.
     */
    public void setRandomShape() {
        myCurrentShape = myNextShape;
        myNextShape = myRand.nextInt(myAllShapes.length);
    }

    /**
     * Rotate the block clockwise by 90 degrees.
     * 
     * @param theSurrounding The area the block may touch as it rotate.
     */
    public void rotateClockwise(BlockUnit[][] theSurrounding) {
        int target = myCurrentShape + 1;
        if (target == myAllShapes.length) {
            target = 0;
        }
        if (isValidRotate(theSurrounding, target)) {
            myCurrentShape = target;
        }
    }

    /**
     * Rotate the block counterclockwise by 90 degrees.
     * 
     * @param theSurrounding The area the block may touch as it rotate.
     */
    public void rotateCounterclockwise(BlockUnit[][] theSurrounding) {
        int target = myCurrentShape - 1;
        if (target == -1) {
            target = myAllShapes.length - 1;
        }
        if (isValidRotate(theSurrounding, target)) {
            myCurrentShape = target;
        }
    }

    /**
     * Check whether the block can be rotated in the given surrounding.
     * 
     * @param theSurrounding The area the block may touch as it rotate.
     * @param theTarget The index of the shape the block is about to rotate to.
     * @return Whether the block can be rotated.
     */
    public boolean isValidRotate(BlockUnit[][] theSurrounding, int theTarget) {
        boolean valid = true;
        for (int i = 0; i < theSurrounding.length; i++) {
            for (int j = 0; j < theSurrounding[0].length; j++) {
                if (theSurrounding[i][j] != null && myAllShapes[theTarget][i][j] == 1) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }
    
}
