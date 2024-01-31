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
     * Get the next random shape of the block.
     * 
     * @return The next random shape of the block.
     */
    public int[][] getNextShape();

    /**
     * Replace the current shape with the next random shape
     * and set a new next random shape.
     */
    public void setRandomShape();
    
    /**
     * Rotate the block clockwise by 90 degrees.
     * 
     * @param theSurrounding The area the block may touch as it rotate.
     */
    public void rotateClockwise(BlockUnit[][] theSurrounding);

    /**
     * Rotate the block counterclockwise by 90 degrees.
     * 
     * @param theSurrounding The area the block may touch as it rotate.
     */
    public void rotateCounterclockwise(BlockUnit[][] theSurrounding);

    /**
     * Check whether the block can be rotated in the given surrounding.
     * 
     * @param theSurrounding The area the block may touch as it rotate.
     * @param theTarget The index of the shape the block is about to rotate to.
     * @return Whether the block can be rotated.
     */
    public boolean isValidRotate(BlockUnit[][] theSurrounding, int theTarget);
    
}
