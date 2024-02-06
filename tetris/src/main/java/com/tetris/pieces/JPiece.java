package com.tetris.pieces;

import java.awt.Color;

/**
 * The J-Piece.
 * Overall color: blue green.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class JPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
        },
        {
            {0, 1, 1},
            {0, 1, 0},
            {0, 1, 0}
        },
        {
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 1}
        }, 
        {
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 0}
        }
    };

    /**
     * Create a J-Piece.
     */
    public JPiece() {
        super(ALL_SHAPES, new Color(93, 214, 244), new Color(13, 152, 186), null);
    }
    
}
