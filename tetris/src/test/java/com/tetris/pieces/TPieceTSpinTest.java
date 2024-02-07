package com.tetris.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link TPiece#isTSpin(PieceUnit[][])}.
 * Test whether the method can recognize different T-spins and other spins.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class TPieceTSpinTest {

    private static final PieceUnit WALL = new PieceUnit();

    private TPiece myPiece;

    private PieceUnit[][] mySurrounding;

    /**
     * Create a new T-Piece and empty surrounding before each test.
     */
    @Before
    public void setup() {
        myPiece = new TPiece();
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
        mySurrounding[0][0] = WALL;
        assertEquals(0, myPiece.isTSpin(mySurrounding));
        // Two units in surrounding
        mySurrounding[0][2] = WALL;
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
        mySurrounding[0][0] = WALL;
        mySurrounding[2][0] = WALL;
        mySurrounding[2][2] = WALL;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][2] = WALL;
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
        mySurrounding[0][0] = WALL;
        mySurrounding[0][2] = WALL;
        mySurrounding[2][0] = WALL;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][2] = null;
        mySurrounding[2][2] = WALL;
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
        mySurrounding[0][2] = WALL;
        mySurrounding[2][0] = WALL;
        mySurrounding[2][2] = WALL;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][0] = WALL;
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
        mySurrounding[0][0] = WALL;
        mySurrounding[2][0] = WALL;
        mySurrounding[2][2] = WALL;
        assertEquals(1, myPiece.isTSpin(mySurrounding));
        // Full T-spin
        mySurrounding[0][0] = null;
        mySurrounding[0][2] = WALL;
        assertEquals(2, myPiece.isTSpin(mySurrounding));
    }

}
