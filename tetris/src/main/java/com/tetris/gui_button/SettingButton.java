package com.tetris.gui_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tetris.gui_scene.TetrisFrame;
import com.tetris.model.Setting;

/**
 * Button used to open the setting scene.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SettingButton extends CommonButton {

    /** The main frame. */
    private TetrisFrame myFrame;

    /**
     * Create a settign button.
     * 
     * @param thSetting The general setting of the game.
     * @param thFrame The main frame.
     */
    public SettingButton(final Setting theSetting, final TetrisFrame theFrame) {
        super("SETTING", theSetting);
        myFrame = theFrame;
        setup();
    }

    /**
     * Set up the on click action for the setting button.
     */
    private void setup() {
        addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             * Open the setting scene when clicked.
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                myFrame.toScene("Setting");
            }
        });
    }
    
}
