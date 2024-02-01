package com.tetris.model;

import java.awt.Color;

/**
 * Interface that represents all types of pieces in tetris.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public interface Piece {

    /**
     * Get the current shape of the piece.
     * 
     * @return The current shape of the piece.
     */
    public int[][] getCurrentShape();

    /**
     * Get the next random shape of the piece.
     * 
     * @return The next random shape of the piece.
     */
    public int[][] getNextShape();

    /**
     * Get the piece unit that made up the piece.
     * 
     * @return The piece unit.
     */
    public PieceUnit getUnit();

    /**
     * Replace the current shape with the next random shape
     * and set a new next random shape.
     */
    public void setRandomShape();
    
    /**
     * Rotate the piece clockwise by 90 degrees.
     * 
     * @param theSurrounding The area the piece may touch as it rotate.
     */
    public void rotateClockwise(PieceUnit[][] theSurrounding);

    /**
     * Rotate the piececounterclockwise by 90 degrees.
     * 
     * @param theSurrounding The area the piece may touch as it rotate.
     */
    public void rotateCounterclockwise(PieceUnit[][] theSurrounding);

    /**
     * Check whether the piece can be rotated in the given surrounding.
     * 
     * @param theSurrounding The area the piece may touch as it rotate.
     * @param theTarget The index of the shape the piece is about to rotate to.
     * @return Whether the piece can be rotated.
     */
    public boolean isValidRotate(PieceUnit[][] theSurrounding, int theTarget);

     /**
     * Change the color of the unit when the background color changed.
     * 
     * @param theBackground The new background color.
     */
    public void backgroundChanged(Color theBackground);
    
}
