package com.tetris.model;

import java.util.Random;

import java.awt.Color;

/**
 * Abstract game piece.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class AbstractPiece implements Piece {

    /** Used to select random shape from the piece. */
    private static Random myRand = new Random();

    /** All possible shape for the piece. */
    private int[][][] myAllShapes;

    /** The current shape of the piece. */
    private int myCurrentShape;

    /** The next random shape. */
    private int myNextShape;

    /** The brighter color of the piece. */
    private Color myBrightColor;

    /** The darker color of the piece. */
    private Color myDarkColor;

    /** The instance of unit used to make piece. */
    private PieceUnit myUnit;

    /**
     * Constructor of abstract piece with given shapes and colors.
     * 
     * @param theShapes All possible shapes of the piece.
     * @param theBright The brighter color.
     * @param theDark The darker color.
     */
    protected AbstractPiece(final int[][][] theShapes, final Color theBright, final Color theDark) {
        super();
        myAllShapes = theShapes;
        myCurrentShape = myRand.nextInt(theShapes.length);
        myNextShape = myRand.nextInt(theShapes.length);
        myBrightColor = theBright;
        myDarkColor = theDark;
        myUnit = new PieceUnit(theBright, theDark);
    }

    /**
     * Get the current shape of the piece.
     * 
     * @return The current shape of the piece.
     */
    public int[][] getCurrentShape() {
        return myAllShapes[myCurrentShape].clone();
    }

    /**
     * Get the next random shape of the piece.
     * 
     * @return The next random shape of the piece.
     */
    public int[][] getNextShape() {
        return myAllShapes[myNextShape].clone();
    }

    /**
     * Get the piece unit that made up the piece.
     * 
     * @return The piece unit.
     */
    public PieceUnit getUnit() {
        return myUnit;
    }

    /**
     * Get get size of the piece (the number of units at each side).
     * 
     * @return Size of the piece.
     */
    public int getSize() {
        return myAllShapes[0].length;
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
     * Rotate the piece clockwise by 90 degrees.
     * 
     * @param theSurrounding The area the piece may touch as it rotate.
     */
    public void rotateClockwise(PieceUnit[][] theSurrounding) {
        int target = myCurrentShape + 1;
        if (target == myAllShapes.length) {
            target = 0;
        }
        if (isValidRotate(theSurrounding, target)) {
            myCurrentShape = target;
        }
    }

    /**
     * Rotate the piece counterclockwise by 90 degrees.
     * 
     * @param theSurrounding The area the piece may touch as it rotate.
     */
    public void rotateCounterclockwise(PieceUnit[][] theSurrounding) {
        int target = myCurrentShape - 1;
        if (target == -1) {
            target = myAllShapes.length - 1;
        }
        if (isValidRotate(theSurrounding, target)) {
            myCurrentShape = target;
        }
    }

    /**
     * Check whether the piece can be rotated in the given surrounding.
     * 
     * @param theSurrounding The area the piece may touch as it rotate.
     * @param theTarget The index of the shape the piece is about to rotate to.
     * @return Whether the piece can be rotated.
     */
    public boolean isValidRotate(PieceUnit[][] theSurrounding, int theTarget) {
        boolean valid = true;
        outerLoop:
        for (int i = 0; i < theSurrounding.length; i++) {
            for (int j = 0; j < theSurrounding[0].length; j++) {
                if (theSurrounding[i][j] != null && myAllShapes[theTarget][i][j] == 1) {
                    valid = false;
                    break outerLoop;
                }
            }
        }
        return valid;
    }

    /**
     * Change the color of the unit when the background color changed.
     * 
     * @param theBackground The new background color.
     */
    public void backgroundChanged(final Color theBackground) {
        if (theBackground == Color.BLACK) {
            myUnit.setInnerColor(myDarkColor);
            myUnit.setOuterColor(myBrightColor);
        } else if (theBackground == Color.WHITE) {
            myUnit.setInnerColor(myBrightColor);
            myUnit.setOuterColor(myDarkColor);
        }
    }
    
}
