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
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private final Setting mySetting;

    /** The main frame. */
    private final TetrisFrame myFrame;

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
        
        setupCategoryLabels(gbc);
        setupKeySelectButtons(gbc);
        setupBackgroundButtons(gbc);
        setupSoundSlider(gbc);
        setupBackButton(gbc);    
    }

    /**
     * Set up label for different categories of settings.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupCategoryLabels(final GridBagConstraints theGBC) {
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
        theGBC.gridy = 0;
        theGBC.gridx = 1;
        add(bgLabel, theGBC);

        // Label for sound setting
        final JLabel soundLabel = createLabel("SOUND", labelFont, labelBorder);
        theGBC.gridy = 0;
        theGBC.gridx = 2;
        add(soundLabel, theGBC);
    }

    /**
     * Set up buttons used to select keys.
     * 
     * @param theGBC The grid bag constraints for the layout of the setting scene.
     */
    private void setupKeySelectButtons(final GridBagConstraints theGBC) {
        theGBC.anchor = GridBagConstraints.WEST;
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
                 * Record that this key is waiting to be change.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    mySelectedButton = (CommonButton) e.getSource();
                    // TODO Implement the feature to replace keys.
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
        theGBC.gridheight = 10;
        theGBC.gridx = 2;
        theGBC.gridy = 2;
        theGBC.fill = GridBagConstraints.VERTICAL;
        final JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, mySetting.getVolume());
        slider.setBackground(mySetting.getBackground());
        slider.setForeground(mySetting.getForeground());
        slider.setFont( new Font("Helvetica", Font.BOLD , 15));
        slider.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 15));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
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
    
}
