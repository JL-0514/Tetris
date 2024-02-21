package com.tetris.model;

/**
 * A helper class that contains methods and resources used to test model classes.
 */
public class TestHelper {
    
    /** Temporary placeholder in surrounding. */
    protected static final PieceUnit TEMP = new PieceUnit();

    /**
     * Set up the surroundind of the testing piece by copying placeholders to their position.
     * 
     * @param theSpace The game space.
     * @param theSurrounding The surrounding used for testing.
     */
    protected static void copySurrounding(final PieceUnit[][] theSpace, final PieceUnit[][] theSurrounding) {
        int r = 19 - theSurrounding.length + 1;
        for (int i = 0; i < theSurrounding.length; i++, r++) {
            int c = 2;
            for (int j = 0; j < theSurrounding[0].length; j++, c++) {
                theSpace[r][c] = theSurrounding[i][j];
            }
        }
    }

}
