package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author gl
 */
public class ButtonDocumentListener implements DocumentListener {

    private final GameBoardPanel gameBoardPanel;
    private final List<JTextField> textFields;
    private JTextField nextFieldToBeFocused;

    public ButtonDocumentListener(GameBoardPanel gameBoardPanel, List<JTextField> textFields) {
        this.gameBoardPanel = gameBoardPanel;
        this.textFields = textFields;
    }

    public void setNextFieldToBeFocused(JTextField nextFieldToBeFocused) {
        this.nextFieldToBeFocused = nextFieldToBeFocused;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        handleUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        handleUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private void handleUpdate() {
        //         if all letters are filled, enabling answer button; disable otherwise
        SwingUtilities.invokeLater(() -> {
            boolean allLettersFilled = textFields.stream().allMatch(textField -> {
                String text = textField.getText();
                return text != null && !text.isEmpty();
            });
            gameBoardPanel.setAnswerButtonEnabled(allLettersFilled);
        });
        
        // switch focus to next letterButton
        if (nextFieldToBeFocused != null) {
            final JTextField nextField = nextFieldToBeFocused;
            SwingUtilities.invokeLater(() -> nextField.requestFocusInWindow());
        }
    }

}
