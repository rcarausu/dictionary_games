import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLetters;
import games.hexletters.HexLettersCreator;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        log.debug("Starting a new game");
        try {
            Map<Locale, String> dictionaryPaths = new HashMap<>();
            dictionaryPaths.put(Locale.US, "src/main/resources/dictionaries/en_us_dictionary.json");
            Dictionary dictionary = new Dictionary(new DictionaryParser(new ObjectMapper(), Locale.US, dictionaryPaths));

            HexLetters hexLetters = new HexLettersCreator(dictionary).create();

            log.debug("New game created with main letter {}, extra letters {} and {} solutions: {}",
                    hexLetters.getMainLetter(), hexLetters.getExtraLetters(), hexLetters.getSolutions().size(), hexLetters.getSolutions());

            System.out.println("Write a solution");
            String solution = scanner.nextLine();
            if (hexLetters.getSolutions().contains(solution)) {
                System.out.printf("Congrats! %s is a solution", solution);
            } else {
                System.out.printf("Sorry, %s is not a solution", solution);
            }
        } catch (ParsingError e) {
            log.error("An error unknown error occurred", e);
        }
    }

}
