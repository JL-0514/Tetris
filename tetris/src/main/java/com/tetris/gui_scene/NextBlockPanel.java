package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.tetris.model.GameSpace;
import com.tetris.model.Setting;
import com.tetris.pieces.Piece;

/**
 * The panel that display next block.
 * The panel has size 144x144 and each piece unit in the panel is 24 width.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class NextBlockPanel extends JPanel {

     /** The game space. */
    private final GameSpace myGameSpace;

    /** The general setting of the game. */
    private final Setting mySetting;

    /**
     * Create a panel that display next block.
     * 
     * @param theSpace The game space.
     * @param theSetting The general setting of the game.
     */
    public NextBlockPanel(final GameSpace theSpace, final Setting theSetting) {
        super(new GridBagLayout());
        myGameSpace = theSpace;
        mySetting = theSetting;
        setup();
    }

    /**
     * Set up the panel.
     */
    private void setup() {
        setPreferredSize(new Dimension(144, 144));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBorder(BorderFactory.createLineBorder(mySetting.getForeground(), 10));
        setBackground(mySetting.getBackground());
    }

    /**
     * {@inheritDoc}
     * Draw the next shape on the panel.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (myGameSpace.getNextPiece() == null) { return; }
        Graphics2D graphics = (Graphics2D) g;
        final Piece myNext = myGameSpace.getNextPiece();
        final int[][] shape = myNext.getNextShape();
        int start = 144 / 2 - shape.length * 24 / 2;
        int y = start;
        graphics.setColor(myNext.getUnit().getOuterColor());
        for (int r = 0; r < shape.length; r++) {
            int x = start;
            for (int c = 0; c < shape.length; c++) {
                if (shape[r][c] == 1) {
                    myNext.getUnit().paintUnit(graphics, x, y, 3);
                }
                x += 24;
            }
            y += 24;
        }
    }
    
}
