package dev.gl.flagsandcapitals.models;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbGames;
import dev.gl.flagsandcapitals.db.entities.DbGeography;
import dev.gl.flagsandcapitals.enums.Achievement;
import dev.gl.flagsandcapitals.enums.Difficulty;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.LetterButtonState;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.Configuration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author gl
 */
public class GameModel {

    private ResourceBundle RB = Configuration.getResourceBundle();
    private HyperConnection con;
    private MainWindow mw;
    private GameBoardPanel gameBoardPanel;
    private DbGames game;
    private GameMode gameMode;
    private Difficulty difficulty;
    private Region region;
    private Integer questionId;
    private Integer lives;
    private Integer hints;
    private Integer rightAnswers;
    private List<DbGeography> questions;
    private Boolean isGameFinished = false;
    private Integer guessedFlags;
    private Integer guessedCapitals;
    private AchievementsModel achievementsModel;

    public GameModel(MainWindow mw, Region region) {
        this.mw = mw;
        this.region = region;
        con = HyperConnection.getInstance();
        gameMode = Configuration.getGameMode();
        difficulty = Configuration.getDifficulty();
        questionId = 0;
        lives = difficulty.getLives();
        hints = difficulty.getInitialAvailableHints();
        rightAnswers = 0;
        guessedFlags = 0;
        guessedCapitals = 0;
        questions = loadQuestionsFromDB();

        game = new DbGames(null,
                gameMode,
                this.region,
                false,
                0,
                0,
                0,
                null,
                guessedFlags,
                guessedCapitals);
        
        achievementsModel = new AchievementsModel(game);
    }

    public void setGameBoardPanel(GameBoardPanel gameBoardPanel) {
        this.gameBoardPanel = gameBoardPanel;
    }

    /**
     * logic for losing game
     *
     * @param showJOptionPane
     */
    public void lose(boolean showJOptionPane) {
        // show dialog for user
        if (showJOptionPane) {
            JOptionPane.showMessageDialog(mw, Configuration.getResourceBundle().getString("gameModelLossMessage"),
                    Configuration.getResourceBundle().getString("title"),
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // save result to DB
        game.setIsWin(false);
        game.setDate(LocalDate.now());
        isGameFinished = true;
        DbGames.saveNewEntryInDb(game, con);

        // block answer button
        gameBoardPanel.setAnswerButtonEnabled(false);
        gameBoardPanel.getMainWindow().setMainMenuButtonFocused();
        
        checkAchievementConditions();
    }

    private void win() {
        // show dialog for user
        JOptionPane.showMessageDialog(mw, Configuration.getResourceBundle().getString("gameModelWinMessage"),
                Configuration.getResourceBundle().getString("title"),
                JOptionPane.INFORMATION_MESSAGE);

        // save result to DB
        game.setIsWin(true);
        game.setDate(LocalDate.now());
        isGameFinished = true;
        DbGames.saveNewEntryInDb(game, con);

        // block answer button
        gameBoardPanel.setAnswerButtonEnabled(false);
        gameBoardPanel.getMainWindow().setMainMenuButtonFocused();
        
        checkAchievementConditions();
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
        sb.append(questionId + 1);
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
        return questions.get(questionId);
    }

    public DbGeography useHint() {
        // check that player has at least one hint
        if (hints == 0) {
            return null;
        }

        hints--;
        game.setKeysUsed(game.getKeysUsed() + 1);
        return getNextQuestion();
    }

    public void checkAnswer() {
        gameBoardPanel.blockLetterButtons();
        gameBoardPanel.setHintButtonEnabled(false);
        gameBoardPanel.setAnswerButtonEnabled(false);

        String usersAnswer = gameBoardPanel.getUsersAnswer();
        boolean isAnswerRight = usersAnswer
                .equalsIgnoreCase(getNextQuestion().getCountryLocalized());

        if (!isAnswerRight) {
            game.setMistakes(game.getMistakes() + 1);
            lives--;
            gameBoardPanel.updateLivesNumberLabel(lives);
            // case: wrong answer
            gameBoardPanel.showHiddenHint(); // show right answer

            if (lives == 0) {
                lose(true);
                return;
            }

            if (questionId.equals(questions.size() - 1)) {
                win();
                return;
            }

        } else {
            // case: right answer
            game.setScore(game.getScore() + difficulty.getScoreRate());
            
            if (gameMode == GameMode.FLAGS) {
                game.setGuessedFlags(game.getGuessedFlags() + 1);
            } else if (gameMode == GameMode.CAPITALS) {
                game.setGuessedCapitals(game.getGuessedCapitals() + 1);
            }

            if (questionId.equals(questions.size() - 1)) {
                win();
                return;
            }

            rightAnswers++;
            if (rightAnswers.equals(difficulty.getHintRate())) {
                rightAnswers = 0;
                hints++;
                gameBoardPanel.setHintsNumber(hints, false);
            }
        }

        // checkAchievements
        checkAchievementConditions();
        
        // preparing gameBoardPanel before next question
        LetterButtonState state = isAnswerRight
                ? LetterButtonState.OK
                : LetterButtonState.WRONG;

        gameBoardPanel.setAnswerButtonText(RB.getString("answerButtonOptionNext"));
        gameBoardPanel.setTextFieldsState(state);
        gameBoardPanel.setAnswerButtonEnabled(true);
    }

    public void setNextQuestion() {
        questionId++;
        gameBoardPanel.updateStepValue();
        gameBoardPanel.updateScoreValue();
        gameBoardPanel.setHintButtonEnabled(hints > 0);
        gameBoardPanel.setAnswerButtonText(RB.getString("answerButtonOptionAnswer"));
        gameBoardPanel.setAnswerButtonEnabled(false);
        gameBoardPanel.updateFlagOrCapitalPanel();
        gameBoardPanel.updateLettersPanel();
    }

    public Boolean getIsGameFinished() {
        return isGameFinished;
    }

    public String getScore() {
        return game.getScore().toString();
    }

    private void checkAchievementConditions() {
        List<Achievement> newAchievements = achievementsModel.checkAchievementCondtions();
        for (Achievement ach : newAchievements) {
            gameBoardPanel.showAchievementPopup(ach);
        }
    }

}
