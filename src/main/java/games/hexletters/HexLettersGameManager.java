package games.hexletters;

import dictionary.Dictionary;
import domain.exceptions.EntryNotFoundError;
import domain.games.HexLetters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HexLettersGameManager {

    private static final String EMPTY_STRING = "";

    private final HexLetters game;
    private final Dictionary dictionary;
    @Getter
    private final List<String> solutions;

    @Getter
    private long numberOfTries = 0l;
    @Getter
    private final List<String> totalTries = new ArrayList<>();
    @Getter
    private final List<String> successfulTries = new ArrayList<>();

    public HexLettersGameManager(HexLetters game, Dictionary dictionary) {
        this.game = game;
        this.dictionary = dictionary;
        this.solutions = game.getSolutions();
    }

    public char getMainLetter() {
        return game.getMainLetter();
    }

    public List<Character> getExtraLetters() {
        return game.getExtraLetters();
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
            return dictionary.getEntry(word).getDefinitions();
        } catch (EntryNotFoundError e) {
            log.warn(e.getMessage());
            return EMPTY_STRING;
        }
    }
}
