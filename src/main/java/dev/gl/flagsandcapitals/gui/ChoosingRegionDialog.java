package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.listeners.OkDisposingAction;
import dev.gl.flagsandcapitals.listeners.StartNewGameLAbstractAction;
import dev.gl.flagsandcapitals.utils.Configuration;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author gl
 */
public class ChoosingRegionDialog extends javax.swing.JDialog {
    
    private AbstractAction okButtonAction;

    public ChoosingRegionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setupIcon();
        
        initRegionCombobox();
        okButtonAction = new StartNewGameLAbstractAction(this);
        attachListenerToOkButton();
        createKeyListeners();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        upperPanel = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        choosingLabel = new javax.swing.JLabel();
        regionComboBox = new javax.swing.JComboBox();
        bottomPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Configuration.getResourceBundle().getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(250, 150));
        setPreferredSize(new java.awt.Dimension(250, 150));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(3, 0));

        upperPanel.setLayout(new java.awt.GridBagLayout());

        questionLabel.setText(Configuration.getResourceBundle().getString("choosingRegionDialogQuestionLabel")); // NOI18N
        upperPanel.add(questionLabel, new java.awt.GridBagConstraints());

        getContentPane().add(upperPanel);

        centerPanel.setLayout(new java.awt.GridBagLayout());

        choosingLabel.setText(Configuration.getResourceBundle().getString("choosingRegionDialogChoosingLabel")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        centerPanel.add(choosingLabel, gridBagConstraints);

        regionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        centerPanel.add(regionComboBox, gridBagConstraints);

        getContentPane().add(centerPanel);

        bottomPanel.setLayout(new java.awt.GridBagLayout());

        okButton.setText("OK");
        bottomPanel.add(okButton, new java.awt.GridBagConstraints());

        getContentPane().add(bottomPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel choosingLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JComboBox regionComboBox;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables

    private void setupIcon() {
        ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-map-40.png"));
        this.setIconImage(icon.getImage());
    }
    
    private void initRegionCombobox() {
        DefaultComboBoxModel<Region> comboBoxModel = new DefaultComboBoxModel<>(Region.values());
        regionComboBox.setModel(comboBoxModel);
        regionComboBox.setSelectedItem(Region.ALL);
    }
    
    private void attachListenerToOkButton() {
        okButton.addActionListener(okButtonAction);
    }
    
    private void createKeyListeners() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = this.getRootPane().getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ok");
        
        actionMap.put("exit", new OkDisposingAction(this));
        actionMap.put("ok", okButtonAction);
    }
}
