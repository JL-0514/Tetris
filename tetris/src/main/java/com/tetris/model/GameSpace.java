package com.tetris.model;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * The game space contains all the pieces used in the game.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class GameSpace {

    /** A block acts as a wall around the game space. */
    private final static PieceUnit WALL = new PieceUnit();

    /** The general setting of the game. */
    private final Setting mySetting;

    /** All available blocks in the game. */
    private final Piece[] myAllPieces;
    
    /** The pieces placed in the game space. */
    private final PieceUnit[][] myPieces;

    /** Used to select a random piece. */
    private static Random myRand;

    /** The row where the bottom-left corner of the piece is placed. */
    private int myRow;

    /** The column where the bottom-left corner of the piece is placed. */
    private int myColumn;

    /** The current piece. */
    private Piece myCurrentPiece;

    /** The next piece. */
    private Piece myNextPiece;

    /** Whether a wall kick is performed. */
    private boolean myHasKick;

    /** If 0, not T-spin. If 1, mini T-spin. If 2, full T-spin. */
    private int myTSpin;

    /**
     * Create a game space.
     * 
     * @param theSetting
     */
    public GameSpace(final Setting theSetting) {
        super();
        mySetting = theSetting;
        myPieces = new PieceUnit[21][14];
        myAllPieces = new Piece[]{new IPiece(), new JPiece(), new LPiece(), new OPiece(), 
                      new SPiece(), new TPiece(), new ZPiece()};
        myRand = new Random();
        myHasKick = false;
        myTSpin = 0;
        setup();
    }

    /**
     * Set up the game space.
     */
    private void setup() {
        mySetting.addPropertyChangeListener(new PieceChangeListener());
        // Fill the wall around the game space
        for (int i = 0; i < 20; i++) {
            myPieces[i][0] = WALL;
            myPieces[i][1] = WALL;
            myPieces[i][12] = WALL;
        }
        for (int i = 0; i < 14; i++) {
            myPieces[20][i] = WALL;
        }
    }

    /**
     * Get the row of the bottom left corner of the current piece.
     * 
     * @return The row of the bottom left corner of the current piece.
     */
    public int getCurrentRow() {
        return myRow;
    }

    /**
     * Get the column of the bottom left corner of the current piece.
     * 
     * @return The column of the bottom left corner of the current piece.
     */
    public int getCurrentColumn() {
        return myColumn;
    }

    /**
     * Get the current piece.
     * 
     * @return The current piece.
     */
    public Piece getCurrentPiece() {
        return myCurrentPiece;
    }

    /**
     * Get the next piece.
     * 
     * @return The next piece.
     */
    public Piece getNextPiece() {
        return myNextPiece;
    }

    /**
     * Get pieces currently placed in the game space.
     * 
     * @return Pieces currently placed in the game space.
     */
    public PieceUnit[][] getPieces() {
        return myPieces;
    }

    /**
     * Set whether a wall kick is performed.
     * 
     * @param theKick Whether a wall kick is performed.
     */
    public void setHasKick(final boolean theKick) {
        myHasKick = theKick;
    }

    /**
     * Set whether a T-spin is performed.
     * 
     * @param theSpin Whether a T-spin is performed.
     */
    public void setTSpin(final int theSpin) {
        myTSpin = theSpin;
    }

    /**
     * Change the current piece to the given piece.
     * 
     * @param thePiece The new current piece.
     */
    protected void setCurrentPiece(final Piece thePiece) {
        myCurrentPiece = thePiece;
    }

    /**
     * Perform a hard drop on the current piece and return the number of lines dropped.
     * 
     * @return The number of lines dropped.
     */
    public int hardDrop() {
        int moves = 0;
        while (canMove(myRow + 1, myColumn)) {
            myRow++;
            moves++;
        }
        return moves;
    }

     /**
     * Check whether the current piece can move to given row and column.
     * The row and column indicate the destination position of the bottom left corner of the piece.
     * Search from the bottom left corner to top right until there's overlapping piece units.
     * 
     * @param theRow The row of the the destination position of the bottom left corner of the piece.
     * @param theCol The column of the the destination position of the bottom left corner of the piece.
     * @return Whether the current piece can move to given row and column.
     */
    public boolean canMove(final int theRow, final int theCol) {
        boolean move = true;
        final int[][] shape = myCurrentPiece.getCurrentShape();
        outerLoop:
        for (int r = shape.length - 1, i = 0; r > -1; r--, i++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 1 && (theRow - i > 19 || theCol + c < 2 || theCol + c > 11 ||
                    (theRow - i > -1 && myPieces[theRow - i][theCol + c] != null))) {
                    move = false;
                    break outerLoop;
                }
            }
        }
        return move;
    }

    /**
     * Get the surrounding of the current piece, that is, the piece units the current piece
     * may touch as it rotate.
     * 
     * @param theDown Get the surrounding n rows below the current piece.
     * @param theRight Get the surrounding n columns on the right of the current piece.
     * @return The surrounding of the current piece.
     */
    public PieceUnit[][] getSurrounding(final int theDown, final int theRight) {
        PieceUnit[][] surrounding = new PieceUnit[myCurrentPiece.getSize()][myCurrentPiece.getSize()];
        for (int r = surrounding.length - 1, i = 0; r > -1 && myRow - i > -1; r--, i++) {
            for (int c = 0; c < surrounding[0].length; c++) {
                final int row = myRow - i + theDown;
                final int col = myColumn + c + theRight;
                if (row > 20 || row < 0 || col > 13 || col < 0) {
                    surrounding[r][c] = WALL;
                } else {
                    surrounding[r][c] = myPieces[row][col];
                }
            }
        }
        return surrounding;
    }

    /**
     * Check whether there's a T-spin. Only called after a rotation.
     */
    public void checkTSpin() {
        if (myCurrentPiece instanceof TPiece) {
            myTSpin = ((TPiece) myCurrentPiece).isTSpin(getSurrounding(0, 0));
        }
    }

    /**
     * Check whether there's any filled rows. If so, clear them.
     * This must be done before getting next piece.
     * 
     * @param theCounter The score counter.
     */
    public void clearLine(final ScoreCounter theCounter) {
        int cleared = 0;
        int r = myRow < 20 ? myRow : 19;
        for (; r > myRow - myCurrentPiece.getSize() && r > -1; r--) {
            boolean fill = true;
            int lines = 0;
            while (fill) {
                // Clear the filled row
                if (cleared > 0 && r - cleared > -1) {
                    for (int c = 2; c < 12; c++) {
                        myPieces[r][c] = myPieces[r - cleared][c];
                    }
                }
                // Find a filled row
                for (int c = 2; c < 12; c++) {
                    if (myPieces[r][c] == null) {
                        fill = false;
                        break;
                    }
                }
                // Count the filled row
                if (fill) {
                    cleared++;
                    lines++;
                }
            }
            // Add score
            if (lines > 0) {
                theCounter.addLine(lines, myTSpin == TPiece.MINI_T_SPIN, myTSpin == TPiece.FULL_T_SPIN, myHasKick); 
            }
        }
        // Add score for full T-spin no line
        if (cleared == 0 && myTSpin == TPiece.FULL_T_SPIN) {
            theCounter.addLine(0, false, true, myHasKick);
        }
    }

    /**
     * Place the current piece at the current position and check whether the game is over.
     * 
     * @return Whether the game is over, which means the piece has reached the top.
     */
    public boolean placeCurrent() {
        final int[][] shape = myCurrentPiece.getCurrentShape();
        boolean over = false;
        outerLoop:
        for (int r = myRow, i = shape.length - 1; i > -1; r--, i--) {
            for (int c = myColumn, j = 0; j < shape[0].length; c++, j++) {
                if (shape[i][j] == 1) {
                    if (r < 0) {    // Reach the top, end the game
                        over = true;
                        break outerLoop;
                    }
                    myPieces[r][c] = myCurrentPiece.getUnit();
                }
            }
        }
        return over;
    }

    /**
     * Get next piece.
     */
    public void nextPiece() {
        myCurrentPiece = myNextPiece;
        myNextPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
        myCurrentPiece.setRandomShape();
        myRow = 0;
        myColumn = 5;
    }

    /**
     * Drop the currrent piece by given number of rows.
     * 
     * @param theDrop The number of rows to drop.
     */
    public void dropCurrent(final int theDrop) {
        myRow += theDrop;
    }

    /**
     * Shift the current piece to the left by given number of columns.
     * 
     * @param theShift The number of columns to shift.
     */
    public void shiftCurrentLeft(final int theShift) {
        myColumn -= theShift;
    }

    /**
     * Shift the current piece to the right by given number of columns.
     * 
     * @param theShift The number of columns to shift.
     */
    public void shiftCurrentRight(final int theShift) {
        myColumn += theShift;
    }

    /**
     * Set the game space to the starting setup.
     */
    public void newGame() {
        myCurrentPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
        myNextPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
        myRow = 0;
        myColumn = 5;
        myHasKick = false;
        myTSpin = 0;
    }

    /**
     * Clear the game space.
     */
    public void clear() {
        myCurrentPiece = null;
        myNextPiece = null;
        for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 12; j++) {
                myPieces[i][j] = null;
            }
        }
    }


    /**
     * Property change listener that listen to the change in background.
     */
    private class PieceChangeListener implements PropertyChangeListener {
        /**
         * {@inheritDoc}
         * Change the color of each piece when background color changes.
         */
        @Override
        public void propertyChange(final PropertyChangeEvent e) {
            if (e.getPropertyName().equals("color")) {
                for (Piece p : myAllPieces) {
                    p.backgroundChanged((Color) e.getNewValue());
                }
            }
        }
    }

}
