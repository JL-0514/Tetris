package com.tetris.gui_scene;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.tetris.gui_button.CommonButton;
import com.tetris.gui_button.ExitButton;
import com.tetris.gui_button.SettingButton;
import com.tetris.model.Setting;

/**
 * The scene that shows up when the game is being paused.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class PauseScene extends JPanel {

    /** The general setting of the game. */
    private final Setting mySetting;

    /** The main frame. */
    private final TetrisFrame myFrame;

    /**
     * Create a pause scene.
     * 
     * @param theSetting The general setting of the game.
     * @param theFrame The main frame.
     */
    public PauseScene(final Setting theSetting, final TetrisFrame theFrame) {
        super(new GridBagLayout());
        mySetting = theSetting;
        myFrame = theFrame;
        setup();
    }

    /**
     * Set up the pause scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        // Resume button
        gbc.gridx = 0;
        gbc.gridy = 0;
        final CommonButton resume = new CommonButton("RESUME", mySetting);
        resume.addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             * Resume to the game.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.toScene("Game");
            }
            
        });
        add(resume, gbc);

        // Setting button
        gbc.gridy++;
        add(new SettingButton(mySetting, myFrame), gbc);

        // Exit button
        gbc.gridy++;
        add(new ExitButton(mySetting), gbc);
    }
    
}
