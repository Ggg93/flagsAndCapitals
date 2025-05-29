package dev.gl.flagsandcapitals.gui;

import static dev.gl.flagsandcapitals.enums.GameMode.CAPITALS;
import static dev.gl.flagsandcapitals.enums.GameMode.FLAGS;
import static dev.gl.flagsandcapitals.enums.Language.EN;
import dev.gl.flagsandcapitals.models.GameModel;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.batik.swing.JSVGCanvas;

/**
 *
 * @author gl
 */
public class GameBoardPanel extends javax.swing.JPanel {

    private static final Logger LOGGER = Logging.getLocalLogger(GameBoardPanel.class);
    private ImageIcon heartIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-heart-20.png"));
    private ImageIcon keyIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-key-20.png"));
    private GameModel gameModel;

    public GameBoardPanel() {
        initComponents();
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        initData();
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
    private javax.swing.JLabel regionValueLabel;
    private javax.swing.JPanel rightInfoPanel;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JLabel stepValueLabel;
    // End of variables declaration//GEN-END:variables

    private void initData() {
        regionValueLabel.setText(gameModel.getRegion().toString());
        stepValueLabel.setText(gameModel.getStepInfo());

        // rightInfoPanel
        JLabel heartLabel = new JLabel(heartIcon);
        rightInfoPanel.add(heartLabel);
        JLabel heartNumber = new JLabel(gameModel.getLives().toString());
        rightInfoPanel.add(heartNumber);
        JLabel keyLabel = new JLabel(keyIcon);
        rightInfoPanel.add(keyLabel);
        JLabel keyNumber = new JLabel(gameModel.getHints().toString());
        rightInfoPanel.add(keyNumber);

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

        // flagOrCapitalPanel
        switch (gameModel.getGameMode()) {
            case FLAGS:
                try {
                    String link = "images/flags/svg/" + gameModel.getNextQuestion().getIsoCode().toLowerCase() + ".svg";
                    URL url = this.getClass().getClassLoader().getResource(link);
                    String uri = url.toURI().toString();
                    
                    JSVGCanvas svg = new JSVGCanvas();
                    svg.setURI(uri);
                    flagOrCapitalPanel.add(svg);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }

                break;
            case CAPITALS:
                String capital = null;
                switch (Configuration.getLang()) {
                    case EN:
                        capital = gameModel.getNextQuestion().getCapitalEn();
                        break;
                    case RU:
                        capital = gameModel.getNextQuestion().getCapitalRu();
                        break;
                }
                JLabel capitalLabel = new JLabel(capital);
                flagOrCapitalPanel.add(capitalLabel);
                break;
        }
        flagOrCapitalPanel.revalidate();
        flagOrCapitalPanel.repaint();
    }
}
