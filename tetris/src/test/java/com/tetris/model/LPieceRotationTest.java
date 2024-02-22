package com.tetris.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for L-piece {@link LPiece}.
 * Test whether the L-piece perform regular rotation and wall kick correctly.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class LPieceRotationTest {

     /** The placeholder used to create scenarios. */
    private final PieceUnit temp = TestHelper.TEMP;

    /** The L-Piece used to test. */
    private final LPiece myPiece = new LPiece();

    /** The game space used to create scenarios. */
    private final GameSpace myGameSpace = new GameSpace(new Setting());

    /**
     * Start a new game space before each test.
     */
    @Before
    public void setup() {
        myGameSpace.clear();
        myGameSpace.newGame();
        myGameSpace.setCurrentPiece(myPiece);
    }

    /**
     * Test for {@link LPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 0 to 1 
     * and all wall kicks currectly.
     */
    @Test
    public void testZeroToOneRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 1 to 2 
     * and all wall kicks currectly.
     */
    @Test
    public void testOneToTwoRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 2 to 3 
     * and all wall kicks currectly.
     */
    @Test
    public void testTwoToThreeRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 3 to 0 
     * and all wall kicks currectly.
     */
    @Test
    public void testThreeToZeroRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 0 to 3 
     * and all wall kicks currectly.
     */
    @Test
    public void testZeroToThreeRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 3 to 2 
     * and all wall kicks currectly.
     */
    @Test
    public void testThreeToTwoRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 2 to 1 
     * and all wall kicks currectly.
     */
    @Test
    public void testTwoToOneRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 1 to 0 
     * and all wall kicks currectly.
     */
    @Test
    public void testOneToZeroRotate() {
        // Test 0
        // Test 1
        // Test 2
        // Test 3
        // Test 4
    }

    /**
     * Test for {@link LPiece#isValidRotate(PieceUnit[][], int)}.
     * Test whether this method recognize valid and invlaid rotate.
     */
    @Test
    public void testIsValidRotate() {
        // Valid rotate
        PieceUnit[][] surrounding = new PieceUnit[][]{
            {temp, temp, null},
            {null, null, null},
            {temp, temp, temp}
        };
        assertTrue(myPiece.isValidRotate(surrounding, 0));
        // Invalid rotate
        surrounding = new PieceUnit[][]{
            {temp, null, temp},
            {temp, null, temp},
            {temp, null, null}
        };
        assertFalse(myPiece.isValidRotate(surrounding, 0));
    }
}
