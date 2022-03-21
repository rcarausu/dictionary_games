package games.hexletters;

import dictionary.Dictionary;
import domain.games.HexLetters;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class HexLettersGameManager {

    private HexLetters game;
    private Dictionary dictionary;

    public boolean isSolution(String word) {
        return game.getSolutions().contains(word);
    }
}
