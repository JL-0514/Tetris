package com.tetris.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for T-piece {@link TPiece#isTSpin(PieceUnit[][])}.
 * Test whether the method can recognize different T-spins and other spins.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class TPieceTSpinTest {

    /** The placeholder used to create scenarios. */
    private final PieceUnit temp = TestHelper.TEMP;

    /** The T-Piece used to test. */
    private final TPiece myPiece = new TPiece();

    /** The surrounding around the T-Piece. */
    private PieceUnit[][] mySurrounding;

    /**
     * Create a empty surrounding before each test.
     */
    @Before
    public void setup() {
        mySurrounding = new PieceUnit[3][3];
    }

    /**
     * Test for {@link TPiece#isTSpin(PieceUnit[][])}.
     * Test whether the method recognize spins that are not T-spin.
     */
    @Test
    public void testIsTSpinNot() {
        // No unit in surronding
        myPiece.setCurrentShapeIdx(0);
        assertEquals(0, myPiece.isTSpin(mySurrounding));
        // One unit in surrounding
        mySurrounding[0][0] = temp;
        assertEquals(0, myPiece.isTSpin(mySurrounding));
        // Two units in surrounding
        mySurrounding[0][2] = temp;
        assertEquals(0, myPiece.isTSpin(mySurrounding));
    }

    /**
     * Test for {@link TPiece#isTSpin(PieceUnit[][])}.
     * Test whether the method recognize full and mini T-spin while the piece is facing up after rotation.
     */
    @Test
    public void testIsTSpinlUp() {
        myPiece.setCurrentShapeIdx(0);
        // Mini T-spin
        mySurrounding[0][0] = temp;
        mySurrounding[2][0] = temp;
        mySurrounding[2][2] = temp;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][2] = temp;
        mySurrounding[2][0] = null;
        assertEquals(2, myPiece.isTSpin(mySurrounding));
    }

    /**
     * Test for {@link TPiece#isTSpin(PieceUnit[][])}.
     * Test whether the method recognize full and mini T-spin while the piece is facing down after rotation.
     */
    @Test
    public void testIsTSpinDown() {
        myPiece.setCurrentShapeIdx(2);
        // Mini T-spin
        mySurrounding[0][0] = temp;
        mySurrounding[0][2] = temp;
        mySurrounding[2][0] = temp;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][2] = null;
        mySurrounding[2][2] = temp;
        assertEquals(2, myPiece.isTSpin(mySurrounding));
    }

    /**
     * Test for {@link TPiece#isTSpin(PieceUnit[][])}.
     * Test whether the method recognize full and mini T-spin while the piece is facing left after rotation.
     */
    @Test
    public void testIsTSpinLeft() {
        myPiece.setCurrentShapeIdx(3);
        // Mini T-spin
        mySurrounding[0][2] = temp;
        mySurrounding[2][0] = temp;
        mySurrounding[2][2] = temp;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][0] = temp;
        mySurrounding[0][2] = null;
        assertEquals(2, myPiece.isTSpin(mySurrounding));
    }

    /**
     * Test for {@link TPiece#isTSpin(PieceUnit[][])}.
     * Test whether the method recognize full and mini T-spin while the piece is facing down after rotation.
     */
    @Test
    public void testIsTSpinRight() {
        myPiece.setCurrentShapeIdx(1);
        // Mini T-spin
        mySurrounding[0][0] = temp;
        mySurrounding[2][0] = temp;
        mySurrounding[2][2] = temp;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][0] = null;
        mySurrounding[0][2] = temp;
        assertEquals(2, myPiece.isTSpin(mySurrounding));
    }

}
