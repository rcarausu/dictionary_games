package games;

import dictionary.Dictionary;
import domain.DictionaryEntry;
import domain.games.HexLettersGame;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;


@Slf4j
@AllArgsConstructor
public class HexLetters implements GameCreator {
    /* TODO LIST:
        - Randomize number of vowels between 2 and 3 and consonants between 5-4, depending on what number of vowels chosen.
        - Regenerate game if number of solutions is very low or very high (parametrize via configuration)
        - Parametrize minimum word size
     */

    public static final int MAX_VOWELS = 2;
    public static final int MAX_CONSONANTS = 5;
    public static final int MINIMUM_WORD_SIZE = 3;

    private Dictionary dictionary;

    private List<String> generateSolutions(char mainLetter, List<Character> letters) {

        List<Character> allLetters = new ArrayList<>(letters);
        allLetters.add(mainLetter);

        List<DictionaryEntry> entries = dictionary.getEntries();
        List<String> solutions = allLetters.stream()
                .map(letter -> entries.stream()
                        .map(DictionaryEntry::getWord)
                        .sorted()
                        .filter(word -> word.charAt(0) == letter)
                        .collect(toList())
                )
                .flatMap(List::stream)
                .filter(word -> {
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) != mainLetter && !letters.contains(word.charAt(i))) {
                            return false;
                        }
                    }
                    return true;
                })
                .filter(word -> word.length() >= MINIMUM_WORD_SIZE)
                .distinct()
                .collect(toList());

        log.info("Found {} solutions", solutions.size());

        return solutions;
    }

    private List<Character> chooseLetters(String origin, int total) {
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < origin.length(); i++) {
            letters.add(origin.charAt(i));
        }
        shuffle(letters);
        return letters.subList(0, total);
    }

    public HexLettersGame create() {
        List<Character> letters = chooseLetters(dictionary.getConsonants(), MAX_CONSONANTS);
        letters.addAll(chooseLetters(dictionary.getVowels(), MAX_VOWELS));

        int mainLetterIndex = new Random().nextInt(letters.size());
        char mainLetter = letters.get(mainLetterIndex);

        letters.remove(mainLetterIndex);

        log.info(format("Creating HexLetters game with main letter '%s' and letters '%s'", mainLetter, letters));

        return new HexLettersGame(mainLetter, letters, generateSolutions(mainLetter, letters));
    }

}
