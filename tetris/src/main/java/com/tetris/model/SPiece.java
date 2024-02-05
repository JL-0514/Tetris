package com.tetris.model;

import java.awt.Color;

/**
 * The S-Piece.
 * Overall color: blue green.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
        },
        {
            {0, 1, 0},
            {0, 1, 1},
            {0, 0, 1}
        }, 
        {
            {0, 0, 0},
            {0, 1, 1},
            {1, 1, 0}
        },
        {
            {1, 0, 0},
            {1, 1, 0},
            {0, 1, 0}
        }
    };

    /**
     * Create a S-Piece.
     */
    public SPiece() {
        super(ALL_SHAPES, new Color(50, 203, 241), new Color(10, 117, 143), null);
    }
    
}