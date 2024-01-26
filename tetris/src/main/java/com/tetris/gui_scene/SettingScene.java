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
import com.tetris.model.Setting;

/**
 * The scene used to change setting of the game.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SettingScene extends JPanel {

    /** The previous scene displayed before the setting scene. */
    private String myPrevScene;

    /** The general setting of the game. */
    private Setting mySetting;

    /** The main frame. */
    private TetrisFrame myFrame;

    /**
     * Create a setting scene.
     * 
     * @param theSetting The general setting of the game.
     * @param theFrame The main frame.
     */
    public SettingScene(final Setting theSetting, final TetrisFrame theFrame) {
        super();
        myPrevScene = "Start";
        mySetting = theSetting;
        myFrame = theFrame;
        setup();
    }

    /**
     * Set up the setting scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        Font labelFont = new Font("Algerian", Font.BOLD, 20);

        // Label for keyboard setting
        final JLabel keyLabel = createLabel("KEYBOARD", labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(keyLabel, gbc);

        // Back button
        final CommonButton backBtn = new CommonButton("BACK", mySetting);
        backBtn.addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             * Go back to previous scene when clicked.
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                myFrame.toScene(myPrevScene);
            }
        });
        gbc.gridy++;
        add(backBtn, gbc);
    }

    /**
     * Create a label for a category of setting.
     * 
     * @param theLabel The text for the label.
     * @param theFont The font for the label.
     * @return The label created.
     */
    private JLabel createLabel(final String theLabel, final Font theFont) {
        final JLabel label = new JLabel(theLabel);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        label.setFont(theFont);
        label.setForeground(mySetting.getForeground());
        return label;
    }

    /**
     * Set previous scene. Only called when switching to setting scene.
     * 
     * @param theScene The previous scene.
     */
    public void setPrevScene(final String theScene) {
        myPrevScene = theScene;
    }
    
}
