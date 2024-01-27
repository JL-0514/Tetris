package com.tetris.gui_scene;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

    /** The button corrsponding to the operation whose key will be replaced. */
    private CommonButton mySelectedButton;

    /** All buttons used to replace keys for their correspondin operations. */
    private Map<JButton, String> myKeySelectButtons;

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
        myKeySelectButtons = null;
        myKeySelectButtons = new HashMap<>();
        setup();
    }

    /**
     * Set up the setting scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setForeground(mySetting.getForeground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2, 10);
        final Font labelFont = new Font("Helvetica", Font.BOLD , 25);

        // Label for keyboard setting
        final JLabel keyLabel = createLabel("KEYBOARD", labelFont);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(keyLabel, gbc);

        // Buttons used to select keys
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        for (String s : mySetting.getAllOperations()) {
            // Label for the button
            final JLabel btnLabel = createLabel(s, CommonButton.TEXT_FONT);
            gbc.gridx = 0;
            gbc.gridy++;
            add(btnLabel, gbc);
            // The button
            final String btnText = mySetting.getKey(s) == KeyEvent.VK_SPACE ? 
                                   "SPACE" : Character.toString((char) mySetting.getKey(s));
            final CommonButton btn = new CommonButton(btnText, mySetting);
            myKeySelectButtons.put(btn, s);
            btn.addActionListener(new ActionListener() {
                /**
                 * {@inheritDoc}
                 * Record that this key is waiting to be change.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    mySelectedButton = (CommonButton) e.getSource();
                }
                
            });
            gbc.gridx = 1;
            add(btn, gbc);
        }
        
        // Label for background setting
        final JLabel bgLabel = createLabel("BACKGROUND", labelFont);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.gridx = 2;
        add(bgLabel, gbc);

        // Buttons used to change background color
        for (String s : mySetting.getAvailableColors()) {
            final CommonButton btn = new CommonButton(s, mySetting);
            btn.addActionListener(new ActionListener() {
                /**
                 * {@inheritDoc}
                 * Change background color when clicked.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    mySetting.setColor(((CommonButton) e.getSource()).getText());
                }
            });
            gbc.gridy++;
            add(btn, gbc);
        }

        // Label for background setting
        final JLabel soundLabel = createLabel("SOUND", labelFont);
        gbc.gridy = 0;
        gbc.gridx = 3;
        add(soundLabel, gbc);

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
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(70, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
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
        label.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 20));
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
