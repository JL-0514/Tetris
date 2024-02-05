package com.tetris.model;

import java.awt.Color;

import com.tetris.gui_scene.GameScene;

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
     * Determine whether or not a funn T-spin or mini T-spin has been done.
     * Assume the rotation is done before this method is called.
     * 
     * @return 0 if not T-spin, 1 if mini T-spin, 2 if full T-spin.
     */
    public int isTSpin(final GameScene theScene) {
        int spin = 0;
        final PieceUnit[][] surrounding = theScene.getSurrounding(0, 0);
        PieceUnit u1 = null, u2 = null;
        switch (super.getCurrentShapeIdx()) {
            case 0:
                u1 = surrounding[0][0];
                u2 = surrounding[0][2];
                break;
            case 1:
                u1 = surrounding[0][2];
                u2 = surrounding[2][2];
                break;
            case 2:
                u1 = surrounding[2][2];
                u2 = surrounding[2][0];
                break;
            case 3:
                u1 = surrounding[2][0];
                u2 = surrounding[0][0];
                break;
            default:
                break;
        }
        // Full T-spin
        if (u1 != null&& u2 != null) {
            spin = 2;
        // Mini T-spin
        } else if (u1 == null ^ u2 == null) {
            spin = 1;
        }
        return spin;
    }
    
}
