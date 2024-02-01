package com.tetris.gui_scene;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.tetris.model.Setting;

/**
 * Property change listener for that listen to changes in setting.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SettingChangeListener implements PropertyChangeListener{

    /** The panel that listen to changes. */
    private final JPanel myPanel;

    /** The general setting of the game. */
    private final Setting mySetting;

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
        if (e.getPropertyName().equals("color") && e.getOldValue() != e.getNewValue()) {
            myPanel.setBackground(mySetting.getBackground());
            myPanel.setForeground(mySetting.getForeground());
            for (Component c : myPanel.getComponents()) {
                c.setForeground(mySetting.getForeground());
                c.setBackground(mySetting.getBackground());
                if (c instanceof JComponent) {
                    final Border b = ((JComponent) c).getBorder();
                    if (b != null && b instanceof LineBorder) {
                        ((JComponent) c).setBorder(BorderFactory.createLineBorder(mySetting.getForeground(), 
                                                   ((LineBorder) b).getThickness()));
                    }
                }
            }
        }
    }
    
}
