package com.tetris.pieces;

import java.awt.Color;

/**
 * The Z-Piece.
 * Overall color: yellow organge.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class ZPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
        },
        {
            {0, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
        },
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
        super(ALL_SHAPES, new Color(255, 213, 158), new Color(255, 154, 20), null);
    }
    
}