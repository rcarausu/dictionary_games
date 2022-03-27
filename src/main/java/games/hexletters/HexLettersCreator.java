package games.hexletters;

import dictionary.Dictionary;
import domain.DictionaryEntry;
import domain.games.HexLetters;
import games.GameCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.emptyList;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;


@Slf4j
@AllArgsConstructor
public class HexLettersCreator implements GameCreator {
    /* TODO LIST:
        - Regenerate game if number of solutions is very low or very high (parametrize via configuration)
        - Parametrize minimum word size
     */

    public static final int MIN_VOWELS = 2;
    public static final int MAX_VOWELS = 3;

    public static final int MIN_CONSONANTS = 4;
    public static final int MAX_CONSONANTS = 5;

    public static final int MINIMUM_WORD_SIZE = 3;

    @Getter
    @AllArgsConstructor
    private static class LetterSizes {
        private int numberOfVowels;
        private int numberOfConsonants;
    }

    private static LetterSizes generateLetterSizes() {
        if (Math.random() < 0.5) {
            return new LetterSizes(MIN_VOWELS, MAX_CONSONANTS);
        }
        return new LetterSizes(MAX_VOWELS, MIN_CONSONANTS);
    }

    private Dictionary dictionary;

    private List<String> generateSolutions(char mainLetter, List<Character> letters) {

        log.info("Generating HexLetters solutions with main letter '{}' and letters '{}'", mainLetter, letters);

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

        if (solutions.isEmpty()) {
            log.info("Found 0 solutions, regenerating");
        }

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

    public HexLetters create() {
        List<String> solutions = emptyList();
        char mainLetter = 'a';
        List<Character> letters = emptyList();

        while (solutions.isEmpty()) {
            LetterSizes letterSizes = generateLetterSizes();
            letters = chooseLetters(dictionary.getConsonants(), letterSizes.getNumberOfConsonants());
            letters.addAll(chooseLetters(dictionary.getVowels(), MAX_VOWELS));

            int mainLetterIndex = new Random().nextInt(letters.size());
            mainLetter = letters.get(mainLetterIndex);

            letters.remove(mainLetterIndex);

            solutions = generateSolutions(mainLetter, letters);
        }

        log.info("Created HexLetters game with solutions '{}''", solutions);

        return new HexLetters(mainLetter, letters, solutions);
    }

}
