package com.tetris.gui_scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * The UI for slider in the setting scene.
 * 
 * @author Jiameng Li
 * @version 1.0
 */
public class SliderUI extends BasicSliderUI {

    /**
     * Create a UI for the slider.
     * 
     * @param theSlider The slider that use the UI.
     */
    public SliderUI(final JSlider theSlider) {
        super(theSlider);
    }

    /**
     * {@inheritDoc}
     * In this UI, the size is always 15x15.
     */
    @Override
    protected Dimension getThumbSize() {
        return new Dimension(15, 15);
    }

    /**
     * {@inheritDoc}
     * Only change vertical slider because horizontal slider is never used in this program.
     */
    @Override
    public void paintThumb(final Graphics theGraphics) {
        if (slider.getOrientation() == JSlider.VERTICAL) {
            final Graphics2D g2 = (Graphics2D) theGraphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(slider.getBackground());
            g2.fillRect(thumbRect.x, thumbRect.y, thumbRect.width - 2, thumbRect.height - 7);
        } else {
            super.paintThumb(theGraphics);
        }
    }

    /**
     * {@inheritDoc}
     * Only change vertical slider because horizontal slider is never used in this program.
     */
    @Override
    public void paintTrack(final Graphics theGraphics) {
        if (slider.getOrientation() == JSlider.VERTICAL) {
            final Graphics2D g2 = (Graphics2D) theGraphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Paint whole track
            g2.setColor(slider.getForeground());
            g2.fillRect(slider.getWidth() / 2 - thumbRect.width - 10, 5, thumbRect.width + 7, slider.getHeight());
            // Paint a line that indicate the value as the thumb moves
            g2.setColor(slider.getBackground());
            g2.fillRect(slider.getWidth() / 2 - thumbRect.width - 5, thumbRect.y + 3, 6, slider.getHeight() - thumbRect.y);
        } else {
            super.paintTrack(theGraphics);
        }
    }
    
}
