package com.tetris.model;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import java.awt.Color;

/**
 * The general setting of the game.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class Setting {

    /** The background color of the game. */
    private Color myBackground;

    /** The foreground color of the game. */
    private Color myForeground;

    /** The volume of the sound. Min 0, max 100. */
    private int myVolume;

    /** Map that contains all operations can be used on block and their corresponding keys. */
    private final Map<String, Integer> myKeys;

    /** All available background colors. The background color is always in index 0 and foreground in index 1/ */
    private final Map<String, Color[]> myColors;

    /** Property change support for setting changes. */
    private final PropertyChangeSupport myPCS;

    /**
     * Create a general setting.
     */
    public Setting() {
        super();
        myBackground = Color.BLACK;
        myForeground = Color.WHITE;
        myVolume = 80;
        myKeys = new LinkedHashMap<>();
        myColors = new LinkedHashMap<>();
        myPCS = new PropertyChangeSupport(this);
        setup();
    }

    /**
     * Set up initial corresponding keys for each operations and all available colors.
     */
    private void setup() {
        myKeys.put("Shift Left", KeyEvent.VK_A);
        myKeys.put("Shift Right", KeyEvent.VK_D);
        myKeys.put("Soft Drop", KeyEvent.VK_S);
        myKeys.put("Hard Drop", KeyEvent.VK_SPACE);
        myKeys.put("Rotate Counterclockwise", KeyEvent.VK_J);
        myKeys.put("Rotate Clockwise", KeyEvent.VK_K);

        myColors.put("White", new Color[]{Color.WHITE, Color.BLACK});
        myColors.put("Black", new Color[]{Color.BLACK, Color.WHITE});
    }

    /**
     * Get a set of all operations that can be performed on the block.
     * 
     * @return A set of all operations that can be performed on the block.
     */
    public Set<String> getAllOperations() {
        return myKeys.keySet();
    }

    /**
     * Get all keys for operations.
     * 
     * @return All keys for operations.
     */
    public Collection<Integer> getAllKeys() {
        return myKeys.values();
    }

    /**
     * Get the key code of corresponding key for the given operation.
     * 
     * @param theOperation The operation.
     * @return The key code of corresponding key.
     */
    public int getKey(final String theOperation) {
        return myKeys.get(theOperation);
    }

    /**
     * Get the background color of the game.
     * 
     * @return The background color of the game.
     */
    public Color getBackground() {
        return myBackground;
    }

    /**
     * Get the foreground color of the game.
     * 
     * @return The foreground color of the game.
     */
    public Color getForeground() {
        return myForeground;
    }

    /**
     * Get the names of all available background colors.
     * 
     * @return
     */
    public Set<String> getAvailableColors() {
        return myColors.keySet();
    }

    /**
     * Get the volume of the sound.
     * 
     * @return The volume of the sound.
     */
    public int getVolume() {
        return myVolume;
    }

    /**
     * Change the volume of the sound.
     * 
     * @param theVolume The new volume of the sound.
     */
    public void setVolume(final int theVolume) {
        myVolume = theVolume;
    }

    /**
     * Change the corresponding key for the given operation.
     * 
     * @param theOperation The operation.
     * @param theKey The key code for new corresponding key.
     */
    public void setKey(final String theOperation, final int theKey) {
        if (myKeys.containsKey(theOperation)) {
            myKeys.replace(theOperation, theKey);
        }
    }

    /**
     * Change the background and foreground color based on the given color. 
     * 
     * @param theColor The new background color.
     */
    public void setColor(final String theColor) {
        final Color oldBG = myBackground;
        final Color[] colors = myColors.get(theColor);
        myBackground = colors[0];
        myForeground = colors[1];
        myPCS.firePropertyChange("color", oldBG, myBackground);
    }



    /**
     * Add property change listener to the property change support.
     * 
     * @param theListener The property change listener for setting
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * Remove property change listener to the property change support.
     * 
     * @param theListener The property change listener for setting
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
    
}
