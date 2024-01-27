package com.tetris.gui_scene;

import javax.swing.JPanel;

import com.tetris.model.Setting;

public class PauseScene extends JPanel {

    private Setting mySetting;

    private TetrisFrame myFrame;

    public PauseScene(final Setting theSetting, final TetrisFrame theFrame) {
        super();
        mySetting = theSetting;
        myFrame = theFrame;
    }
    
}
