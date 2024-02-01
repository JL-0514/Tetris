package com.tetris.model;

import java.awt.Color;

/**
 * The T-Piece.
 * Overall color: yellow orange.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class TPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 0, 0},
            {1, 1, 1},
            {0, 1, 0}
        }, 
        {
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
        },
        {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        },
        {
            {0, 1, 0},
            {0, 1, 1},
            {0, 1, 0}
        }
    };

    /**
     * Create a T-Piece.
     */
    public TPiece() {
        super(ALL_SHAPES, new Color(255, 194, 112), new Color(229, 131, 0));
    }
    
}
