package com.tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tetris.model.Setting;

/**
 * Buttons in the game. Mainly used to create uniform look for buttons.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class CommonButton extends JButton {

    /** Font of the text in the button. */
    private static final Font TEXT_FONT = new Font("Helvetica", Font.BOLD , 20);

    /** Size of the button. */
    private static final Dimension BUTTON_SIZE = new Dimension(230, 50);

    /** Setting applies to the button. */
    private Setting mySetting;

    /**
     * Create a button.
     * 
     * @param theText The text in the button.
     * @param thSetting The setting applies to the button.
     */
    public CommonButton(final String theText, final Setting thSetting) {
        super(theText);
        mySetting = thSetting;
        setup();
    }

    /**
     * Set up the button by adjusting its look.
     */
    private void setup() {
        setFont(TEXT_FONT);
        setForeground(mySetting.getForeground());
        setBackground(mySetting.getBackground());
        setFocusPainted(false);
        setPreferredSize(BUTTON_SIZE);
        setBorder(BorderFactory.createLineBorder(mySetting.getForeground(), 10));
        
        // Set hovered and pressed background color changes
        setContentAreaFilled(false);
        setOpaque(true);
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                if (getModel().isPressed()) {
                    setBackground(Color.DARK_GRAY);
                } else if (getModel().isRollover()) {
                    setBackground(Color.GRAY);
                } else {
                    setBackground(mySetting.getBackground());
                }
            }
        });
    }
    
}
