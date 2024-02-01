package com.tetris.gui_scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.tetris.gui_button.CommonButton;
import com.tetris.model.Piece;
import com.tetris.model.PieceUnit;
import com.tetris.model.SPiece;
import com.tetris.model.IPiece;
import com.tetris.model.JPiece;
import com.tetris.model.LPiece;
import com.tetris.model.OPiece;
import com.tetris.model.ScoreCounter;
import com.tetris.model.Setting;
import com.tetris.model.TPiece;
import com.tetris.model.ZPiece;

/**
 * The game scene where the game is being played.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class GameScene extends JPanel{

    /** A block acts as a wall around the game space. */
    private final static PieceUnit WALL = new PieceUnit();

    /** Used to select a random piece. */
    private static Random myRand;

    /** All available blocks in the game. */
    private final Piece[] myAllPieces;

    /** The general setting of the game. */
    private final Setting mySetting;

    /** The main frame. */
    private final TetrisFrame myFrame;

    /** The panel that displays piecea. */
    private final GameSpacePanel myGameSpacePanel;

    /** The panel that displays next piece. */
    private final NextBlockPanel myNextBlockPanel;

    /** The button used to start or pause the game. */
    private final CommonButton myPlayBtn;

    /** The label for score. */
    private final JLabel myScore;

    /** The label for level. */
    private final JLabel myLevel;

    /** Timer used to move the piece in a cartain rate. */
    private Timer myTimer;

    /** The pieces placed in the game space. */
    private final PieceUnit[][] myPieces;

    /** The score counter. */
    private ScoreCounter myScoreCounter;
 
    /** The row where the bottom-left corner ofthe piece is placed. */
    private int myRow;

    /** The column where the bottom-left corner of the piece is placed. */
    private int myColumn;

    /** The current piece. */
    private Piece myCurrentPiece;

    /** The next piece. */
    private Piece myNextPiece;

    /**
     * Create a game scene.
     * 
     * @param theSetting The general setting of the game.
     * @param theFrame The main frame.
     */
    public GameScene(final Setting theSetting, final TetrisFrame theFrame) {
        super(new GridBagLayout());
        myAllPieces = new Piece[]{new IPiece(), new JPiece(), new LPiece(), new OPiece(), 
                                  new SPiece(), new TPiece(), new ZPiece()};
        mySetting = theSetting;
        myFrame = theFrame;
        myGameSpacePanel = new GameSpacePanel(this, theSetting);
        myNextBlockPanel = new NextBlockPanel(this, theSetting);
        myPlayBtn = new CommonButton("START", theSetting);
        myScore = new JLabel("0");
        myLevel = new JLabel("0");
        myPieces = new PieceUnit[21][13];
        myTimer = new Timer(ScoreCounter.INIT_SPEED, new DropBlockAction());
        myRand = new Random();
        myRow = 0;
        myColumn = 5;
        setup();
    }

    /**
     * Set up the game scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setForeground(mySetting.getForeground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));
        mySetting.addPropertyChangeListener(new PieceChangeListener());
        addKeyListener(new GameSceneKeyAdapter());

        // Fill the wall around the game space
        for (int i = 0; i < 20; i++) {
            myPieces[i][0] = WALL;
            myPieces[i][1] = WALL;
            myPieces[i][12] = WALL;
        }
        for (int i = 0; i < 13; i++) {
            myPieces[20][i] = WALL;
        } 

        Font labelFont = new Font("Helvetica", Font.BOLD , 20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Panel that display pieces
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(myGameSpacePanel, gbc);

        // Label for the next piece
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx++;
        final JLabel nextLabel = new JLabel("NEXT");
        nextLabel.setFont(labelFont);
        nextLabel.setForeground(mySetting.getForeground());
        add(nextLabel, gbc);

        // Panel that display next piece
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        add(myNextBlockPanel, gbc);

        // Label that indicate score
        gbc.gridy++;
        add(createLabel("SCORE:", labelFont), gbc);

        // Label for score
        gbc.gridy++;
        myScore.setFont(labelFont);
        myScore.setForeground(mySetting.getForeground());
        add(myScore, gbc);

        // Label that indicate level
        gbc.gridy++;
        add(createLabel("LEVEL:", labelFont), gbc);

        // Label for level
        gbc.gridy++;
        myLevel.setFont(labelFont);
        myLevel.setForeground(mySetting.getForeground());
        add(myLevel, gbc);

        // Fill empty space
        gbc.gridy++;
        gbc.fill = GridBagConstraints.VERTICAL;
        final JPanel space = new JPanel();
        space.setVisible(false);
        add(space, gbc);

        // Play button used to start or pause the game.
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        myPlayBtn.setPreferredSize(new Dimension(144, 50));
        myPlayBtn.setFont(labelFont);
        myPlayBtn.addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             * Start or pause the game.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pause the game
                if (myPlayBtn.getText().equals("PAUSE")) {
                    myFrame.toScene("Pause");
                    myTimer.stop();
                // Start the game
                } else {
                    myPlayBtn.setText("PAUSE");
                    requestFocusInWindow();
                    myScoreCounter = new ScoreCounter();
                    myCurrentPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
                    myNextPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
                    myScore.setText("0");
                    myLevel.setText("0");
                    myTimer.restart();
                    myNextBlockPanel.repaint();
                }
            }
            
        });
        add(myPlayBtn, gbc);
    }

    /**
     * Create a label that indicate score or level.
     * 
     * @param theText The text of the label.
     * @param theFont The font of the label.
     * @return The label with given text and font.
     */
    private JLabel createLabel(final String theText, final Font theFont) {
        final JLabel label = new JLabel(theText);
        label.setFont(theFont);
        label.setForeground(mySetting.getForeground());
        label.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        return label;
    }

    /**
     * Resume the game.
     */
    protected void resume() {
        requestFocusInWindow();
        myTimer.start();
    }

    /**
     * End the game.
     */
    private void gameOver() {
        myTimer.stop();
        myPlayBtn.setText("START");
        myCurrentPiece = null;
        myNextPiece = null;
        for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 12; j++) {
                myPieces[i][j] = null;
            }
        }
    }

    /**
     * Get the row of the bottom left corner of the current piece.
     * 
     * @return The row of the bottom left corner of the current piece.
     */
    protected int getCurrentRow() {
        return myRow;
    }

    /**
     * Get the column of the bottom left corner of the current piece.
     * 
     * @return The column of the bottom left corner of the current piece.
     */
    protected int getCurrentColumn() {
        return myColumn;
    }

    /**
     * Get the current piece.
     * 
     * @return The current piece.
     */
    protected Piece getCurrentPiece() {
        return myCurrentPiece;
    }

    /**
     * Get the next piece.
     * 
     * @return The next piece.
     */
    protected Piece getNextPiece() {
        return myNextPiece;
    }

    /**
     * Get pieces currently placed in the game space.
     * 
     * @return Pieces currently placed in the game space.
     */
    protected PieceUnit[][] getPieces() {
        return myPieces;
    }

    /**
     * Check whether the current piece can drop to next row.
     * 
     * @return Whether the current piece can drop to next row.
     */
    private boolean canDrop() {
        boolean drop = true;
        final int[][] shape = myCurrentPiece.getCurrentShape();
        outerLoop:
        for (int c = 0; c < shape[0].length; c++) {
            for (int r = shape.length - 1; r > -1; r--) {
                if (shape[r][c] == 1) {
                    final int nextR = myRow - shape.length + r + 2;
                    if (nextR > 19 || (nextR > -1 &&myPieces[nextR][myColumn + c] != null)) {
                        drop = false;
                        break outerLoop;
                    }
                    break;
                }
            }
        }
        return drop;
    }

    /**
     * Action listener for the timer that update the game scene and relative information
     * as the piece drop.
     */
    private class DropBlockAction implements ActionListener {
        /**
         * {@inheritDoc}
         * Move to next row or get next piece, and repaint the panel.
         */
        @Override
        public void actionPerformed(final ActionEvent e) {
            final int[][] shape = myCurrentPiece.getCurrentShape();
            if(!canDrop()) {
                // Place the piece in current position
                for (int r = myRow, i = shape.length - 1; i > -1; r--, i--) {
                    for (int c = myColumn, j = 0; j < shape[0].length; c++, j++) {
                        if (shape[i][j] == 1) {
                            if (r < 0) {    // Reach the top, end the game
                                gameOver();
                                return;
                            }
                            myPieces[r][c] = myCurrentPiece.getUnit();
                        }
                    }
                }
                // Get next piece
                myCurrentPiece = myNextPiece;
                myNextPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
                myCurrentPiece.setRandomShape();
                myRow = 0;
                myColumn = 5;
                myNextBlockPanel.repaint();
            } else {
                myRow++;
            }
            myGameSpacePanel.repaint();
        }
    }

    /**
     * Property change listener that listen to the change in background.
     */
    private class PieceChangeListener implements PropertyChangeListener {
        /**
         * {@inheritDoc}
         * Change the color of each piece when background color changes.
         */
        @Override
        public void propertyChange(final PropertyChangeEvent e) {
            if (e.getPropertyName().equals("color") && e.getNewValue() != e.getOldValue()) {
                for (Piece p : myAllPieces) {
                    p.backgroundChanged((Color) e.getNewValue());
                }
            }
        }
    }

    private class GameSceneKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent e) {

        }
    }
    
}
