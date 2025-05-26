package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.utils.Configuration;
import javax.swing.ImageIcon;

/**
 *
 * @author gl
 */
public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
        mainPanel.add(new MainMenuPanel());
        
        setupThisWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        leftButtonsPanel = new javax.swing.JPanel();
        mainMenuButton = new javax.swing.JButton();
        rightButtonsPanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Configuration.getResourceBundle().getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(500, 400));
        setPreferredSize(new java.awt.Dimension(500, 400));

        mainPanel.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        buttonsPanel.setLayout(new java.awt.GridLayout(1, 2));

        leftButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        mainMenuButton.setText(Configuration.getResourceBundle().getString("mainMenuButton")); // NOI18N
        leftButtonsPanel.add(mainMenuButton);

        buttonsPanel.add(leftButtonsPanel);

        rightButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        exitButton.setText(Configuration.getResourceBundle().getString("exitButton")); // NOI18N
        rightButtonsPanel.add(exitButton);

        buttonsPanel.add(rightButtonsPanel);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel leftButtonsPanel;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel rightButtonsPanel;
    // End of variables declaration//GEN-END:variables

    private void setupThisWindow() {
        // set window icon
        ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-map-40.png"));
        this.setIconImage(icon.getImage());

        // it helps detects key presses
        mainPanel.requestFocus();

        // place window in the middle of the screen
        this.setLocationRelativeTo(null);
    }
}
