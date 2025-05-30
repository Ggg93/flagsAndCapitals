package dev.gl.flagsandcapitals.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author gl
 */
public class ButtonKeyListener extends KeyAdapter {
    private JTextField nextFieldToBeFocused;

    @Override
    public void keyPressed(KeyEvent e) {
        // prevents unwanted displacement
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE 
                || e.getKeyCode() == KeyEvent.VK_SHIFT
                || e.getKeyCode() == KeyEvent.VK_ENTER
                || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return;
        }
        
        if (nextFieldToBeFocused != null) {
            final JTextField nextField = nextFieldToBeFocused;
            SwingUtilities.invokeLater(() -> nextField.requestFocusInWindow());
        }
    }

    public void setNextFieldToBeFocused(JTextField nextFieldToBeFocused) {
        this.nextFieldToBeFocused = nextFieldToBeFocused;
    }
    
}
