package com.tetris.model;

import java.awt.Color;

/**
 * The Z-Piece.
 * Overall color: blue green.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class ZPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 0, 0},
            {1, 1, 0},
            {0, 1, 1}
        }, 
        {
            {0, 1, 0},
            {1, 1, 0},
            {1, 0, 0}
        }
    };

    /**
     * Create a Z-Piece.
     */
    public ZPiece() {
        super(ALL_SHAPES, new Color(93, 214, 244), new Color(13, 152, 186));
    }
    
}