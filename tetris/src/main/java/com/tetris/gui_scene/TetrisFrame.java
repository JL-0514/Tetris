package com.tetris.gui_scene;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tetris.model.Setting;

/**
 * The main frame of the game.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class TetrisFrame extends JFrame {

    /** The start scene of the game. */
    private final StartScene myStartScene;

    /** Teh game scene. */
    private final GameScene myGameScene;

    /** Teh pause scene. */
    private final PauseScene myPauseScene;

    /** The setting scene. */
    private final SettingScene mySettingScene;

    /** The general setting of the game. */
    private final Setting mySetting;

    /** The card layout used to switch between scenes. */
    private final CardLayout myLayout;

    /** The container of the card layout. */
    private JPanel myContainer;

    /** The scene currently displaying. */
    private String myCurrentScene;


    /**
     * Initialize the frame and its components.
     */
    public TetrisFrame() {
        super("Tetris");
        mySetting = new Setting();
        myStartScene = new StartScene(mySetting, this);
        myGameScene = new GameScene(mySetting, this);
        myPauseScene = new PauseScene(mySetting, this);
        mySettingScene = new SettingScene(mySetting, this);
        myLayout = new CardLayout();
        myContainer = new JPanel();
        myCurrentScene = "Start";
        setup();
    }

    /**
     * Set up the frame and its components.
     */
    private void setup() {
        add(myContainer, BorderLayout.CENTER);
        myContainer.setLayout(myLayout);
        myContainer.add(myStartScene, "Start");
        myContainer.add(mySettingScene, "Setting");
        myContainer.add(myGameScene, "Game");
        myContainer.add(myPauseScene, "Pause");
        myLayout.show(myContainer, "Start");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Switch to given scene.
     * 
     * @param theScene The scene to display.
     */
    public void toScene(final String theScene) {
        myLayout.show(myContainer, theScene);
        if (theScene.equals("Setting")) {
            mySettingScene.setPrevScene(myCurrentScene);
        }
        myCurrentScene = theScene;
    }
    
}
