package com.tetris.model;

import java.awt.Color;

/**
 * Represent a unit of a piece.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class PieceUnit {

    /** Design of the piece unit. */
    public static final int[][] DESIGN = {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 0},
        {0, 1, 0, 1, 0, 1, 1, 0},
        {0, 1, 0, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };

    /** The length of each side of the whole unit. */
    public static final int SIZE = 32;

    /** The length of each side of the sub unit in the whole unit. */
    public static final int SUB_SIZE = SIZE / DESIGN[0].length;

    /** The bright color used in the unit. */
    private Color myBrightColor;

    /** The dark color used in the unit. */
    private Color myDarkColor;

    /**
     * Constructor used to create placeholder used as wall.
     */
    public PieceUnit() {
        super();
    }

    /**
     * Create a block unit with given colors.
     * 
     * @param theBright The bright color of the unit.
     * @param theDark The dark color of the unit.
     */
    public PieceUnit(final Color theBright, final Color theDark) {
        super();
        myBrightColor = theBright;
        myDarkColor = theDark;
    }

    /**
     * Get the bright color used in the unit.
     * 
     * @return The bright color used in the unit.
     */
    public Color getBrightColor() {
        return myBrightColor;
    }

    /**
     * Get the dark color used in the unit.
     * 
     * @return The dark color used in the unit.
     */
    public Color getDarkColor() {
        return myDarkColor;
    }
    
}
