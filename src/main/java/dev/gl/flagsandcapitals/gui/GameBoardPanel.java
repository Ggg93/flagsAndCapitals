package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class GameBoardPanel extends javax.swing.JPanel {
    
    private static final Logger LOGGER = Logging.getLocalLogger(GameBoardPanel.class);

    public GameBoardPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoPanel = new javax.swing.JPanel();
        leftInfoPanel = new javax.swing.JPanel();
        regionLabel = new javax.swing.JLabel();
        regionTextField = new javax.swing.JTextField();
        stepLabel = new javax.swing.JLabel();
        stepTextField = new javax.swing.JTextField();
        rightInfoPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        questionPanel = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        flagOrCapitalPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        lettersButton = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        leftButtonPanel = new javax.swing.JPanel();
        endGameButton = new javax.swing.JButton();
        centerButtonsPanel = new javax.swing.JPanel();
        answerButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        infoPanel.setLayout(new java.awt.BorderLayout());

        leftInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        regionLabel.setText(Configuration.getResourceBundle().getString("regionLabel")); // NOI18N
        leftInfoPanel.add(regionLabel);

        regionTextField.setText("jTextField1");
        leftInfoPanel.add(regionTextField);

        stepLabel.setText(Configuration.getResourceBundle().getString("stepLabel")); // NOI18N
        leftInfoPanel.add(stepLabel);

        stepTextField.setText("jTextField2");
        leftInfoPanel.add(stepTextField);

        infoPanel.add(leftInfoPanel, java.awt.BorderLayout.CENTER);

        rightInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        infoPanel.add(rightInfoPanel, java.awt.BorderLayout.EAST);

        add(infoPanel, java.awt.BorderLayout.PAGE_START);

        mainPanel.setLayout(new java.awt.BorderLayout());

        questionLabel.setText(Configuration.getResourceBundle().getString("questionLabelFlag")); // NOI18N
        questionPanel.add(questionLabel);

        mainPanel.add(questionPanel, java.awt.BorderLayout.NORTH);

        flagOrCapitalPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.add(flagOrCapitalPanel, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);

        controlPanel.setLayout(new java.awt.BorderLayout());
        controlPanel.add(lettersButton, java.awt.BorderLayout.NORTH);

        buttonsPanel.setLayout(new java.awt.BorderLayout());

        leftButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        endGameButton.setText(Configuration.getResourceBundle().getString("endGameButton")); // NOI18N
        leftButtonPanel.add(endGameButton);

        buttonsPanel.add(leftButtonPanel, java.awt.BorderLayout.WEST);

        answerButton.setText(Configuration.getResourceBundle().getString("answerButton")); // NOI18N
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
    private javax.swing.JButton endGameButton;
    private javax.swing.JPanel flagOrCapitalPanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel leftButtonPanel;
    private javax.swing.JPanel leftInfoPanel;
    private javax.swing.JPanel lettersButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JPanel questionPanel;
    private javax.swing.JLabel regionLabel;
    private javax.swing.JTextField regionTextField;
    private javax.swing.JPanel rightInfoPanel;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JTextField stepTextField;
    // End of variables declaration//GEN-END:variables
}
