package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tetris.gui_button.CommonButton;
import com.tetris.model.BlockUnit;
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

    /** The panel that displays blocks. */
    private final GameSpacePanel myGameSpace;

    /** The panel that displays next block. */
    private final NextBlockPanel myNextBlock;

    /** The button used to start or pause the game. */
    private final CommonButton myPlayBtn;

    /** The label for score. */
    private final JLabel myScore;

    /** The score counter. */
    private final ScoreCounter myScoreCounter;

    /** The blocks placed in the game space. */
    private final BlockUnit[][] myBlocks;

    /** Whether teh game has started. */
    private boolean myStarted;

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
        myGameSpace = new GameSpacePanel(this, theSetting);
        myNextBlock = new NextBlockPanel(this, theSetting);
        myPlayBtn = new CommonButton("START", theSetting);
        myScoreCounter = new ScoreCounter();
        myScore = new JLabel("0");
        myBlocks = new BlockUnit[20][10];
        myStarted = false;
        setup();
    }

    /**
     * Set up the game scene.
     */
    private void setup() {
        setBackground(mySetting.getBackground());
        setForeground(mySetting.getForeground());
        mySetting.addPropertyChangeListener(new SettingChangeListener(this, mySetting));

        Font labelFont = new Font("Helvetica", Font.BOLD , 20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Panel that display blocks
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(myGameSpace, gbc);

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
        add(myNextBlock, gbc);

        // Label that indicate score
        gbc.gridy++;
        final JLabel scoreLabel = new JLabel("SCORE:");
        scoreLabel.setFont(labelFont);
        scoreLabel.setForeground(mySetting.getForeground());
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        add(scoreLabel, gbc);

        // Label for score
        gbc.gridy++;
        myScore.setFont(labelFont);
        myScore.setForeground(mySetting.getForeground());
        add(myScore, gbc);

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
                if (myPlayBtn.getText().equals("PAUSE")) {
                    // TODO Pause the game.
                    myFrame.toScene("Pause");
                } else {
                    // TODO Start the game.
                    myStarted = true;
                    myPlayBtn.setText("PAUSE");
                    requestFocusInWindow();
                }
            }
            
        });
        add(myPlayBtn, gbc);;
    }
    
}
