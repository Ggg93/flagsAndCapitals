package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbAchievements;
import dev.gl.flagsandcapitals.db.entities.DbGames;
import dev.gl.flagsandcapitals.listeners.OkDisposingAction;
import dev.gl.flagsandcapitals.models.UneditableTableModel;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author gl
 */
public class StatisticsDialog extends javax.swing.JDialog {
    
    private static final Logger LOGGER = Logging.getLocalLogger(StatisticsDialog.class);
    private OkDisposingAction okButtonAction;

    public StatisticsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setupIcon();
        
        okButtonAction = new OkDisposingAction(this);
        attachListenerToOkButton();
        bindKeyListenersToOkButton();
        loadDataIntoTheTable();
        
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
        statisticsTable = new javax.swing.JTable();
        bottomPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Configuration.getResourceBundle().getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Configuration.getResourceBundle().getString("statsMainPanelBorder"))); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(400, 120));
        mainPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(375, 120));

        statisticsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(statisticsTable);

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
    private javax.swing.JTable statisticsTable;
    // End of variables declaration//GEN-END:variables

    private void loadDataIntoTheTable() {
        Object[][] stats = loadStats();
        String[] columnNames = {
            Configuration.getResourceBundle().getString("parameter"),
            Configuration.getResourceBundle().getString("value")};

        UneditableTableModel model = new UneditableTableModel(stats, columnNames);
        statisticsTable.setModel(model);
    }

    private Object[][] loadStats() {
        HyperConnection con = HyperConnection.getInstance();
        Map<Integer, DbGames> games = DbGames.getAllRows(con);
        Integer ttlGames = games.size();
        Integer wins = (int) games.values().stream()
                .filter(game -> game.getIsWin())
                .count();
        Integer defeats = ttlGames - wins;
        
        // calculate achievements
        Map<Integer, DbAchievements> achievementsMap = DbAchievements.getAllRows(con);
        Integer achievedGoals = (int) achievementsMap.values().stream()
                .filter(a -> a.getAchievedDate() != null)
                .count();
        String achievementsStats = achievedGoals + " / " + achievementsMap.size();
        
        Object[][] stats = {
            {Configuration.getResourceBundle().getString("statsTtlGames"), ttlGames},
            {Configuration.getResourceBundle().getString("statsWins"), wins},
            {Configuration.getResourceBundle().getString("statsDefeats"), defeats},
            {Configuration.getResourceBundle().getString("statsAchievements"), achievementsStats}
        };
        return stats;
    }
    
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
}
