package com.tetris.pieces;

import java.awt.Color;

/**
 * The O-Piece.
 * Overall color: kelly green.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class OPiece extends AbstractPiece {
    
    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        }
    };

    /** The wall kick data that specify the number of rows and columns to move */
    private static final int[][][] WALL_KICK = {
        {{0, 0}}, 
        {{0, 0}}, 
        {{0, 0}}, 
        {{0, 0}}
    };

    /**
     * Create a O-Piece.
     */
    public OPiece() {
        super(ALL_SHAPES, new Color(122, 232, 70), new Color(43, 105, 13), WALL_KICK);
    }   

}
