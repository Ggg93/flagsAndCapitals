package dev.gl.flagsandcapitals.models;

import dev.gl.flagsandcapitals.enums.Achievement;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.gui.PopupWindow;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.Component;
import java.awt.IllegalComponentStateException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author gl
 */
public class PopupManager {
    private static final Logger LOGGER = Logging.getLocalLogger(PopupManager.class);
    
    private final Queue<String> messageQueue = new LinkedList<>();
    private boolean isShowing = false;
    private Component parentComponent;
    private MainWindow mainWindow;
    private ImageIcon trophyIcon;

    public PopupManager(Component parentComponent, MainWindow mainWindow) {
        this.parentComponent = parentComponent;
        this.mainWindow = mainWindow;
        
        ClassLoader cl = this.getClass().getClassLoader();
        trophyIcon = new ImageIcon(cl.getResource("images/icons8-trophy-gold-40.png"));
    }
    
    public void showAchievementPopup(Achievement ach) {
        messageQueue.offer(ach.toString());
        if (!isShowing) {
            showNext();
        }
    }

    public void showNext() {
        try {
            PopupWindow.showPopupWindowWithManager(parentComponent, trophyIcon, 250, 50, -300, 500, 1000, this, mainWindow);
        } catch (IllegalComponentStateException e) {
            LOGGER.log(Level.FINE, "Player closed gameBoard and some achievements were not shown");
        }
    }

    public boolean isIsShowing() {
        return isShowing;
    }

    public void setIsShowing(boolean isShowing) {
        this.isShowing = isShowing;
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    public Queue<String> getMessageQueue() {
        return messageQueue;
    }
    
}
