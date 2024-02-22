package com.tetris.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for J-piece {@link JPiece}.
 * Test whether the J-piece perform regular rotation and wall kick correctly.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class JPieceRotationTest {

    /** The placeholder used to create scenarios. */
    private final PieceUnit temp = TestHelper.TEMP;

    /** The J-Piece used to test. */
    private final JPiece myPiece = new JPiece();

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
     * Test for {@link JPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 0 to 1 
     * and all wall kicks currectly.
     */
    @Test
    public void testZeroToOneRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(0);
        myGameSpace.dropCurrent(17);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, -1}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(-1, move[1]);

        // Test 2 {-1, -1}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(-1, move[0]);
        assertEquals(-1, move[1]);

        // Test 3 {2, 0}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, null, null, null, temp, temp, temp, temp, temp, temp},
            {temp, null, null, null, temp, temp, temp, temp, temp, temp},
            {temp, null, null, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {2, -1}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, null, null, null, temp, temp, temp, temp, temp, temp},
            {temp, null, null, null, temp, temp, temp, temp, temp, temp},
            {temp, null, null, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(-1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 1 to 2 
     * and all wall kicks currectly.
     */
    @Test
    public void testOneToTwoRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(1);
        myGameSpace.dropCurrent(18);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, null, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, 1}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, temp, null, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(1, move[1]);

        // Test 2 {1, 1}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, null, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, null, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(1, move[0]);
        assertEquals(1, move[1]);

        // Test 3 {-2, 0}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {-2, 1}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, null, temp, temp, temp},
            {temp, temp, temp, temp, null, null, null, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 2 to 3 
     * and all wall kicks currectly.
     */
    @Test
    public void testTwoToThreeRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(2);
        myGameSpace.dropCurrent(17);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, 1}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(1, move[1]);

        // Test 2 {-1, 1}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(-1, move[0]);
        assertEquals(1, move[1]);

        // Test 3 {2, 0}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, temp, null, null, null, temp},
            {temp, temp, temp, null, null, null, null, null, null, temp},
            {temp, temp, temp, temp, null, null, null, null, null, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {2, 1}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, temp, null, null, null, temp},
            {temp, temp, temp, null, null, null, null, null, null, temp},
            {temp, temp, temp, temp, temp, null, null, null, null, temp},
            {temp, temp, temp, temp, temp, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateClockwise(GameSpace)}.
     * Test whether this method can perform clockwise rotation from position in index 3 to 0 
     * and all wall kicks currectly.
     */
    @Test
    public void testThreeToZeroRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(3);
        myGameSpace.dropCurrent(19);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, -1}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, null, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(-1, move[1]);

        // Test 2 {1, -1}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, null, temp, null, null, temp, temp, temp, temp},
            {temp, temp, null, null, null, null, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(1, move[0]);
        assertEquals(-1, move[1]);

        // Test 3 {-2, 0}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, null, null, null, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(0, move[1]);
        
        // Test 4 {-2, -1}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, null, temp, null, null, temp, temp, temp, temp},
            {temp, temp, null, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, null, null, null, temp, temp, temp, temp}
        });
        move = myPiece.rotateClockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(-1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 0 to 3 
     * and all wall kicks currectly.
     */
    @Test
    public void testZeroToThreeRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(0);
        myGameSpace.dropCurrent(17);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, 1}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(1, move[1]);

        // Test 2 {-1, 1}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(-1, move[0]);
        assertEquals(1, move[1]);

        // Test 3 {2, 0}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, null, null, null, temp, temp, temp, temp, temp, temp},
            {temp, null, null, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {2, 1}
        myPiece.setCurrentShapeIdx(0);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, null, null, null, temp, temp, temp, temp, temp, temp},
            {temp, null, null, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 3 to 2 
     * and all wall kicks currectly.
     */
    @Test
    public void testThreeToTwoRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(3);
        myGameSpace.dropCurrent(18);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, -1}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(-1, move[1]);

        // Test 2 {1, -1}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, null, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(1, move[0]);
        assertEquals(-1, move[1]);

        // Test 3 {-2, 0}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {-2, -1}
        myPiece.setCurrentShapeIdx(3);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(-1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 2 to 1 
     * and all wall kicks currectly.
     */
    @Test
    public void testTwoToOneRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(2);
        myGameSpace.dropCurrent(17);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, -1}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, null, temp, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(-1, move[1]);

        // Test 2 {-1, -1}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(-1, move[0]);
        assertEquals(-1, move[1]);

        // Test 3 {2, 0}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, temp, null, null, null, temp},
            {temp, temp, temp, null, null, null, null, null, null, temp},
            {temp, temp, temp, temp, null, null, null, null, null, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {2, -1}
        myPiece.setCurrentShapeIdx(2);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, temp, temp, null, null, null, temp},
            {temp, temp, temp, null, null, null, null, null, null, temp},
            {temp, temp, temp, null, null, null, null, null, null, temp},
            {temp, temp, temp, null, temp, temp, temp, temp, temp, temp},
            {temp, temp, temp, null, temp, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(2, move[0]);
        assertEquals(-1, move[1]);
    }

    /**
     * Test for {@link JPiece#rotateCounterclockwise(GameSpace)}.
     * Test whether this method can perform counterclockwise rotation from position in index 1 to 0 
     * and all wall kicks currectly.
     */
    @Test
    public void testOneToZeroRotate() {
        // Test 0 {0, 0}
        myPiece.setCurrentShapeIdx(1);
        myGameSpace.dropCurrent(19);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        int[] move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(0, move[1]);

        // Test 1 {0, 1}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, null, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(0, move[0]);
        assertEquals(1, move[1]);

        // Test 2 {1, 1}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, null, null, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(1, move[0]);
        assertEquals(1, move[1]);

        // Test 3 {-2, 0}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(0, move[1]);

        // Test 4 {-2, 1}
        myPiece.setCurrentShapeIdx(1);
        TestHelper.copySurrounding(myGameSpace.getPieces(), new PieceUnit[][]{
            {temp, temp, temp, temp, null, null, null, temp, temp, temp},
            {temp, temp, temp, temp, null, null, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp},
            {temp, temp, temp, temp, null, temp, temp, temp, temp, temp}
        });
        move = myPiece.rotateCounterclockwise(myGameSpace);
        assertEquals(-2, move[0]);
        assertEquals(1, move[1]);
    }

    /**
     * Test for {@link JPiece#isValidRotate(PieceUnit[][], int)}.
     * Test whether this method recognize valid and invlaid rotate.
     */
    @Test
    public void testIsValidRotate() {
        // Valid rotate
        PieceUnit[][] surrounding = new PieceUnit[][]{
            {null, temp, temp},
            {null, null, null},
            {temp, temp, temp}
        };
        assertTrue(myPiece.isValidRotate(surrounding, 0));
        // Invalid rotate
        surrounding = new PieceUnit[][]{
            {temp, null, null},
            {temp, null, temp},
            {temp, null, temp}
        };
        assertFalse(myPiece.isValidRotate(surrounding, 0));
    }
}
