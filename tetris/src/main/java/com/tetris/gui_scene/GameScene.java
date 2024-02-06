package com.tetris.gui_scene;

import java.awt.BorderLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.tetris.gui_button.CommonButton;
import com.tetris.model.GameSpace;
import com.tetris.model.ScoreCounter;
import com.tetris.model.Setting;

/**
 * The game scene where the game is being played.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class GameScene extends JPanel{

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

    /** The score counter. */
    private final ScoreCounter myScoreCounter;

    private final GameSpace myGameSpace;

    /** Whether the current piece is soft dropping. */
    private boolean mySoftDropping;

    /**
     * Create a game scene.
     * 
     * @param theSetting The general setting of the game.
     * @param theFrame The main frame.
     */
    public GameScene(final Setting theSetting, final TetrisFrame theFrame) {
        super(new GridBagLayout());
        mySetting = theSetting;
        myFrame = theFrame;
        myGameSpace = new GameSpace(theSetting);
        myGameSpacePanel = new GameSpacePanel(myGameSpace, theSetting);
        myNextBlockPanel = new NextBlockPanel(myGameSpace, theSetting);
        myNewGameBtn = new CommonButton("NEW GAME", theSetting);
        myPauseBtn = new CommonButton("PAUSE", theSetting);
        myScore = new JLabel("0");
        myLevel = new JLabel("0");
        myTimer = new Timer(ScoreCounter.INIT_SPEED, new DropBlockAction());
        myScoreCounter = new ScoreCounter();
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
        addKeyListener(new GameSceneKeyAdapter());
        myScoreCounter.addPropertyChangeListener(new ScoreChangeListener());
        myTimer.setInitialDelay(0); 

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
        myGameSpace.clear();
        myPauseBtn.setEnabled(false);
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
            if (myGameSpace.getCurrentPiece() != null) {
                gameOver();
            }
            requestFocusInWindow();
            myGameSpace.start();
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
            // Get next piece
            if(!myGameSpace.canMove(myGameSpace.getCurrentRow() + 1, myGameSpace.getCurrentColumn())) {
                if (myGameSpace.placeCurrent()) { 
                    gameOver(); 
                    return;
                }
                myGameSpace.clearLine(myScoreCounter);
                myGameSpace.nextPiece();
                myNextBlockPanel.repaint();
            // Or drop the current piece
            } else {
                myGameSpace.dropCurrent(1);
                if (mySoftDropping) { myScoreCounter.addScore(1); }
            }
            myGameSpace.setHasKick(false);
            myGameSpace.setTSpin(0);
            myGameSpacePanel.repaint();
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
            if (!myTimer.isRunning() || myGameSpace.getCurrentPiece() == null) { return; }
            final int keycode = e.getKeyCode();
            int[] move = null;
            final int row = myGameSpace.getCurrentRow();
            final int col = myGameSpace.getCurrentColumn();
            if (keycode == mySetting.getKey("Shift Left") && myGameSpace.canMove(row, col - 1)) {
                myGameSpace.shiftCurrentLeft(1);
            } else if (keycode == mySetting.getKey("Shift Right") && myGameSpace.canMove(row, col + 1)) {
                myGameSpace.shiftCurrentRight(1);
            } else if (keycode == mySetting.getKey("Rotate Clockwise")) {
                move = myGameSpace.getCurrentPiece().rotateClockwise(myGameSpace);
            } else if (keycode == mySetting.getKey("Rotate Counterclockwise")) {
                move = myGameSpace.getCurrentPiece().rotateCounterclockwise(myGameSpace);
            } else if (keycode == mySetting.getKey("Soft Drop")) {
                mySoftDropping = true;
                myTimer.setDelay(myScoreCounter.getSpeed() / 3);
            }
            if (keycode != mySetting.getKey("Soft Drop")) {
                myGameSpacePanel.repaint();
            }
            // For wall kick
            if (move != null && (move[0] != 0 || move[1] != 0)) {
                myGameSpace.dropCurrent(move[0]);
                myGameSpace.shiftCurrentRight(move[1]);
                myGameSpace.setHasKick(true);
            }
            // For T-spin and mini T-spin
            if (move != null) {
                myGameSpace.checkTSpin();
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
            if (!myTimer.isRunning() || myGameSpace.getCurrentPiece() == null) { return; }
            final int keycode = e.getKeyCode();
            if (keycode == mySetting.getKey("Soft Drop")) {
                myTimer.setDelay(myScoreCounter.getSpeed());
                mySoftDropping = false;
            } else if (keycode == mySetting.getKey("Hard Drop")) {
                myScoreCounter.addScore(myGameSpace.hardDrop() * 2);
                repaint();
            }
        }

    }
    
}
