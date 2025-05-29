package dev.gl.flagsandcapitals.models;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.enums.Difficulty;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.MainWindowMode;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.Configuration;
import javax.swing.JOptionPane;

/**
 *
 * @author gl
 */
public class GameModel {
    
    private HyperConnection con;
    private MainWindow mw;
    private GameMode gameMode;
    private Difficulty difficulty;
    private Region region;
    private Integer questionId;
    private Integer lives;
    private Integer initialAvailableHints;
    private Integer hintRate;
    
    public GameModel(MainWindow mw, Region region) {
        this.mw = mw;
        this.region = region;
        con = HyperConnection.getInstance();
        gameMode = Configuration.getGameMode();
        difficulty = Configuration.getDifficulty();
        questionId = 1;
        lives = difficulty.getLives();
        initialAvailableHints = difficulty.getInitialAvailableHints();
        hintRate = difficulty.getHintRate();
        
        mw.setMainWindowMode(MainWindowMode.PLAYING);
    }

    /**
     * logic for losing game
     * @param showJOptionPane 
     */
    public void lose(boolean showJOptionPane) {
        if (showJOptionPane) {
            JOptionPane.showConfirmDialog(mw, Configuration.getResourceBundle().getString("gameModelLossMessage"),
                Configuration.getResourceBundle().getString("title"), 
                JOptionPane.OK_OPTION, 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        // logic...
    }
    
}
