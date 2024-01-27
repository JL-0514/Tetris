package com.tetris.gui_scene;

import javax.swing.JPanel;

import com.tetris.model.Setting;

public class GameScene extends JPanel{

    private final Setting mySetting;

    private final TetrisFrame myFrame;

    public GameScene(final Setting theSetting, final TetrisFrame theFrame) {
        super();
        mySetting = theSetting;
        myFrame = theFrame;
    }
    
}
