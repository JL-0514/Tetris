package com.tetris.model;

import java.awt.Color;

/**
 * The I-Block.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class IBlock extends AbstractBlock {

    /** The shape of block after rotated zero degree. */
    private static final int[][] ZERO = {
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {1, 1, 1, 1},
        {0, 0, 0, 0}
    };
    
    /** The shape of block after rotated ninety degrees clockwise. */
    private static final int[][] NINETY = {
        {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 1, 0}
    };

    /** All possible shapes of the block. */
    private static final int[][][] ALL_SHAPES = {ZERO, NINETY};

    /** The bright color used by the block. */
    public static final Color BRIGHT_COLOR = new Color(42, 42, 187);

    /** The darck color used by the block. */
    public static final Color DARK_COLOR = new Color(25, 25, 112);

    public IBlock() {
        super(ALL_SHAPES);
    }
    
}
