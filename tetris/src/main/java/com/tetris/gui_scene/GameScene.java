package com.tetris.gui_scene;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tetris.model.BlockUnit;
import com.tetris.model.ScoreCounter;
import com.tetris.model.Setting;

public class GameScene extends JPanel{

    private final Setting mySetting;

    private final TetrisFrame myFrame;

    private final JPanel myGameSpace;

    private final JPanel myNextBlock;

    private final JLabel myScore;

    private final ScoreCounter myScoreCounter;

    private final JButton myPlayBtn;

    private final BlockUnit[][] myBlocks;

    public GameScene(final Setting theSetting, final TetrisFrame theFrame) {
        super(new GridBagLayout());
        mySetting = theSetting;
        myFrame = theFrame;
        myGameSpace = new JPanel();
        myNextBlock = new JPanel();
        myScore = new JLabel("0");
        myScoreCounter = new ScoreCounter();
        myPlayBtn = new JButton("START");
        myBlocks = new BlockUnit[20][10];
        setup();
    }

    private void setup() {
        GridBagConstraints gbc = new GridBagConstraints();
    }
    
}
