package com.tetris.gui_scene;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import com.tetris.model.Setting;

/**
 * Property change listener for that listen to changes in setting.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SettingChangeListener implements PropertyChangeListener{

    /** The panel that listen to changes. */
    private JPanel myPanel;

    /** The general setting of the game. */
    private Setting mySetting;

    /**
     * Create a setting change listener.
     * 
     * @param thePanel The panel that listen to changes.
     * @param theSetting The general setting of the game.
     */
    public SettingChangeListener(final JPanel thePanel, final Setting theSetting) {
        super();
        myPanel = thePanel;
        mySetting = theSetting;
    }

    /**
     * {@inheritDoc}
     * Change the background and foreground color of the panel and all its components.
     */
    public void propertyChange(final PropertyChangeEvent e) {
        if (e.getPropertyName().equals("color")) {
            myPanel.setBackground(mySetting.getBackground());
            myPanel.setForeground(mySetting.getForeground());
            for (Component c : myPanel.getComponents()) {
                c.setForeground(mySetting.getForeground());
                c.setBackground(mySetting.getBackground());
            }
        }
    }
    
}
