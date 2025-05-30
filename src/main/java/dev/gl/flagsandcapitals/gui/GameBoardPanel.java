package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.db.entities.DbGeography;
import static dev.gl.flagsandcapitals.enums.GameMode.CAPITALS;
import static dev.gl.flagsandcapitals.enums.GameMode.FLAGS;
import static dev.gl.flagsandcapitals.enums.Language.EN;
import dev.gl.flagsandcapitals.enums.LetterButtonState;
import static dev.gl.flagsandcapitals.enums.LetterButtonState.OK;
import dev.gl.flagsandcapitals.listeners.AnswerButtonActionListener;
import dev.gl.flagsandcapitals.listeners.HiddenHintAbstractListener;
import dev.gl.flagsandcapitals.listeners.HintButtonAbstractAction;
import dev.gl.flagsandcapitals.models.GameModel;
import dev.gl.flagsandcapitals.utils.ButtonDocumentListener;
import dev.gl.flagsandcapitals.utils.ButtonFilter;
import dev.gl.flagsandcapitals.utils.ButtonFocusListener;
import dev.gl.flagsandcapitals.utils.ButtonKeyListener;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import org.apache.batik.swing.JSVGCanvas;

/**
 *
 * @author gl
 */
public class GameBoardPanel extends javax.swing.JPanel {

    private static final Logger LOGGER = Logging.getLocalLogger(GameBoardPanel.class);
    private static final Font BUTTON_FONT = new Font("SansSerif", Font.PLAIN, 18);
    private static final Font QUESTION_FONT = new Font("SansSerif", Font.PLAIN, 24);
    private static final Font CAPITAL_FONT = new Font("SansSerif", Font.PLAIN, 32);
    private static final ButtonFilter BUTTON_FILTER = new ButtonFilter();
    private ImageIcon heartIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-heart-20.png"));
    private ImageIcon keyIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-key-20.png"));
    private GameModel gameModel;
    private JLabel livesNumberLabel;
    private JLabel hintNumberLabel;
    private JButton hintButton;
    private List<JTextField> textFields;
    private HintButtonAbstractAction hintButtonListener;
    private AnswerButtonActionListener answerButtonListener;
    private HiddenHintAbstractListener hiddenHintListener;
    private static Border regularTextFieldBorder;

