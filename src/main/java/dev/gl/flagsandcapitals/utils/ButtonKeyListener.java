package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author gl
 */
public class ButtonKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        // prevents unwanted displacement
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                || e.getKeyCode() == KeyEvent.VK_SHIFT
                || e.getKeyCode() == KeyEvent.VK_ENTER
                || e.getKeyCode() == KeyEvent.VK_ESCAPE
                || e.getKeyCode() == KeyEvent.VK_F11) {
            return;
        }
    }

}
