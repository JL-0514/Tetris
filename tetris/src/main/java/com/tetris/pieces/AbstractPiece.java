package com.tetris.pieces;

import java.util.Random;

import com.tetris.model.GameSpace;

import java.awt.Color;

/**
 * Abstract game piece.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class AbstractPiece implements Piece {

    /*
     * Wall kick:
     * When the piece is rotating, there are five different position it may end up with.
     * If it can't move to one of those positions, the rotation fails.
     * The default positions are listed below. I-block and O-block have their own position.
     */

    /** The wall kick data that specify the number of {rows, columns} to move */
    private static final int[][][] DEFAULT_WALL_KICK = {
        {{0, 0}, {0, -1}, {-1, -1}, {2, 0}, {2, -1}}, 
        {{0, 0}, {0, 1}, {1, 1}, {-2, 0}, {-2, 1}}, 
        {{0, 0}, {0, 1}, {-1, 1}, {2, 0}, {2, 1}}, 
        {{0, 0}, {0 ,-1}, {1, -1}, {-2, 0}, {-2, -1}}
    };

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

    /** Wall kick data of this piece. */
    private final int[][][] myWalkKick;

    /**
     * Constructor of abstract piece with given shapes and colors.
     * 
     * @param theShapes All possible shapes of the piece.
     * @param theBright The brighter color.
     * @param theDark The darker color.
     * @param theWallKick Wall kick data of this piece.
     */
    protected AbstractPiece(final int[][][] theShapes, final Color theBright, final Color theDark, 
                            final int[][][] theWallKick) {
        super();
        myAllShapes = theShapes;
        myCurrentShape = myRand.nextInt(theShapes.length);
        myNextShape = myRand.nextInt(theShapes.length);
        myBrightColor = theBright;
        myDarkColor = theDark;
        myUnit = new PieceUnit(theBright, theDark);
        if (theWallKick == null) {
            myWalkKick = DEFAULT_WALL_KICK;
        } else {
            myWalkKick = theWallKick;
        }
    }

    /**
     * Get the index of the current shape.
     * 
     * @return The index of the current shape.
     */
    protected int getCurrentShapeIdx() {
        return myCurrentShape;
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
     * @param theSpace The game space.
     * @return An array [row, column] that represent how many rows and columns to move when rotating.
     *         If the rotation fails, return null.
     */
    public int[] rotateClockwise(final GameSpace theSpace) {
        int[] move = null;
        int target = myCurrentShape + 1;
        if (target == myAllShapes.length) {
            target = 0;
        }
        for (int[] wk : myWalkKick[myCurrentShape]) {
            final PieceUnit[][] surrounding = theSpace.getSurrounding(wk[0], wk[1]);
            if (isValidRotate(surrounding, target)) {
                myCurrentShape = target;
                move = new int[]{wk[0], wk[1]};
                break;
            }
        }
        return move;
    }

    /**
     * Rotate the piece counterclockwise by 90 degrees.
     * 
     * @param theSpace The game space.
     * @return An array [row, column] that represent how many rows and columns to move when rotating.
     *         If the rotation fails, return null.
     */
    public int[] rotateCounterclockwise(final GameSpace theSpace) {
        int[] move = null;
        int target = myCurrentShape - 1;
        if (target == -1) {
            target = myAllShapes.length - 1;
        }
        for (int[] wk : myWalkKick[target]) {
            final PieceUnit[][] surrounding = theSpace.getSurrounding(-wk[0], -wk[1]);
            if (isValidRotate(surrounding, target)) {
                myCurrentShape = target;
                move = new int[]{-wk[0], -wk[1]};
                break;
            }
        }
        return move;
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