    public GameBoardPanel() {
        initComponents();
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        initData();

        hintButtonListener = new HintButtonAbstractAction(this, gameModel);
        answerButtonListener = new AnswerButtonActionListener(this, gameModel);
        hiddenHintListener = new HiddenHintAbstractListener(this, gameModel);
        bindActionsToButtons();
        createKeyBindings();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoPanel = new javax.swing.JPanel();
        leftInfoPanel = new javax.swing.JPanel();
        regionLabel = new javax.swing.JLabel();
        regionValueLabel = new javax.swing.JLabel();
        stepLabel = new javax.swing.JLabel();
        stepValueLabel = new javax.swing.JLabel();
        rightInfoPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        questionPanel = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        flagOrCapitalPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        lettersPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        centerButtonsPanel = new javax.swing.JPanel();
        answerButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        infoPanel.setLayout(new java.awt.BorderLayout());

        leftInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        regionLabel.setText(Configuration.getResourceBundle().getString("regionLabel")); // NOI18N
        leftInfoPanel.add(regionLabel);

        regionValueLabel.setText("jLabel1");
        leftInfoPanel.add(regionValueLabel);

        stepLabel.setText(Configuration.getResourceBundle().getString("stepLabel")); // NOI18N
        leftInfoPanel.add(stepLabel);

        stepValueLabel.setText("jLabel1");
        leftInfoPanel.add(stepValueLabel);

        infoPanel.add(leftInfoPanel, java.awt.BorderLayout.CENTER);

        rightInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        infoPanel.add(rightInfoPanel, java.awt.BorderLayout.EAST);

        add(infoPanel, java.awt.BorderLayout.PAGE_START);

        mainPanel.setLayout(new java.awt.BorderLayout());

        questionLabel.setText(Configuration.getResourceBundle().getString("questionLabelFlag")); // NOI18N
        questionPanel.add(questionLabel);

        mainPanel.add(questionPanel, java.awt.BorderLayout.NORTH);

        flagOrCapitalPanel.setLayout(new java.awt.GridBagLayout());
        mainPanel.add(flagOrCapitalPanel, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);

        controlPanel.setLayout(new java.awt.BorderLayout());
        controlPanel.add(lettersPanel, java.awt.BorderLayout.NORTH);

        buttonsPanel.setLayout(new java.awt.BorderLayout());

        answerButton.setText(Configuration.getResourceBundle().getString("answerButtonOptionAnswer")); // NOI18N
        answerButton.setEnabled(false);
        centerButtonsPanel.add(answerButton);

        buttonsPanel.add(centerButtonsPanel, java.awt.BorderLayout.CENTER);

        controlPanel.add(buttonsPanel, java.awt.BorderLayout.CENTER);

        add(controlPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton answerButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel centerButtonsPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel flagOrCapitalPanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel leftInfoPanel;
    private javax.swing.JPanel lettersPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JPanel questionPanel;
    private javax.swing.JLabel regionLabel;
    private javax.swing.JLabel regionValueLabel;
    private javax.swing.JPanel rightInfoPanel;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JLabel stepValueLabel;
    // End of variables declaration//GEN-END:variables

    private void initData() {
        regionValueLabel.setText(gameModel.getRegion().toString());
        updateStepValue();

        // rightInfoPanel
        // lives
        JLabel heartLabel = new JLabel(heartIcon);
        rightInfoPanel.add(heartLabel);
        livesNumberLabel = new JLabel(gameModel.getLives().toString());
        rightInfoPanel.add(livesNumberLabel);

        // hints
        JLabel keyLabel = new JLabel(keyIcon);
        rightInfoPanel.add(keyLabel);
        hintNumberLabel = new JLabel(gameModel.getHints().toString());
        rightInfoPanel.add(hintNumberLabel);

        // getHint Button
        String hintButtonText = Configuration.getResourceBundle().getString("hintButton");
        hintButton = new JButton();
        hintButton.setText(hintButtonText);
        setHintButtonEnabled(gameModel.getHints() > 0);
        rightInfoPanel.add(hintButton);

        // questionLabel
        String questionLabelText = null;
        switch (gameModel.getGameMode()) {
            case FLAGS:
                questionLabelText = Configuration.getResourceBundle().getString("questionLabelFlag");
                break;
            case CAPITALS:
                questionLabelText = Configuration.getResourceBundle().getString("questionLabelCapital");
                break;
        }
        questionLabel.setText(questionLabelText);
        questionLabel.setFont(QUESTION_FONT);

        // flagOrCapitalPanel
        updateFlagOrCapitalPanel();

        // lettersButton
        updateLettersPanel();
    }

    public void setHintsNumber(Integer hints, Boolean subtracting) {
        hintNumberLabel.setText(hints.toString());
        String message = (subtracting ? "-1 " : "+1 ")
                + Configuration.getResourceBundle().getString("hintButton")
                + "!";
        showPopup(hintButton, message, 1000, 1500);
        setHintButtonEnabled(false);
    }

    public void showPopup(Component parent,
            String message,
            int fadeDurationMs,
            int visibleDurationMs) {
        PopupWindow.showPopupWindow(parent, message, fadeDurationMs, visibleDurationMs);
    }

    public void setAnswer(DbGeography answer) {
        String country = answer.getCountryLocalized();
        for (int i = 0; i < country.length(); i++) {
            char ch = country.charAt(i);
            textFields.get(i).setText(Character.toString(ch));
        }

        setAnswerButtonEnabled(true);
    }

    public Boolean isHintButtonEnabled() {
        return hintButton.isEnabled();
    }

    public void setHintButtonEnabled(boolean enabled) {
        hintButton.setEnabled(enabled);
    }

    public void clickAnswerButton() {
        if (answerButton.isEnabled()) {
            answerButton.doClick();
        }
    }

    public Boolean isAnswerButtonEnabled() {
        return answerButton.isEnabled();
    }

    public void setAnswerButtonEnabled(boolean allLettersFilled) {
        answerButton.setEnabled(allLettersFilled);
    }

    public String getAnswerButtonText() {
        return answerButton.getText();
    }

    public void setAnswerButtonText(String buttonText) {
        answerButton.setText(buttonText);
    }

    public void setAnswerButtonFocused() {
        answerButton.requestFocusInWindow();
        this.revalidate();
        this.repaint();
    }

    public void updateLivesNumberLabel(Integer lives) {
        livesNumberLabel.setText(lives.toString());
        String message = "-1 "
                + Configuration.getResourceBundle().getString("liveLabel")
                + "!";
        showPopup(livesNumberLabel, message, 1000, 1500);
    }

    private void bindActionsToButtons() {
        hintButton.addActionListener(hintButtonListener);
        answerButton.addActionListener(answerButtonListener);
    }

    private void createKeyBindings() {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK), "hint");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "hiddenHint");
        actionMap.put("hint", hintButtonListener);
        actionMap.put("hiddenHint", hiddenHintListener);
    }

    public String getUsersAnswer() {
        return textFields.stream()
                .map(JTextField::getText)
                .collect(Collectors.joining());
    }

    public void blockLetterButtons() {
        textFields.stream().forEach(field -> field.setFocusable(false));
//        textFields.stream().forEach(field -> field.setEnabled(false));
    }

    public void setTextFieldsState(LetterButtonState state) {
        textFields.stream()
                .forEach(field -> {
                    switch (state) {
                        case OK:
                            field.setBorder(new LineBorder(Color.GREEN, 2));
                            break;
                        case WRONG:
                            field.setBorder(new LineBorder(Color.RED, 2));
                            break;
                        case REGULAR:
                            field.setBorder(regularTextFieldBorder);
                            break;
                    }
                });
        this.revalidate();
        this.repaint();
    }

    public void showHiddenHint() {
        String message = gameModel.getNextQuestion().getCountryLocalized();
        showPopup(answerButton, message, 500, 1000);
    }

    public void showAnswerNotReadyMessage() {
        String message = Configuration.getResourceBundle().getString("answerNotPreparedMessage");
        showPopup(answerButton, message, 500, 1000);
    }

    public boolean areAllLettersFilled() {
        for (JTextField textField : textFields) {
            String text = textField.getText();
            if (text == null || text.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public void updateStepValue() {
        stepValueLabel.setText(gameModel.getStepInfo());
    }

    public void updateFlagOrCapitalPanel() {
        flagOrCapitalPanel.removeAll();

        switch (gameModel.getGameMode()) {
            case FLAGS:
                try {
                    String link = "images/flags/svg/" + gameModel.getNextQuestion().getIsoCode().toLowerCase() + ".svg";
                    URL url = this.getClass().getClassLoader().getResource(link);
                    String uri = url.toURI().toString();

                    JSVGCanvas svg = new JSVGCanvas();
                    svg.setBackground(new Color(0, 0, 0, 0));
//                    svg.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
                    svg.setURI(uri);
                    flagOrCapitalPanel.add(svg);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }

                break;
            case CAPITALS:
                String capital = gameModel.getNextQuestion().getCapitalLocalized();
                JLabel capitalLabel = new JLabel(capital);
                capitalLabel.setFont(CAPITAL_FONT);
                flagOrCapitalPanel.add(capitalLabel);
                break;
        }
        flagOrCapitalPanel.revalidate();
        flagOrCapitalPanel.repaint();
    }

    public void updateLettersPanel() {
        lettersPanel.removeAll();

        textFields = new ArrayList<>();
        String answer = gameModel.getNextQuestion().getCountryLocalized();

        for (int i = 0; i < answer.length(); i++) {
            JTextField letterField = new JTextField();
            textFields.add(letterField);
            letterField.setMaximumSize(new Dimension(25, Integer.MAX_VALUE));
            letterField.setPreferredSize(new Dimension(25, 40));
            letterField.setHorizontalAlignment(JTextField.CENTER);
            letterField.setFont(BUTTON_FONT);
            ((AbstractDocument) letterField.getDocument()).setDocumentFilter(BUTTON_FILTER);
            letterField.addFocusListener(new ButtonFocusListener(letterField));
            lettersPanel.add(letterField);
            if (regularTextFieldBorder == null) {
                regularTextFieldBorder = letterField.getBorder();
            }
            if (i == 0) {
                final JTextField firstField = letterField;
                SwingUtilities.invokeLater(() -> firstField.requestFocusInWindow());
            }
        }

        // adding listeners to shift the focus
        for (int i = 0; i < textFields.size(); i++) {
            JTextField textField = textFields.get(i);
            ButtonDocumentListener bdl = new ButtonDocumentListener(this, textFields);
            textField.getDocument().addDocumentListener(bdl);
            
            if (i < textFields.size() - 1) {
                bdl.setNextFieldToBeFocused(textFields.get(i + 1));
            }
        }
        lettersPanel.revalidate();
        lettersPanel.repaint();
    }

}
