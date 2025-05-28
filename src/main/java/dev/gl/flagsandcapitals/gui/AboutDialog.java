package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.listeners.OkDisposingAction;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 *
 * @author gl
 */
public class AboutDialog extends javax.swing.JDialog {
    
    private static final Logger LOGGER = Logging.getLocalLogger(AboutDialog.class);
    private AbstractAction okButtonAction;

    public AboutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        okButtonAction = new OkDisposingAction(this);
        initComponents();
        setupIcon();
        this.setLocationRelativeTo(null);
        attachListenerToOkButton();
        bindKeyListenersToOkButton();
        loadLogo();
        getVersion();
        setHomeLink();
        setMapSourceLink();
        setHeartSourceLink();
        setKeySourceLink();
        setTrophySourceLink();
        setFlagsSourceLink();
        LOGGER.fine("AboutDialog opened");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        upperSubpanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        programNameLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        linksPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        homeLinkLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        mapLinkLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        heartLinkLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        keyLinkLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        trophyLinkLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        flagsLinkLabel = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Configuration.getResourceBundle().getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(380, 320));
        setPreferredSize(new java.awt.Dimension(380, 320));
        setResizable(false);

        mainPanel.setLayout(new java.awt.BorderLayout());

        upperSubpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        upperSubpanel.add(logoPanel);

        infoPanel.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        programNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        programNameLabel.setText(Configuration.getResourceBundle().getString("aboutProgramName")); // NOI18N
        jPanel4.add(programNameLabel);

        infoPanel.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText(Configuration.getResourceBundle().getString("aboutVersion")); // NOI18N
        jPanel3.add(jLabel3);

        versionLabel.setText("jLabel4");
        jPanel3.add(versionLabel);

        infoPanel.add(jPanel3, java.awt.BorderLayout.SOUTH);

        upperSubpanel.add(infoPanel);

        mainPanel.add(upperSubpanel, java.awt.BorderLayout.CENTER);

        linksPanel.setLayout(new java.awt.GridLayout(6, 1));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setText(Configuration.getResourceBundle().getString("aboutHome")); // NOI18N
        jPanel1.add(jLabel4);

        homeLinkLabel.setText("jLabel5");
        homeLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(homeLinkLabel);

        linksPanel.add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setText(Configuration.getResourceBundle().getString("aboutMapLinkLabel")); // NOI18N
        jPanel2.add(jLabel6);

        mapLinkLabel.setText("jLabel7");
        mapLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(mapLinkLabel);

        linksPanel.add(jPanel2);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel7.setText(Configuration.getResourceBundle().getString("aboutHeartLinkLabel")); // NOI18N
        jPanel5.add(jLabel7);

        heartLinkLabel.setText("jLabel7");
        heartLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(heartLinkLabel);

        linksPanel.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel8.setText(Configuration.getResourceBundle().getString("aboutKeyLinkLabel")); // NOI18N
        jPanel6.add(jLabel8);

        keyLinkLabel.setText("jLabel7");
        keyLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel6.add(keyLinkLabel);

        linksPanel.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setText(Configuration.getResourceBundle().getString("aboutTrophyLinkLabel")); // NOI18N
        jPanel7.add(jLabel5);

        trophyLinkLabel.setText("jLabel5");
        trophyLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel7.add(trophyLinkLabel);

        linksPanel.add(jPanel7);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel9.setText(Configuration.getResourceBundle().getString("aboutFlagsLinkLabel")); // NOI18N
        jPanel8.add(jLabel9);

        flagsLinkLabel.setText("jLabel5");
        flagsLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel8.add(flagsLinkLabel);

        linksPanel.add(jPanel8);

        mainPanel.add(linksPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        okButton.setText("OK");
        bottomPanel.add(okButton);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel flagsLinkLabel;
    private javax.swing.JLabel heartLinkLabel;
    private javax.swing.JLabel homeLinkLabel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel keyLinkLabel;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mapLinkLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel programNameLabel;
    private javax.swing.JLabel trophyLinkLabel;
    private javax.swing.JPanel upperSubpanel;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables

    private void loadLogo() {
        ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/icons8-map-80.png"));
        JLabel logoLabel = new JLabel(icon);
        logoPanel.add(logoLabel);
    }

    private void getVersion() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(".properties"));
            versionLabel.setText(props.getProperty("version", "undefined"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    private void setHomeLink() {
        final String homeLink = "https://github.com/Ggg93";
        homeLinkLabel.setText("<html><a href=\"\">" + homeLink + "</a></html>");
        homeLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(homeLink));
                } catch (Exception urlException) {
                    LOGGER.log(Level.SEVERE, null, urlException);
                }
            }
        });
    }

    private void setMapSourceLink() {
        final String sudokuImageLink = "https://icons8.com/icon/59830/location";
        final String icons8Link = """
                                <html>
                                <a href="https://icons8.com/icon/59830/location">Map</a>
                                icon by 
                                <a href="https://icons8.com">Icons8</a>
                                </html>
                                """;
        mapLinkLabel.setText(icons8Link);
        mapLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mapLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(sudokuImageLink));
                } catch (Exception urlException) {
                    LOGGER.log(Level.SEVERE, null, urlException);
                }
            }
        });
    }
    
    private void setHeartSourceLink() {
        final String imageLink = "https://icons8.com/icon/DFU1kReSUccu/heart";
        final String icons8Link = """
                                <html>
                                <a href="https://icons8.com/icon/DFU1kReSUccu/heart">Heart</a>
                                icon by 
                                <a href="https://icons8.com">Icons8</a>
                                </html>
                                """;
        heartLinkLabel.setText(icons8Link);
        heartLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        heartLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(imageLink));
                } catch (Exception urlException) {
                    LOGGER.log(Level.SEVERE, null, urlException);
                }
            }
        });
    }
    
    private void setKeySourceLink() {
        final String imageLink = "https://icons8.com/icon/msjdn2ARObDz/key";
        final String icons8Link = """
                                <html>
                                <a href="https://icons8.com/icon/msjdn2ARObDz/key">Key</a>
                                icon by 
                                <a href="https://icons8.com">Icons8</a>
                                </html>
                                """;
        keyLinkLabel.setText(icons8Link);
        keyLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        keyLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(imageLink));
                } catch (Exception urlException) {
                    LOGGER.log(Level.SEVERE, null, urlException);
                }
            }
        });
    }
    
    private void setTrophySourceLink() {
        final String imageLink = "https://icons8.com/icon/pBDUTxjAkZKW/trophy";
        final String icons8Link = """
                                <html>
                                <a href="https://icons8.com/icon/pBDUTxjAkZKW/trophy">Trophy</a>
                                icon by 
                                <a href="https://icons8.com">Icons8</a>
                                </html>
                                """;
        trophyLinkLabel.setText(icons8Link);
        trophyLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        trophyLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(imageLink));
                } catch (Exception urlException) {
                    LOGGER.log(Level.SEVERE, null, urlException);
                }
            }
        });
    }
    
    private void setFlagsSourceLink() {
        final String homeLink = "https://github.com/hampusborgos/country-flags";
        flagsLinkLabel.setText("<html><a href=\"\">" + homeLink + "</a></html>");
        flagsLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        flagsLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(homeLink));
                } catch (Exception urlException) {
                    LOGGER.log(Level.SEVERE, null, urlException);
                }
            }
        });
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
