package com.tetris.model;

import java.awt.Color;

/**
 * The J-Piece.
 * Overall color: yellow orange.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class JPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 1}
        }, 
        {
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 0}
        },
        {
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
        },
        {
            {0, 1, 1},
            {0, 1, 0},
            {0, 1, 0}
        }
    };

    /**
     * Create a J-Piece.
     */
    public JPiece() {
        super(ALL_SHAPES, new Color(255, 213, 158), new Color(255, 154, 20));
    }
    
}
