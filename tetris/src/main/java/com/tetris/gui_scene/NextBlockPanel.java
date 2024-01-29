package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.tetris.model.Setting;

/**
 * The panel that display next block.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class NextBlockPanel extends JPanel {

    /** The game scene. */
    private final JPanel myGameScene;

    /** The general setting of the game. */
    private final Setting mySetting;

    /**
     * Create a panel that display next block.
     * 
     * @param thePanel The game scene.
     * @param theSetting The general setting of the game.
     */
    public NextBlockPanel(final JPanel thePanel, final Setting theSetting) {
        super(new GridBagLayout());
        myGameScene = thePanel;
        mySetting = theSetting;
        setup();
    }

    /**
     * Set up the panel.
     */
    private void setup() {
        setPreferredSize(new Dimension(120, 120));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBorder(BorderFactory.createLineBorder(mySetting.getForeground(), 10));
        setBackground(mySetting.getBackground());
    }
    
}
