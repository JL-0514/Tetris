package com.tetris.model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represent a unit of a piece.
 * Every piece is made up by four units of the same color.
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

    /** The color used in the outer side of the unit. */
    private Color myOuterColor;

    /** The color used in the inner side of the unit. */
    private Color myInnerColor;

    /**
     * Constructor used to create placeholder used as wall.
     */
    public PieceUnit() {
        super();
    }

    /**
     * Create a block unit with given colors.
     * 
     * @param theOuter The outer color of the unit.
     * @param theInner The inner color of the unit.
     */
    public PieceUnit(final Color theOuter, final Color theInner) {
        super();
        myOuterColor = theOuter;
        myInnerColor = theInner;
    }

    /**
     * Get the outer color used in the unit.
     * 
     * @return The outer color used in the unit.
     */
    public Color getOuterColor() {
        return myOuterColor;
    }

    /**
     * Get the inner color used in the unit.
     * 
     * @return The inner color used in the unit.
     */
    public Color getInnerColor() {
        return myInnerColor;
    }

    /**
     * Change the outer color of the unit.
     * 
     * @param theOuter The new outer color.
     */
    public void setOuterColor(final Color theOuter) {
        myOuterColor = theOuter;
    }

    /**
     * Change the innner color of the unit.
     * 
     * @param theInner The new inner color.
     */
    public void setInnerColor(final Color theInner) {
        myInnerColor = theInner;
    }

    /**
     * Paint the unit using the given graphics on the given coordinate.
     * 
     * @param theGraphics The graphics used to paint the unit.
     * @param theX The x-coordinate.
     * @param theY The y-coordinate.
     * @param theSubSize Sub size of each small unit in the whole unit.
     */
    public void paintUnit(final Graphics2D theGraphics, final int theX, final int theY, final int theSubSize) {
        int y = theY;
        for (int i = 0; i < DESIGN.length; i++) {
            int x = theX;
            for (int j = 0; j < DESIGN[0].length; j++) {
                Color color = DESIGN[i][j] == 0 ? myOuterColor : myInnerColor;
                theGraphics.setColor(color);
                theGraphics.fillRect(x, y, theSubSize, theSubSize);
                x += theSubSize;
            }
            y += theSubSize;
        }
    }
    
}
