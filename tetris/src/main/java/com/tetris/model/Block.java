package com.tetris.model;

/**
 * Interface that represents all types of blocks in tetris.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public interface Block {

    /**
     * Get the current shape of the block.
     * 
     * @return The current shape of the block.
     */
    public int[][] getCurrentShape();
    
    /**
     * Rotate the block clockwise by 90 degrees.
     */
    public void rotate();
    
}
