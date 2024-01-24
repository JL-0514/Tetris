package com.tetris.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.tetris.model.Setting;

/**
 * The main frame of the game.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class TetrisFrame {

    /** The main frame. */
    private JFrame myFrame;

    private StartScene myStartScene;

    private GameScene myGameScene;

    private PauseScene mPauseScene;

    private Setting mySetting;

    /**
     * Initialize the frame and its components.
     */
    public TetrisFrame() {
        myFrame = new JFrame("Tetris");
        mySetting = new Setting();
        myStartScene = new StartScene(mySetting);
        myGameScene = new GameScene();
        mPauseScene = new PauseScene();
        setup();
    }

    /**
     * Set up the frame and its components.
     */
    private void setup() {
        myFrame.add(myStartScene, BorderLayout.CENTER);

        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setUndecorated(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
    
}
