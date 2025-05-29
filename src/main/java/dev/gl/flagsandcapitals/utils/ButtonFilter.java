package dev.gl.flagsandcapitals.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author gl
 */
public class ButtonFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null || text.length() == 0) {
            return;
        }
        
        char ch = text.charAt(0);
        
        if (isValidChar(ch)) {
            ch = Character.toUpperCase(ch);
            fb.replace(0, fb.getDocument().getLength(), String.valueOf(ch), attrs);
        }
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }
    
    

    private boolean isValidChar(char ch) {
        return Character.isLetter(ch) || ch == ' ' || ch == '-';
    }
    
}
