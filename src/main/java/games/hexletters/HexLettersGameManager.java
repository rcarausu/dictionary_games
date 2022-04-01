package games.hexletters;

import domain.exceptions.EntryNotFoundError;
import domain.games.HexLetters;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HexLettersGameManager {

    private static final String EMPTY_STRING = "";

    @Getter
    private final HexLetters game;
    @Getter
    private long numberOfTries = 0L;
    @Getter
    private final List<String> totalTries = new ArrayList<>();
    @Getter
    private final List<String> successfulTries = new ArrayList<>();

    public HexLettersGameManager(HexLetters game) {
        this.game = game;
    }

    public boolean isSolution(String word) {
        numberOfTries++;
        totalTries.add(word);
        if (game.getSolutions().contains(word)) {
            successfulTries.add(word);
            return true;
        }
        return false;
    }

    public String getDefinitions(String word) {
        try {
            return game.getDictionary().getEntry(word).getDefinitions();
        } catch (EntryNotFoundError e) {
            log.warn(e.getMessage());
            return EMPTY_STRING;
        }
    }
}
