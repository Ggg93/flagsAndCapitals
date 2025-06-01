package dev.gl.flagsandcapitals.models;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbAchievements;
import dev.gl.flagsandcapitals.db.entities.DbGames;
import dev.gl.flagsandcapitals.enums.Achievement;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author gl
 */
public class AchievementsModel {

    private static final Logger LOGGER = Logging.getLocalLogger(AchievementsModel.class);
    
    private DbGames game;
    private List<Achievement> availableAchs;
    private Map<Integer, DbAchievements> achsInDB;
    private List<DbGames> previousGames;

    public AchievementsModel(DbGames game) {
        if (game == null) {
            throw new IllegalArgumentException("game cannot be null");
        }
        this.game = game;
        loadAvailableAchievements();
        loadPrevousGames();
    }

    private void loadAvailableAchievements() {
        HyperConnection con = HyperConnection.getInstance();
        
        // collect achs from DB without date (not achieved)
        achsInDB = DbAchievements.getAllRows(con).values()
                .stream()
                .filter(ach -> ach.getAchievedDate() == null)
                .collect(Collectors.toMap(ach -> ach.getAchievement().getCode(), ach -> ach));
        
        // select unreached achs from enum
        availableAchs = Arrays.asList(Achievement.values())
                .stream()
                .filter(ach -> achsInDB.containsKey(ach.getCode()))
                .collect(Collectors.toList());
    }
    
    public List<Achievement> checkAchievementCondtions() {
        List<Achievement> completedAchs = new ArrayList<>();
        
        GameMode mode = game.getGameMode();
        Region region = game.getRegion();
        Boolean finished = game.getDate() != null;
        for (Achievement ach : availableAchs) {
            // check gameMode match
            if (ach.getGameMode() != null && mode != ach.getGameMode()) {
                continue;
            }
            
            // check region match
            if (ach.getRegion() != null && region != ach.getRegion()) {
                continue;
            }
            
            // exclude achs that require completed games if the game continues
            if (!finished && (ach.getGames() > 0 || ach.getWins() > 0)) {
                continue;
            }
            
            // exclude lost games from achievements that require victories
            if (finished && !game.getIsWin() && ach.getWins() > 0) {
                continue;
            }
            
            
            // collect previous games that satisfy the achievement's conditions
            List<DbGames> matchedGames = new ArrayList(previousGames);
            if (ach.getGameMode() != null) {
                matchedGames.removeIf(g -> g.getGameMode() != ach.getGameMode());
            }
            if (ach.getRegion() != null) {
                matchedGames.removeIf(g -> g.getRegion() != ach.getRegion());
            }
            
            // main block: check conditions
            Integer ttlGamesCondition = ach.getGames();
            if (ttlGamesCondition > 0) {
                Integer prevGames = (int) matchedGames
                        .stream()
                        .count();
                if (prevGames + 1 >= ttlGamesCondition) {
                    LOGGER.log(Level.FINE, "New achievement: " + ach.getName());
                    completedAchs.add(ach);
                    updateAchInDB(achsInDB.get(ach.getCode()));
                }
                continue;
            }
            
            Integer winsCondition = ach.getWins();
            if (winsCondition > 0) {
                Integer prevGames = (int) matchedGames
                        .stream()
                        .filter(g -> g.getIsWin())
                        .count();
                if (prevGames + 1 >= winsCondition) {
                    LOGGER.log(Level.FINE, "New achievement: " + ach.getName());
                    completedAchs.add(ach);
                    updateAchInDB(achsInDB.get(ach.getCode()));
                }
                continue;
            }
            
            Integer flagsCondition = ach.getFlags();
            if (flagsCondition > 0) {
                Integer flags = matchedGames
                        .stream()
                        .mapToInt(DbGames::getGuessedFlags)
                        .sum();
                if (flags + game.getGuessedFlags() >= flagsCondition) {
                    LOGGER.log(Level.FINE, "New achievement: " + ach.getName());
                    completedAchs.add(ach);
                    updateAchInDB(achsInDB.get(ach.getCode()));
                }
                continue;
            }
            
            Integer capitalsCondition = ach.getCapitals();
            if (capitalsCondition > 0) {
                Integer capitals = matchedGames
                        .stream()
                        .mapToInt(DbGames::getGuessedCapitals)
                        .sum();
                if (capitals + game.getGuessedCapitals() >= capitalsCondition) {
                    LOGGER.log(Level.FINE, "New achievement: " + ach.getName());
                    completedAchs.add(ach);
                    updateAchInDB(achsInDB.get(ach.getCode()));
                }
                continue;
            }
            
            Integer scoreCondition = ach.getScore();
            if (scoreCondition > 0) {
                Integer score = matchedGames
                        .stream()
                        .mapToInt(DbGames::getScore)
                        .sum();
                if (score + game.getScore() >= scoreCondition) {
                    LOGGER.log(Level.FINE, "New achievement: " + ach.getName());
                    completedAchs.add(ach);
                    updateAchInDB(achsInDB.get(ach.getCode()));
                }
                continue;
            }
        }
        
        // remove all currently satisfied achivements
        if (!completedAchs.isEmpty()) {
            availableAchs.removeAll(completedAchs);
        }
        
        return completedAchs;
    }

    private void loadPrevousGames() {
        HyperConnection con = HyperConnection.getInstance();
        previousGames = DbGames.getAllRows(con).values()
                .stream()
                .filter(game -> game.getDate() != null)
                .collect(Collectors.toList());
    }

    private void updateAchInDB(DbAchievements ach) {
        ach.setAchievedDate(LocalDate.now());
        DbAchievements.updateRow(ach, HyperConnection.getInstance());
    }
    
}
