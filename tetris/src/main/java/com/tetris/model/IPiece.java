package com.tetris.model;

import java.awt.Color;

/**
 * The I-Piece.
 * Overall color: blue green.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class IPiece extends AbstractPiece {

    /** All possible shapes of the piece. */
    private static final int[][][] ALL_SHAPES = {
        {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }, 
        {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        },
        {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
        },
        {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        }
    };

    /** The wall kick data that specify the number of rows and columns to move */
    private static final int[][][] WALL_KICK = {
        {{0, 0}, {0, -2}, {0, 1}, {1, -2}, {-2, 1}}, 
        {{0, 0}, {0, -1}, {0, 2}, {-2, -1}, {1, 2}}, 
        {{0, 0}, {0, 2}, {0, -1}, {-1, 2}, {2, -1}}, 
        {{0, 0}, {0, 1}, {0, -2}, {2, 1}, {-1, -2}}
    };

    /**
     * Create a I-Piece.
     */
    public IPiece() {
        super(ALL_SHAPES, new Color(16, 187, 229), new Color(7, 82, 100), WALL_KICK);
    }
    
}
