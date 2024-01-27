package com.tetris.driver;

import javax.swing.SwingUtilities;

import com.tetris.gui_scene.TetrisFrame;

/**
 * The driver class for the tetris game.
 *
 * @author Jiameng Li
 * @version 1.0
 */
public class TetrisMain {

    /**
     * Load game window.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TetrisFrame();
            }
        });
    }
}
