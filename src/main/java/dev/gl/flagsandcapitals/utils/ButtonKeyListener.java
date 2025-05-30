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

    private final GameBoardPanel gameBoardPanel;
    private final List<JTextField> textFields;
    private JTextField nextFieldToBeFocused;

    public ButtonKeyListener(GameBoardPanel gameBoardPanel, List<JTextField> textFields) {
        this.gameBoardPanel = gameBoardPanel;
        this.textFields = textFields;
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        // if all letters are filled, enabling answer button; disable otherwise
        boolean allLettersFilled = textFields.stream().allMatch(textField -> {
            String text = textField.getText();
            return text != null && !text.isEmpty();
        });
        gameBoardPanel.setAnswerButtonEnabled(allLettersFilled);
    }
    
    

    public void setNextFieldToBeFocused(JTextField nextFieldToBeFocused) {
        this.nextFieldToBeFocused = nextFieldToBeFocused;
    }

}
