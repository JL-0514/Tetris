package com.tetris.gui_scene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

    /** The button used to start the game. */
    private final CommonButton myNewGameBtn;

    /** The button used to pause the game. */
    private final CommonButton myPauseBtn;

    /** The label for score. */
    private final JLabel myScore;

    /** The label for level. */
    private final JLabel myLevel;

    /** Timer used to move the piece in a cartain rate. */
    private final Timer myTimer;

    /** The pieces placed in the game space. */
    private final PieceUnit[][] myPieces;

    /** The score counter. */
    private final ScoreCounter myScoreCounter;
 
    /** The row where the bottom-left corner ofthe piece is placed. */
    private int myRow;

    /** The column where the bottom-left corner of the piece is placed. */
    private int myColumn;

    /** The current piece. */
    private Piece myCurrentPiece;

    /** The next piece. */
    private Piece myNextPiece;

    /** Whether the current piece is soft dropping. */
    private boolean mySoftDropping;

    /** Whether a wall kick is performed. */
    private boolean myHasKick;

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
        myNewGameBtn = new CommonButton("NEW GAME", theSetting);
        myPauseBtn = new CommonButton("PAUSE", theSetting);
        myScore = new JLabel("0");
        myLevel = new JLabel("0");
        myPieces = new PieceUnit[21][13];
        myTimer = new Timer(ScoreCounter.INIT_SPEED, new DropBlockAction());
        myScoreCounter = new ScoreCounter();
        myRand = new Random();
        myHasKick = false;
        setup();
    }

    /**
     * Set up the game scene by placing components on the right position
     * and add listeners for components.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setForeground(mySetting.getForeground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));
        mySetting.addPropertyChangeListener(new PieceChangeListener());
        addKeyListener(new GameSceneKeyAdapter(this));
        myScoreCounter.addPropertyChangeListener(new ScoreChangeListener());
        myTimer.setInitialDelay(0);

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
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(myGameSpacePanel, gbc);

        // Label for the next piece
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx++;
        final JLabel nextLabel = new JLabel("NEXT");
        nextLabel.setFont(labelFont);
        nextLabel.setForeground(mySetting.getForeground());
        add(nextLabel, gbc);

        // Panel that display next piece
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

        // Use nested panel to create empty space in grid bag layout
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(mySetting.getBackground());
        final JPanel nested = new JPanel(new GridLayout(2, 1, 0, 5));
        nested.setBackground(mySetting.getBackground());
        panel.add(nested, BorderLayout.SOUTH);

        // Play button used to start the game.
        final Dimension btnSize = new Dimension(144, 50);
        myNewGameBtn.setPreferredSize(btnSize);
        myNewGameBtn.setFont(labelFont);
        myNewGameBtn.addActionListener(new NewGameBtnActionListner());
        nested.add(myNewGameBtn);

        // Pause button used to pause the game.
        myPauseBtn.setPreferredSize(btnSize);
        myPauseBtn.setFont(labelFont);
        myPauseBtn.addActionListener(new PauseBtnActionListener());
        myPauseBtn.setEnabled(false);
        nested.add(myPauseBtn);

        gbc.gridy++;
        add(panel, gbc);
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
     * Resume the game from the pause scene.
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
        myScoreCounter.reset();
        myCurrentPiece = null;
        myNextPiece = null;
        myHasKick = false;
        for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 12; j++) {
                myPieces[i][j] = null;
            }
        }
        myPauseBtn.setEnabled(false);
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
     * Perform a hard drop on the current piece.
     */
    private void hardDrop() {
        int moves = 0;
        while (canMove(myRow + 1, myColumn)) {
            myRow++;
            moves++;
        }
        myScoreCounter.addScore(moves * 2);
    }

    /**
     * Check whether the current piece can move to given row and column.
     * The row and column indicate the destination position of the bottom left corner of the piece.
     * Search from the bottom left corner to top right until there's overlapping piece units.
     * 
     * @param theRow The row of the the destination position of the bottom left corner of the piece.
     * @param theCol The column of the the destination position of the bottom left corner of the piece.
     * @return Whether the current piece can move to given row and column.
     */
    private boolean canMove(final int theRow, final int theCol) {
        boolean move = true;
        final int[][] shape = myCurrentPiece.getCurrentShape();
        outerLoop:
        for (int r = shape.length - 1, i = 0; r > -1; r--, i++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 1 && (theRow - i > 19 || theCol + c < 2 || theCol + c > 11 ||
                    (theRow - i > -1 && myPieces[theRow - i][theCol + c] != null))) {
                    move = false;
                    break outerLoop;
                }
            }
        }
        return move;
    }

    /**
     * Get the surrounding of the current piece, that is, the piece units the current piece
     * may touch as it rotate.
     * 
     * @param theDown Get the surrounding n rows below the current piece.
     * @param theRight Get the surrounding n columns on the right of the current piece.
     * @return The surrounding of the current piece.
     */
    public PieceUnit[][] getSurrounding(final int theDown, final int theRight) {
        PieceUnit[][] surrounding = new PieceUnit[myCurrentPiece.getSize()][myCurrentPiece.getSize()];
        for (int r = surrounding.length - 1, i = 0; r > -1 && myRow - i > -1; r--, i++) {
            for (int c = 0; c < surrounding[0].length; c++) {
                final int row = myRow - i + theDown;
                final int col = myColumn + c + theRight;
                if (row > 20 || row < 0 || col > 12 || col < 0) {
                    surrounding[r][c] = WALL;
                } else {
                    surrounding[r][c] = myPieces[myRow - i + theDown][myColumn + c + theRight];
                }
            }
        }
        return surrounding;
    }

    /**
     * Check whether there's any filled rows. If so, clear them.
     * This must be done before getting next piece.
     */
    private void clearLine() {
        int cleared = 0;
        for (int r = 19; r > -1; r--) {
            boolean fill = true;
            int lines = 0;
            while (fill) {
                // Clear the filled row
                if (cleared > 0 && r - cleared > -1) {
                    for (int c = 2; c < 12; c++) {
                        myPieces[r][c] = myPieces[r - cleared][c];
                    }
                }
                // Find a filled row
                for (int c = 2; c < 12; c++) {
                    if (myPieces[r][c] == null) {
                        fill = false;
                        break;
                    }
                }
                // Count the filled row
                if (fill) {
                    cleared++;
                    lines++;
                }
            }
            // TODO Implement scoring for T-spin, back-to-back, and wall kick
            if (lines > 0) {
                myScoreCounter.addLine(lines); 
            }
        }
        myHasKick = false;
        
    }

     /**
     * Action listener for the button used to pause the game.
     */
    private class PauseBtnActionListener implements ActionListener {
         /**
         * {@inheritDoc}
         * Pause the game.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            myFrame.toScene("Pause");
            myTimer.stop();
        }
    }


    /**
     * Action listener for the button used to start the game.
     */
    private class NewGameBtnActionListner implements ActionListener {
        /**
         * {@inheritDoc}
         * Start the game.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (myCurrentPiece != null) {
                gameOver();
            }
            requestFocusInWindow();
            myCurrentPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
            myNextPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
            myRow = 0;
            myColumn = 5;
            mySoftDropping = false;
            myScore.setText("0");
            myLevel.setText("0");
            myGameSpacePanel.repaint();
            myNextBlockPanel.repaint();
            myPauseBtn.setEnabled(true);
            myTimer.setDelay(myScoreCounter.getSpeed());
            myTimer.restart();
        }
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
            if(!canMove(myRow + 1, myColumn)) {
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
                clearLine();
                // Get next piece
                myCurrentPiece = myNextPiece;
                myNextPiece = myAllPieces[myRand.nextInt(myAllPieces.length)];
                myCurrentPiece.setRandomShape();
                myRow = 0;
                myColumn = 5;
                myNextBlockPanel.repaint();
            } else {
                myRow++;
                if (mySoftDropping) { myScoreCounter.addScore(1); }
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
            if (e.getPropertyName().equals("color")) {
                for (Piece p : myAllPieces) {
                    p.backgroundChanged((Color) e.getNewValue());
                }
            }
        }
    }

    /**
     * Listener for changes in score and level.
     */
    private class ScoreChangeListener implements PropertyChangeListener {
        /**
         * {@inheritDoc}
         * Change the label for score and level, and adjust speed.
         */
        public void propertyChange(final PropertyChangeEvent e) {
            if (e.getPropertyName().equals("score")) {
                myScore.setText(Integer.toString(myScoreCounter.getScore()));
            } else if (e.getPropertyName().equals("level")) {
                myLevel.setText(Integer.toString(myScoreCounter.getLevel()));
                myTimer.setDelay(myScoreCounter.getSpeed());
            }
        }
    }

    /**
     * Key sdapter used to move and rotate the current piece.
     */
    private class GameSceneKeyAdapter extends KeyAdapter {

        /** The game scene that use this key adapter. */
        private final GameScene myScene;

        /**
         * Create a key adapter.
         * 
         * @param theScene The game scene that use this key adapter.
         */
        public GameSceneKeyAdapter(final GameScene theScene) {
            super();
            myScene = theScene;
        }

        /**
         * {@inheritDoc}
         * Perform one of the following operations on the current piece based on the key pressed: 
         * 1. Shift left
         * 2. Shift right
         * 3. Rotate clockwise
         * 4. Rotate counterclockwise
         * 5. Soft drop
         */
        @Override
        public void keyPressed(final KeyEvent e) {
            if (!myTimer.isRunning() || myCurrentPiece == null) { return; }
            final int keycode = e.getKeyCode();
            int[] move = null;
            if (keycode == mySetting.getKey("Shift Left") && canMove(myRow, myColumn - 1)) {
                myColumn--;
            } else if (keycode == mySetting.getKey("Shift Right") && canMove(myRow, myColumn + 1)) {
                myColumn++;
            } else if (keycode == mySetting.getKey("Rotate Clockwise")) {
                move = myCurrentPiece.rotateClockwise(myScene);
            } else if (keycode == mySetting.getKey("Rotate Counterclockwise")) {
                move = myCurrentPiece.rotateCounterclockwise(myScene);
            } else if (keycode == mySetting.getKey("Soft Drop")) {
                mySoftDropping = true;
                myTimer.setDelay(myScoreCounter.getSpeed() / 3);
            }
            if (keycode != mySetting.getKey("Soft Drop")) {
                myGameSpacePanel.repaint();
            }
            // For wall kick
            if (move != null) {
                myRow += move[0];
                myColumn += move[1];
                myHasKick = true;
            }
        }

        /**
         * {@inheritDoc}
         * Perform one of the following operations on the current piece based on the key released: 
         * 1. Resume to regular speed if released from soft drop.
         * 2. Perform a hard drop.
         */
        @Override
        public void keyReleased(final KeyEvent e) {
            if (!myTimer.isRunning() || myCurrentPiece == null) { return; }
            final int keycode = e.getKeyCode();
            if (keycode == mySetting.getKey("Soft Drop")) {
                myTimer.setDelay(myScoreCounter.getSpeed());
                mySoftDropping = false;
            } else if (keycode == mySetting.getKey("Hard Drop")) {
                hardDrop();
                repaint();
            }
        }

    }
    
}
