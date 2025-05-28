package dev.gl.flagsandcapitals.gui;

import javax.swing.JLabel;

/**
 *
 * @author gl
 */
public class AchievementPanel extends javax.swing.JPanel {

    public AchievementPanel(JLabel trophyImage, String description) {
        initComponents();
        imagePanel.add(trophyImage);
        descriptionLabel.setText(description);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JPanel();
        descriptionPanel = new javax.swing.JPanel();
        descriptionLabel = new javax.swing.JLabel();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        imagePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        add(imagePanel);

        descriptionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        descriptionLabel.setText("jLabel1");
        descriptionPanel.add(descriptionLabel);

        add(descriptionPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JPanel imagePanel;
    // End of variables declaration//GEN-END:variables

    public void addAchievementImage(JLabel image) {
        imagePanel.add(image);
    }
    
    public void addAchievementDescription(String description) {
        descriptionLabel.setText(description);
    }
}
