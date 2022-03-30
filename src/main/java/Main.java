import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLetters;
import games.hexletters.HexLettersCreator;
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

        System.out.println("Starting a new game");
        try {
            Map<Locale, String> dictionaryPaths = new HashMap<>();
            dictionaryPaths.put(Locale.US, "src/main/resources/dictionaries/en_us_dictionary.json");
            Dictionary dictionary = new Dictionary(new DictionaryParser(new ObjectMapper(), Locale.US, dictionaryPaths));

            HexLetters hexLetters = new HexLettersCreator(dictionary).create();

            HexLettersGameManager manager = new HexLettersGameManager(hexLetters, dictionary);

            while (manager.getSuccessfulTries().size() != manager.getSolutions().size()) {
                System.out.println("Guess a word:");
                String guess = scanner.nextLine();

                if (manager.isSolution(guess)) {
                    System.out.printf("Congrats! %s is a solution. Found %s of %s\n", guess, manager.getSuccessfulTries().size(), manager.getSolutions().size());
                } else {
                    System.out.printf("Sorry, %s is not a solution, try another one!\n", guess);
                }
            }

            System.out.println("CONGRATS! You have found all solutions :)");

        } catch (ParsingError e) {
            log.error("An error unknown error occurred", e);
        }
    }

}
