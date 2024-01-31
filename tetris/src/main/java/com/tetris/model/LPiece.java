package com.tetris.model;

import java.awt.Color;

/**
 * The L-Piece.
 * Overall color: yellow orange.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class LPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 0, 0},
            {1, 1, 1},
            {1, 0, 0}
        }, 
        {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
        },
        {
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        },
        {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        }
    };

    /** The bright color used by the piece. */
    public static final Color BRIGHT_COLOR = new Color(255, 233, 204);

    /** The darck color used by the piece. */
    public static final Color DARK_COLOR = new Color(255, 174, 66);

    public LPiece() {
        super(ALL_SHAPES);
    }
    
}
