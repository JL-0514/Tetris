package com.tetris.model;

import java.util.Random;

public class AbstractBlock implements Block {

    private static Random myRand = new Random();

    private int[][][] myAllShapes;

    private int myCurrentShape;

    protected AbstractBlock(final int[][][] theShapes) {
        super();
        myAllShapes = theShapes;
        myCurrentShape = myRand.nextInt(theShapes.length);
    }

    public int[][] getCurrentShape() {
        return myAllShapes[myCurrentShape].clone();
    }

    public void rotate() {
        myCurrentShape++;
        if (myCurrentShape == myAllShapes.length) {
            myCurrentShape = 0;
        }
    }
    
}
