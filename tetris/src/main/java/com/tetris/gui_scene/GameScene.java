package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.tetris.gui_button.CommonButton;
import com.tetris.model.Block;
import com.tetris.model.BlockUnit;
import com.tetris.model.IBlock;
import com.tetris.model.ScoreCounter;
import com.tetris.model.Setting;

/**
 * The game scene where the game is being played.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class GameScene extends JPanel{

    /** A block acts as a wall around the game space. */
    private final static BlockUnit WALL = new BlockUnit();

    /** All available blocks in the game. */
    private final static Block[] ALL_BLOCKS = {new IBlock()};

    /** The general setting of the game. */
    private final Setting mySetting;

    /** The main frame. */
    private final TetrisFrame myFrame;

    /** The panel that displays blocks. */
    private final GameSpacePanel myGameSpacePanel;

    /** The panel that displays next block. */
    private final NextBlockPanel myNextBlockPanel;

    /** The button used to start or pause the game. */
    private final CommonButton myPlayBtn;

    /** The label for score. */
    private final JLabel myScore;

    /** The label for level. */
    private final JLabel myLevel;

    /** The score counter. */
    private final ScoreCounter myScoreCounter;

    /** The blocks placed in the game space. */
    private final BlockUnit[][] myBlocks;

    /** Timer used to move the block in a cartain rate. */
    private final Timer myTimer;

    /** Used to select a random block. */
    private static Random myRand;
 
    /** The row where the bottom-left corner ofthe block is placed. */
    private int myRow;

    /** The column where the bottom-left corner of the block is placed. */
    private int myColumn;

    /** The current block. */
    private Block myCurrentBlock;

    /** The next block. */
    private Block myNextBlock;

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
        myGameSpacePanel = new GameSpacePanel(this, theSetting);
        myNextBlockPanel = new NextBlockPanel(this, theSetting);
        myPlayBtn = new CommonButton("START", theSetting);
        myScoreCounter = new ScoreCounter();
        myScore = new JLabel("0");
        myLevel = new JLabel("0");
        myBlocks = new BlockUnit[21][13];
        myTimer = new Timer(myScoreCounter.getSpeed(), new DropBlockAction());
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

        // Fill the wall around the game space
        for (int i = 0; i < 20; i++) {
            myBlocks[i][0] = WALL;
            myBlocks[i][1] = WALL;
            myBlocks[i][12] = WALL;
        }
        for (int i = 0; i < 13; i++) {
            myBlocks[20][i] = WALL;
        } 

        Font labelFont = new Font("Helvetica", Font.BOLD , 20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Panel that display blocks
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(myGameSpacePanel, gbc);

        // Label for the next block
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx++;
        final JLabel nextLabel = new JLabel("NEXT");
        nextLabel.setFont(labelFont);
        nextLabel.setForeground(mySetting.getForeground());
        add(nextLabel, gbc);

        // Panel that display next block
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
        myPlayBtn.setPreferredSize(new Dimension(120, 50));
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
                    myCurrentBlock = ALL_BLOCKS[myRand.nextInt(ALL_BLOCKS.length)];
                    myNextBlock = ALL_BLOCKS[myRand.nextInt(ALL_BLOCKS.length)];
                    myTimer.start();
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
    public void resume() {
        requestFocusInWindow();
        myTimer.start();
    }

    /**
     * Action listener for the timer that update the game scene and relative information
     * as the block drop.
     */
    private class DropBlockAction implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            // TODO Update as block drop
        }
    }
    
}
