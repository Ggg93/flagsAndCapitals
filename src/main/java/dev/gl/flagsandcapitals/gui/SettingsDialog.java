package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.enums.Difficulty;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.Language;
import dev.gl.flagsandcapitals.listeners.CloseDialogAbstractAction;
import dev.gl.flagsandcapitals.listeners.SettingsDialogElementsStateListener;
import dev.gl.flagsandcapitals.listeners.SettingsDialogSaveButtonListener;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author gl
 */
public class SettingsDialog extends javax.swing.JDialog {
    
    private Logger logger;
    private MainWindow parent;
    private CloseDialogAbstractAction closeButtonListener;
    private Boolean needRestart = false;

    public SettingsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = (MainWindow) parent;
        initComponents();
        setName(this.getClass().getSimpleName());
        setSaveButtonEnabled(false);
        setupIcon();
        initLanguageCombobox();
        initModeCombobox();
        initDifficultyCombobox();
        bindButtonsToListeners();
        createKeyBindings();
        
        
        this.setLocationRelativeTo(null);
        logger = Logging.getLocalLogger(this.getClass());
        logger.log(Level.FINE, this.getName() + " opened");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        langPanel = new javax.swing.JPanel();
        langLabel = new javax.swing.JLabel();
        langComboBox = new javax.swing.JComboBox();
        modePanel = new javax.swing.JPanel();
        modeLabel = new javax.swing.JLabel();
        modeComboBox = new javax.swing.JComboBox();
        difficultyPanel = new javax.swing.JPanel();
        difficultyLabel = new javax.swing.JLabel();
        difficultyComboBox = new javax.swing.JComboBox();
        buttonsPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Configuration.getResourceBundle().getString("settingsTitle")); // NOI18N
        setMaximumSize(new java.awt.Dimension(300, 250));
        setMinimumSize(new java.awt.Dimension(300, 250));
        setPreferredSize(new java.awt.Dimension(300, 250));
        setResizable(false);

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(Configuration.getResourceBundle().getString("settingsGroupMain"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        langPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        langLabel.setText(Configuration.getResourceBundle().getString("settingsLangLabel")); // NOI18N
        langPanel.add(langLabel);

        langComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        langPanel.add(langComboBox);

        jPanel1.add(langPanel);

        modePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        modeLabel.setText(Configuration.getResourceBundle().getString("settingsModeLabel")); // NOI18N
        modePanel.add(modeLabel);

        modeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modePanel.add(modeComboBox);

        jPanel1.add(modePanel);

        difficultyPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        difficultyLabel.setText(Configuration.getResourceBundle().getString("settingsDifficultyLabel")); // NOI18N
        difficultyPanel.add(difficultyLabel);

        difficultyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        difficultyPanel.add(difficultyComboBox);

        jPanel1.add(difficultyPanel);

        mainPanel.add(jPanel1);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        saveButton.setText(Configuration.getResourceBundle().getString("settingsSaveButton")); // NOI18N
        buttonsPanel.add(saveButton);

        closeButton.setText(Configuration.getResourceBundle().getString("settingsCloseButton")); // NOI18N
        buttonsPanel.add(closeButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox difficultyComboBox;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JPanel difficultyPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox langComboBox;
    private javax.swing.JLabel langLabel;
    private javax.swing.JPanel langPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox modeComboBox;
    private javax.swing.JLabel modeLabel;
    private javax.swing.JPanel modePanel;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    private void initLanguageCombobox() {
        DefaultComboBoxModel<Language> comboBoxModel = new DefaultComboBoxModel<>(Language.values());
        langComboBox.setModel(comboBoxModel);
        langComboBox.setSelectedItem(Configuration.getLang());
    }

    private void initModeCombobox() {
        DefaultComboBoxModel<GameMode> comboBoxModel = new DefaultComboBoxModel<>(GameMode.values());
        modeComboBox.setModel(comboBoxModel);
        modeComboBox.setSelectedItem(Configuration.getGameMode());
    }

    private void initDifficultyCombobox() {
        DefaultComboBoxModel<Difficulty> comboBoxModel = new DefaultComboBoxModel<>(Difficulty.values());
        difficultyComboBox.setModel(comboBoxModel);
        difficultyComboBox.setSelectedItem(Configuration.getDifficulty());
    }

    private void bindButtonsToListeners() {
        closeButtonListener = new CloseDialogAbstractAction(this);
        closeButton.addActionListener(closeButtonListener);
        
        SettingsDialogElementsStateListener elementsListener = new SettingsDialogElementsStateListener(this);
        langComboBox.addActionListener(elementsListener);
        modeComboBox.addActionListener(elementsListener);
        difficultyComboBox.addActionListener(elementsListener);
        
        SettingsDialogSaveButtonListener saveButtonListener = new SettingsDialogSaveButtonListener(this);
        saveButton.addActionListener(saveButtonListener);
    }

    private void createKeyBindings() {
        this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ok");
        this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ok");

        this.getRootPane().getActionMap().put("ok", closeButtonListener);
    }
    
    public void setSaveButtonEnabled(boolean isEnabled) {
        saveButton.setEnabled(isEnabled);
    }
    
    public boolean isSaveButtonEnabled() {
        return saveButton.isEnabled();
    }
    
    public Language getSelectedLanguage() {
        return (Language) langComboBox.getSelectedItem();
    }
    
    public GameMode getSelectedGameMode() {
        return (GameMode) modeComboBox.getSelectedItem();
    }
    
    public Difficulty getSelectedDifficulty() {
        return (Difficulty) difficultyComboBox.getSelectedItem();
    }

    private void setupIcon() {
        ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-map-40.png"));
        this.setIconImage(icon.getImage());
    }

    public void needRestart() {
        needRestart = true;
    }

    public Boolean getNeedRestart() {
        return needRestart;
    }

    public MainWindow getParent() {
        return parent;
    }
    
}
