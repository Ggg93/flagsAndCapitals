package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbAchievements;
import dev.gl.flagsandcapitals.listeners.OkDisposingAction;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 *
 * @author gl
 */
public class AchievementsDialog extends javax.swing.JDialog {

    private static final Logger LOGGER = Logging.getLocalLogger(AchievementsDialog.class);
    private OkDisposingAction okButtonAction;

    public AchievementsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setupIcon();

        okButtonAction = new OkDisposingAction(this);
        attachListenerToOkButton();
        bindKeyListenersToOkButton();
        fillAchievementsPanel();
        this.pack();
        this.setLocationRelativeTo(null);
        LOGGER.log(Level.FINE, getName() + " opened");
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scrolledAchivementsPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Configuration.getResourceBundle().getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));
        setResizable(false);

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Configuration.getResourceBundle().getString("achievementsDialogMainPanelBorder"))); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrolledAchivementsPanel.setLayout(new javax.swing.BoxLayout(scrolledAchivementsPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(scrolledAchivementsPanel);

        mainPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        okButton.setText("OK");
        bottomPanel.add(okButton);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel scrolledAchivementsPanel;
    // End of variables declaration//GEN-END:variables

    private void attachListenerToOkButton() {
        okButton.addActionListener(okButtonAction);
    }

    private void bindKeyListenersToOkButton() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ok");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ok");

        actionMap.put("ok", okButtonAction);
    }

    private void setupIcon() {
        ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-map-40.png"));
        this.setIconImage(icon.getImage());
    }

    private void fillAchievementsPanel() {
        ClassLoader cl = this.getClass().getClassLoader();
        ImageIcon goldTrophy = new ImageIcon(cl.getResource("images/icons8-trophy-gold-40.png"));
        ImageIcon greyTrophy = new ImageIcon(cl.getResource("images/icons8-trophy-grey-40.png"));

        HyperConnection con = HyperConnection.getInstance();
        Map<Integer, DbAchievements> achievements = DbAchievements.getAllRows(con);

        for (DbAchievements achievement : achievements.values()) {

            JLabel iconLabel = null;
            if (achievement.getAchievedDate() != null) {
                iconLabel = new JLabel(goldTrophy);
            } else {
                iconLabel = new JLabel(greyTrophy);
            }
            AchievementPanel achievementPanel = new AchievementPanel(iconLabel, achievement.getAchievement().toString());
            achievementPanel.setPreferredSize(new Dimension(scrolledAchivementsPanel.getWidth(), 50));
            achievementPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//            achievementPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
            scrolledAchivementsPanel.add(achievementPanel);
        }

        scrolledAchivementsPanel.revalidate();
        scrolledAchivementsPanel.repaint();
    }

}
