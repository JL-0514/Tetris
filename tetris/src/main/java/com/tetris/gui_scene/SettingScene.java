package com.tetris.gui_scene;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tetris.gui_button.CommonButton;
import com.tetris.model.Setting;

/**
 * The scene used to change setting of the game.
 * Containing the settign for keys, background color, and sound.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SettingScene extends JPanel {

    /** The previous scene displayed before the setting scene. */
    private String myPrevScene;

    /** The general setting of the game. */
    private final Setting mySetting;

    /** The main frame. */
    private final TetrisFrame myFrame;

    /** The button corrsponding to the operation whose key will be replaced. */
    private CommonButton mySelectedButton;

    /** All buttons used to replace keys for their correspondin operations. */
    private Map<JButton, String> myKeySelectButtons;

    /** Hint for key selection. */
    private JLabel myHint;

    /**
     * Create a setting scene.
     * 
     * @param theSetting The general setting of the game.
     * @param theFrame The main frame.
     */
    public SettingScene(final Setting theSetting, final TetrisFrame theFrame) {
        super(new GridBagLayout());
        myPrevScene = "Start";
        mySetting = theSetting;
        myFrame = theFrame;
        myKeySelectButtons = null;
        myKeySelectButtons = new HashMap<>();
        myHint = createLabel("", new Font("Helvetica", Font.BOLD , 15), 
                             BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setup();
    }

    /**
     * Set up the setting scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setForeground(mySetting.getForeground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));
        myHint.setVisible(false);
        myHint.setForeground(mySetting.getForeground());
        
        GridBagConstraints gbc = new GridBagConstraints();
        setupLabels(gbc);
        setupKeySelectButtons(gbc);
        setupBackgroundButtons(gbc);
        setupSoundSlider(gbc);
        setupBackButton(gbc);

        addKeyListener(new SettingKeyAdapter());
    }

    /**
     * Set up label for different categories of settings.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupLabels(final GridBagConstraints theGBC) {
        Font labelFont = new Font("Helvetica", Font.BOLD , 25);
        Border labelBorder = BorderFactory.createEmptyBorder(25, 0, 25, 0);

        // Label for keyboard setting
        final JLabel keyLabel = createLabel("KEYBOARD", labelFont, labelBorder);
        theGBC.insets = new Insets(0, 20, 0, 20);
        theGBC.gridx = 0;
        theGBC.gridy = 0;
        add(keyLabel, theGBC);

        // Label for background setting
        final JLabel bgLabel = createLabel("BACKGROUND", labelFont, labelBorder);
        theGBC.gridx++;
        add(bgLabel, theGBC);

        // Label for sound setting
        final JLabel soundLabel = createLabel("SOUND", labelFont, labelBorder);
        theGBC.gridx++;
        add(soundLabel, theGBC);

        // Label for hint to the user
        theGBC.gridx = 0;
        theGBC.gridy = 13;
        theGBC.gridwidth = 3;
        add(myHint, theGBC);
    }

    /**
     * Set up buttons used to select keys.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupKeySelectButtons(final GridBagConstraints theGBC) {
        theGBC.anchor = GridBagConstraints.WEST;
        theGBC.gridwidth = 1;
        theGBC.gridx = 0;
        theGBC.gridy = 0;
        Font labelFont = new Font("Helvetica", Font.BOLD , 15);
        Border labelBorder = BorderFactory.createEmptyBorder(10, 0, 2, 0);
        for (String s : mySetting.getAllOperations()) {
            // Label for the button
            final JLabel btnLabel = createLabel(s, labelFont, labelBorder);
            theGBC.gridy++;
            add(btnLabel, theGBC);
            // The button
            final String btnText = mySetting.getKey(s) == KeyEvent.VK_SPACE ? 
                                   "SPACE" : Character.toString((char) mySetting.getKey(s));
            final CommonButton btn = new CommonButton(btnText, mySetting);
            myKeySelectButtons.put(btn, s);
            btn.addActionListener(new ActionListener() {
                /**
                 * {@inheritDoc}
                 * Diable all buttons except the selected one and display the hint,
                 * or enable buttons and cancel the replacement of the key.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Select a key to replace
                    if (mySelectedButton == null) {
                        mySelectedButton = (CommonButton) e.getSource();
                        for (Component c : getComponents()) {
                            if (!(c instanceof JLabel)) {
                                c.setEnabled(false);
                            }
                        }
                        mySelectedButton.setEnabled(true);
                        myHint.setText("Press a key to replace or press the button again to cancel");
                        myHint.setVisible(true);
                        requestFocusInWindow();
                    // Cancel the replacement
                    } else {
                        for (Component c : getComponents()) {
                            c.setEnabled(true);
                        }
                        mySelectedButton = null;
                        myHint.setVisible(false);
                    }
                }
                
            });
            theGBC.gridy++;
            add(btn, theGBC);
        }
    }

    /**
     * Set up buttons used to select background color.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupBackgroundButtons(final GridBagConstraints theGBC) {
        theGBC.gridx = 1;
        theGBC.gridy = 2;
        theGBC.anchor = GridBagConstraints.NORTH;
        theGBC.gridheight = 2;
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
            add(btn, theGBC);
            theGBC.gridy += 2;
        }
    }

    /**
     * Set up the slider used to adjust the volume of the sound.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupSoundSlider(final GridBagConstraints theGBC) {
        // Labels for value of the slider
        final Font font = new Font("Helvetica", Font.BOLD , 15);
        theGBC.gridheight = 1;
        theGBC.anchor = GridBagConstraints.SOUTH;
        theGBC.gridx = 2;
        theGBC.gridy = 1;
        JLabel label = new JLabel("100");
        label.setFont(font);
        label.setForeground(mySetting.getForeground());
        add(label, theGBC);
        theGBC.anchor = GridBagConstraints.NORTH;
        theGBC.gridy = 12;
        label = new JLabel("0");
        label.setFont(font);
        label.setForeground(mySetting.getForeground());
        add(label, theGBC);
        // Slider
        theGBC.gridheight = 10;
        theGBC.gridy = 2;
        theGBC.fill = GridBagConstraints.VERTICAL;
        final JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, mySetting.getVolume());
        slider.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        slider.setBackground(mySetting.getBackground());
        slider.setForeground(mySetting.getForeground());
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setFocusable(false);
        slider.setUI(new SliderUI(slider));
        slider.addChangeListener(new ChangeListener() {
            /**
             * {@inheritDoc}
             * Adjust sound based on the value of the slider.
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                mySetting.setVolume(slider.getValue());
            }
            
        });
        add(slider, theGBC);
    }

    /**
     * Set up button used to go back to the previous scene.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupBackButton(final GridBagConstraints theGBC) {
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
        theGBC.gridx = 3;
        theGBC.gridy = 13;
        theGBC.gridheight = 1;
        theGBC.anchor = GridBagConstraints.EAST;
        theGBC.fill = GridBagConstraints.NONE;
        add(backBtn, theGBC);
    }

    /**
     * Create a label for a category of setting.
     * 
     * @param theLabel The text for the label.
     * @param theFont The font for the label.
     * @param theBorder The border of the label.
     * @return The label created.
     */
    private JLabel createLabel(final String theLabel, final Font theFont, final Border theBorder) {
        final JLabel label = new JLabel(theLabel);
        label.setBorder(theBorder);
        label.setFont(theFont);
        label.setForeground(mySetting.getForeground());
        label.setHorizontalAlignment(JLabel.CENTER);
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

    /**
     * Key adapter for the setting scene.
     */
    private class SettingKeyAdapter extends KeyAdapter {
        /**
         * {@inheritDoc}
         * Replace the corresponding key of the selcted operation with the key pressed,
         * if an operation is selected.
         */
        @Override
        public void keyPressed(final KeyEvent e) {
            if (mySelectedButton != null) {
                final int newKey = e.getKeyCode();
                final String operation = myKeySelectButtons.get(mySelectedButton);
                // A valid key is pressed
                if (!mySetting.getAllKeys().contains(newKey) || newKey == mySetting.getKey(operation)) {
                    mySetting.setKey(operation, newKey);
                    mySelectedButton.setText(Character.toString((char) newKey));
                    for (Component c : getComponents()) {
                        c.setEnabled(true);
                    }
                    mySelectedButton = null;
                    myHint.setVisible(false);
                // The key is used for another operation
                } else {
                    myHint.setText("The same key cannot be used twice");
                    myHint.setVisible(true);
                }
            }
        }
    }
    
}
