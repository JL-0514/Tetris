package com.tetris.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tetris.model.Setting;

/**
 * Button used to exit the game.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class ExitButton extends CommonButton {

    /**
     * Create an exit button.
     */
    public ExitButton(final Setting thSetting) {
        super("EXIT", thSetting);
        setup();
    }

    /**
     * Set up the exit button by adding sction listener.
     */
    private void setup() {
        addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             * Close the program when exit button is clicked.
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
}
