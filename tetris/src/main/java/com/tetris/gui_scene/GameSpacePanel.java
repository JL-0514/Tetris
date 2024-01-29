package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.tetris.model.BlockUnit;
import com.tetris.model.Setting;

/**
 * The panel that display blocks.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class GameSpacePanel extends JPanel {

    /** The game scene. */
    private final JPanel myGameScene;

    /** The general setting of the game. */
    private final Setting mySetting;

    /**
     * Create a panel that display blocks.
     * 
     * @param thePanel The game scene.
     * @param theSetting The general setting of the game.
     */
    public GameSpacePanel(final JPanel thePanel, final Setting theSetting) {
        super(new GridBagLayout());
        myGameScene = thePanel;
        mySetting = theSetting;
        setup();
    }

    /**
     * Set up the panel.
     */
    private void setup() {
        setPreferredSize(new Dimension(1 + 10 * (BlockUnit.SIZE + 1), 1 + 20 * (BlockUnit.SIZE + 1)));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBorder(BorderFactory.createLineBorder(mySetting.getForeground(), 15));
        setBackground(mySetting.getBackground());
    }
    
}
