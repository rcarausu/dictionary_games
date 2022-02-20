import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLettersGame;
import games.HexLetters;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Starting a new game");
        try {
            Dictionary dictionary = new Dictionary(new DictionaryParser(new ObjectMapper(), Locale.US));

            HexLettersGame hexLettersGame = new HexLetters(dictionary).create();

            log.info("New game created with main letter {}, extra letters {} and {} solutions: {}",
                    hexLettersGame.getMainLetter(), hexLettersGame.getExtraLetters(), hexLettersGame.getSolutions().size(), hexLettersGame.getSolutions());
        } catch (ParsingError e) {
            e.printStackTrace();
        }
    }

}
