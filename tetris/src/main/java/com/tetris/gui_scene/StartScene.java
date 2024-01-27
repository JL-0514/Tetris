package com.tetris.gui_scene;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tetris.gui_button.CommonButton;
import com.tetris.gui_button.ExitButton;
import com.tetris.gui_button.SettingButton;
import com.tetris.model.Setting;

/**
 * The start scene of the game.
 * Contains the game title, play button, setting button, and exit button.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class StartScene extends JPanel {

    /** The general setting of the game. */
    private Setting mySetting;

    /** The main frame. */
    private TetrisFrame myFrame;

    /**
     * Create a start scene.
     * 
     * @param thSetting The general setting of the game.
     * @param theFrame The main frame.
     */
    public StartScene(final Setting thSetting, final TetrisFrame theFrame) {
        super();
        mySetting = thSetting;
        myFrame = theFrame;
        setup();
    }

    /**
     * Set up the start scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setForeground(mySetting.getForeground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        // Title
        final JLabel title = new JLabel("Tetris", JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        title.setFont(new Font("Algerian", Font.BOLD, 50));
        title.setForeground(mySetting.getForeground());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        // Play button
        final CommonButton playBtn = new CommonButton("PLAY", mySetting);
        playBtn.addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             * Open the game scene when clicked.
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                myFrame.toScene("Game");
            }
        });
        gbc.gridy++;
        add(playBtn, gbc);

        // Setting button
        final SettingButton settingBtn = new SettingButton(mySetting, myFrame);
        gbc.gridy++;
        add(settingBtn, gbc);

        // Exit button
        final ExitButton exitBtn = new ExitButton(mySetting);
        gbc.gridy++;
        add(exitBtn, gbc);
    }
    
}
