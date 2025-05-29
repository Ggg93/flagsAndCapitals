package dev.gl.flagsandcapitals.utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author gl
 */
public class ButtonFocusListener implements FocusListener {
    
    private static final Color UNFOCUSED_COLOR = new Color(255, 255, 255);
    private static final Color FOCUSED_COLOR = new Color(211, 211, 211);
    private JTextField textField;

    public ButtonFocusListener(JTextField textField) {
        this.textField = textField;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        textField.setBackground(FOCUSED_COLOR);
    }

    @Override
    public void focusLost(FocusEvent e) {
        textField.setBackground(UNFOCUSED_COLOR);
    }
    
}
