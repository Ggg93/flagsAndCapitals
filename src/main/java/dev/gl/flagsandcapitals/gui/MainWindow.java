package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.enums.MainWindowMode;
import dev.gl.flagsandcapitals.models.GameModel;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.MainWindowListenersKeeper;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author gl
 */
public class MainWindow extends javax.swing.JFrame {

    private MainWindowMode mainWindowMode;
    private MainWindowListenersKeeper listenersKeeper;
    private MainMenuPanel mainMenuPanel = new MainMenuPanel();
    private GameBoardPanel gameBoardPanel = new GameBoardPanel();
    private GameModel gameModel;

    public MainWindow() {
        initComponents();
        setMainWindowMode(MainWindowMode.IDLE);

        listenersKeeper = new MainWindowListenersKeeper(this);
        bindActionsToButtons();
        createKeyBindings();
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

        // hide this button for idle mode
        mainMenuButton.setVisible(false);
    }

    public void setMainWindowMode(MainWindowMode mode) {
        mainWindowMode = mode;
        mainPanel.removeAll();

        if (mode == MainWindowMode.PLAYING) {
            mainPanel.add(gameBoardPanel);
            mainMenuButton.setVisible(true);
            mainMenuButton.setEnabled(true);
        }

        if (mode == MainWindowMode.IDLE) {
            mainPanel.add(mainMenuPanel);
            mainMenuButton.setVisible(false);
            mainMenuButton.setEnabled(false);
        }
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void bindActionsToButtons() {
        exitButton.addActionListener(listenersKeeper.getClosingMainWindowListener());
        mainMenuButton.addActionListener(listenersKeeper.getBackToMainMenuListener());
        mainMenuPanel.getNewGameButton().addActionListener(listenersKeeper.getChoosingRegionDialogListener());
        mainMenuPanel.getSettingsButton().addActionListener(listenersKeeper.getSettingsDialogListener());
        mainMenuPanel.getAboutButton().addActionListener(listenersKeeper.getAboutDialogListener());
        mainMenuPanel.getStatisticsButton().addActionListener(listenersKeeper.getStatisticsDialogListener());
        mainMenuPanel.getAchievementsButton().addActionListener(listenersKeeper.getAchievementsDialogListener());
    }
    
    private void createKeyBindings() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = this.getRootPane().getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "close");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "newGame");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backToMainMenu");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "about");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "settings");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "stats");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "achievements");
        
        actionMap.put("close", listenersKeeper.getClosingMainWindowListener());
        actionMap.put("newGame", listenersKeeper.getChoosingRegionDialogListener());
        actionMap.put("backToMainMenu", listenersKeeper.getBackToMainMenuListener());
        actionMap.put("settings", listenersKeeper.getSettingsDialogListener());
        actionMap.put("about", listenersKeeper.getAboutDialogListener());
        actionMap.put("stats", listenersKeeper.getStatisticsDialogListener());
        actionMap.put("achievements", listenersKeeper.getAchievementsDialogListener());
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public MainWindowMode getMainWindowMode() {
        return mainWindowMode;
    }
    
}
