import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLetters;
import games.hexletters.HexLettersBuilder;
import games.hexletters.HexLettersGameManager;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        log.info("Starting a new game");
        try {
            // TODO: move the generation of this map to a configuration file/class
            Map<Locale, String> dictionaryPaths = new HashMap<>();
            dictionaryPaths.put(Locale.US, "src/main/resources/dictionaries/en_us_dictionary.json");

            HexLetters hexLetters = new HexLettersBuilder()
                    .from(new Dictionary(new DictionaryParser(new ObjectMapper(), Locale.US, dictionaryPaths)))
                    .build();

            HexLettersGameManager manager = new HexLettersGameManager(hexLetters);

            while (manager.getSuccessfulTries().size() != manager.getGame().getSolutions().size()) {
                log.info("Guess a word:");
                String guess = scanner.nextLine();

                if (manager.isSolution(guess)) {
                    log.info("Congrats! {} is a solution. Found {} of {}", guess, manager.getSuccessfulTries().size(), manager.getGame().getSolutions().size());
                } else {
                    log.info("Sorry, {} is not a solution, try another one!", guess);
                }
            }

            log.info("CONGRATS! You have found all solutions :)");

        } catch (ParsingError e) {
            log.error("An error unknown error occurred", e);
        }
    }

}
