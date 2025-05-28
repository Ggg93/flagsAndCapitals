package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.utils.Configuration;
import javax.swing.JButton;

/**
 *
 * @author gl
 */
public class MainMenuPanel extends javax.swing.JPanel {

    public MainMenuPanel() {
        initComponents();
    }

    public JButton getAboutButton() {
        return aboutButton;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getStatisticsButton() {
        return statisticsButton;
    }

    public JButton getAchievementsButton() {
        return achievementsButton;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new javax.swing.JPanel();
        newGameButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        statisticsButton = new javax.swing.JButton();
        achievementsButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        buttonsPanel.setLayout(new java.awt.GridLayout(5, 0, 0, 25));

        newGameButton.setText(Configuration.getResourceBundle().getString("newGameButton")); // NOI18N
        buttonsPanel.add(newGameButton);

        settingsButton.setText(Configuration.getResourceBundle().getString("settingsButton")); // NOI18N
        buttonsPanel.add(settingsButton);

        statisticsButton.setText(Configuration.getResourceBundle().getString("statisticsButton")); // NOI18N
        buttonsPanel.add(statisticsButton);

        achievementsButton.setText(Configuration.getResourceBundle().getString("achievementsButton")); // NOI18N
        buttonsPanel.add(achievementsButton);

        aboutButton.setText(Configuration.getResourceBundle().getString("aboutButton")); // NOI18N
        buttonsPanel.add(aboutButton);

        add(buttonsPanel, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton achievementsButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton statisticsButton;
    // End of variables declaration//GEN-END:variables
}
