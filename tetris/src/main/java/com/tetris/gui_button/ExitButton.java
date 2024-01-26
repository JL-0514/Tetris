package com.tetris.gui_button;

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
     * 
     * @param theSetting The general setting of the game.
     */
    public ExitButton(final Setting theSetting) {
        super("EXIT", theSetting);
        setup();
    }

    /**
     * Set up the on click action for the exit button.
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
