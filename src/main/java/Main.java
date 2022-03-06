import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLettersGame;
import games.HexLetters;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.debug("Starting a new game");
        try {
            Map<Locale, String> dictionaryPaths = new HashMap<>();
            dictionaryPaths.put(Locale.US, "src/main/resources/dictionaries/en_us_dictionary.json");
            Dictionary dictionary = new Dictionary(new DictionaryParser(new ObjectMapper(), Locale.US, dictionaryPaths));

            HexLettersGame hexLettersGame = new HexLetters(dictionary).create();

            log.debug("New game created with main letter {}, extra letters {} and {} solutions: {}",
                    hexLettersGame.getMainLetter(), hexLettersGame.getExtraLetters(), hexLettersGame.getSolutions().size(), hexLettersGame.getSolutions());
        } catch (ParsingError e) {
            e.printStackTrace();
        }
    }

}
