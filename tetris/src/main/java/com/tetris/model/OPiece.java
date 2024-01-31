package com.tetris.model;

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

    /** The bright color used by the piece. */
    public static final Color BRIGHT_COLOR = new Color(122, 232, 70);

    /** The darck color used by the piece. */
    public static final Color DARK_COLOR = new Color(43, 105, 13);

    public OPiece() {
        super(ALL_SHAPES);
    }   

}
