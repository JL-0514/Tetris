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

    /** The bright color used by the piece. */
    public static final Color BRIGHT_COLOR = new Color(50, 203, 241);

    /** The darck color used by the piece. */
    public static final Color DARK_COLOR = new Color(10, 117, 143);

    public SPiece() {
        super(ALL_SHAPES);
    }
    
}