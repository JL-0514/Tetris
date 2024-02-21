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

    /** Number that represent mini T-spin. */
    public static final int MINI_T_SPIN = 1;

    /** Number that represent full T-spin. */
    public static final int FULL_T_SPIN = 2;

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        },
        {
            {0, 1, 0},
            {0, 1, 1},
            {0, 1, 0}
        },
        {
            {0, 0, 0},
            {1, 1, 1},
            {0, 1, 0}
        }, 
        {
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
        }
    };

    /**
     * Create a T-Piece.
     */
    public TPiece() {
        super(ALL_SHAPES, new Color(255, 194, 112), new Color(229, 131, 0), null);
    }

    /**
     * Determine whether or not a full T-spin or mini T-spin has been done.
     * Assume the rotation is done before this method is called.
     * Both full and mini T-spin have three surronding pieces at the corner.
     * In a full T-spin, the piece face two surrounding pieces after the rotation
     * while mini T-spin faces one.
     * 
     * @return 0 if not T-spin, 1 if mini T-spin, 2 if full T-spin.
     */
    public int isTSpin(final PieceUnit[][] theSurrounding) {
        int spin = 0;
        int count = 0;
        // Count how many units in the corner
        count += theSurrounding[0][0] != null ? 1 : 0;
        count += theSurrounding[0][2] != null ? 1 : 0;
        count += theSurrounding[2][0] != null ? 1 : 0;
        count += theSurrounding[2][2] != null ? 1 : 0;
        if (count > 2) {
            PieceUnit u1 = null, u2 = null;
            switch (super.getCurrentShapeIdx()) {
                case 0:
                    u1 = theSurrounding[0][0];
                    u2 = theSurrounding[0][2];
                    break;
                case 1:
                    u1 = theSurrounding[0][2];
                    u2 = theSurrounding[2][2];
                    break;
                case 2:
                    u1 = theSurrounding[2][2];
                    u2 = theSurrounding[2][0];
                    break;
                case 3:
                    u1 = theSurrounding[2][0];
                    u2 = theSurrounding[0][0];
                    break;
                default:
                    break;
            }
            // Full T-spin
            if (u1 != null && u2 != null) {
                spin = FULL_T_SPIN;
            // Mini T-spin
            } else if (u1 == null ^ u2 == null) {
                spin = MINI_T_SPIN;
            }
        }
        return spin;
    }
    
}
