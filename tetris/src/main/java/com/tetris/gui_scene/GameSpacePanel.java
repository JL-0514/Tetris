package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.tetris.model.Piece;
import com.tetris.model.PieceUnit;
import com.tetris.model.Setting;

/**
 * The panel that act as a 10x20 grid and display pieces.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class GameSpacePanel extends JPanel {

    /** The game scene. */
    private final GameScene myGameScene;

    /** The general setting of the game. */
    private final Setting mySetting;

    /**
     * Create a panel that display pieces.
     * 
     * @param thePanel The game scene.
     * @param theSetting The general setting of the game.
     */
    public GameSpacePanel(final GameScene thePanel, final Setting theSetting) {
        super();
        myGameScene = thePanel;
        mySetting = theSetting;
        setup();
    }

    /**
     * Set up the panel.
     */
    private void setup() {
        setPreferredSize(new Dimension(20 + 10 * PieceUnit.SIZE, 20 + 20 * PieceUnit.SIZE));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBorder(BorderFactory.createLineBorder(mySetting.getForeground(), 10));
        setBackground(mySetting.getBackground());
    }
    
    /**
     * {@inheritDoc}
     * Paint each piece unit on the panel.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (myGameScene.getCurrentPiece() == null) { return; }
        final Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_DEFAULT);
        final PieceUnit[][] units = myGameScene.getPieces();
        final Piece current = myGameScene.getCurrentPiece();
        
        // Paint placed pieces
        int y = 10 + 19 * PieceUnit.SIZE;
        for (int r = 19; r > -1; r--) {
            int x = 10;
            for (int c = 2; c < 12; c++) {
                if (units[r][c] != null) {
                    units[r][c].paintUnit(graphics, x, y);
                }
                x += PieceUnit.SIZE;
            }
            y -= PieceUnit.SIZE;
        }

        // Paint current piece
        y = 10 + myGameScene.getCurrentRow() * PieceUnit.SIZE;
        final int[][] shape = current.getCurrentShape();
        for (int r = shape.length - 1; r > -1 && y > 0; r--) {
            int x = 10 + (myGameScene.getCurrentColumn() + shape[0].length - 3) * PieceUnit.SIZE;
            for (int c = shape[0].length - 1; c > -1 && x > 0; c--) {
                if (shape[r][c] == 1) {
                    current.getUnit().paintUnit(graphics, x, y);
                }
                x -= PieceUnit.SIZE;
            }
            y -= PieceUnit.SIZE;
        }
    }
    
    
}
