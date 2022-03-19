import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLetters;
import games.HexLettersCreator;
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

            HexLetters hexLetters = new HexLettersCreator(dictionary).create();

            log.debug("New game created with main letter {}, extra letters {} and {} solutions: {}",
                    hexLetters.getMainLetter(), hexLetters.getExtraLetters(), hexLetters.getSolutions().size(), hexLetters.getSolutions());
        } catch (ParsingError e) {
            e.printStackTrace();
        }
    }

}
