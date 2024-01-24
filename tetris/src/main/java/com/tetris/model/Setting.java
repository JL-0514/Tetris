package com.tetris.model;

import java.awt.Color;

public class Setting {

    private Color myBackground;

    private Color myForeground;

    public Setting() {
        myBackground = Color.BLACK;
        myForeground = Color.WHITE;
    }

    public Color getBackground() {
        return myBackground;
    }

    public Color getForeground() {
        return myForeground;
    }
    
}
