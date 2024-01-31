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

    /** The bright color used by the piece. */
    public static final Color BRIGHT_COLOR = new Color(255, 213, 158);

    /** The darck color used by the piece. */
    public static final Color DARK_COLOR = new Color(255, 154, 20);

    public JPiece() {
        super(ALL_SHAPES);
    }
    
}
