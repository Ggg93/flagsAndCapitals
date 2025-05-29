package dev.gl.flagsandcapitals.models;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbGeography;
import dev.gl.flagsandcapitals.enums.Difficulty;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.MainWindowMode;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    private Integer hints;
    private Integer hintRate;
    private List<DbGeography> questions;
    
    public GameModel(MainWindow mw, Region region) {
        this.mw = mw;
        this.region = region;
        con = HyperConnection.getInstance();
        gameMode = Configuration.getGameMode();
        difficulty = Configuration.getDifficulty();
        questionId = 1;
        lives = difficulty.getLives();
        hints = difficulty.getInitialAvailableHints();
        hintRate = difficulty.getHintRate();
        questions = loadQuestionsFromDB();
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

    public GameMode getGameMode() {
        return gameMode;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Region getRegion() {
        return region;
    }

    public String getStepInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionId);
        sb.append(" / ");
        sb.append(questions.size());
        return sb.toString();
    }

    public Integer getLives() {
        return lives;
    }

    public Integer getHints() {
        return hints;
    }

    private List<DbGeography> loadQuestionsFromDB() {
        Map<Integer, DbGeography> regionsFromDB = (region == Region.ALL)
                ? DbGeography.getAllRows(con) 
                : DbGeography.getRowsByRegionFilter(con, region);
        List<DbGeography> questionList = new ArrayList<>(regionsFromDB.values());
        Collections.shuffle(questionList);
        return questionList;
    }
    
    public DbGeography getNextQuestion() {
        return questions.get(questionId - 1);
    }

    public DbGeography useHint() {
        // check that player has at least one hint
        if (hints == 0) {
            return null;
        }
        
        hints--;
        return getNextQuestion();
    }
    
}
