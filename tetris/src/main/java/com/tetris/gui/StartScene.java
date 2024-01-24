package com.tetris.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tetris.model.Setting;

public class StartScene extends JPanel {

    private Setting mySetting;

    public StartScene(final Setting thSetting) {
        super();
        mySetting = thSetting;
        setup();
    }

    private void setup() {
        setBackground(mySetting.getBackground());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
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
        gbc.gridy = 1;
        add(playBtn, gbc);

        // Setting button
        final CommonButton settingBtn = new CommonButton("SETTING", mySetting);
        gbc.gridy = 2;
        add(settingBtn, gbc);

        // Exit button
        final ExitButton exitBtn = new ExitButton(mySetting);
        gbc.gridy = 3;
        add(exitBtn, gbc);
    }
    
}
