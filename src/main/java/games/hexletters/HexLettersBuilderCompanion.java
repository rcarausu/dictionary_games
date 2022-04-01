package games.hexletters;

import dictionary.Dictionary;
import domain.DictionaryEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

@Slf4j
public class HexLettersBuilderCompanion {
    static final int MIN_VOWELS = 2;
    static final int MAX_VOWELS = 3;

    static final int MIN_CONSONANTS = 4;
    static final int MAX_CONSONANTS = 5;

    static final int MINIMUM_WORD_SIZE = 3;

    @Getter
    @AllArgsConstructor
    static class LetterSizes {
        private int numberOfVowels;
        private int numberOfConsonants;
    }

    static LetterSizes generateLetterSizes() {
        if (Math.random() < 0.5) {
            return new LetterSizes(MIN_VOWELS, MAX_CONSONANTS);
        }
        return new LetterSizes(MAX_VOWELS, MIN_CONSONANTS);
    }

    static List<Character> chooseLetters(String origin, int total) {
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < origin.length(); i++) {
            letters.add(origin.charAt(i));
        }
        shuffle(letters);
        return letters.subList(0, total);
    }

    static List<String> generateSolutions(final Dictionary dictionary, final char mainLetter, final List<Character> letters) {

        log.debug("Generating HexLetters solutions with main letter '{}' and letters '{}'", mainLetter, letters);

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
                    if (!word.contains(String.valueOf(mainLetter))) {
                        return false;
                    }

                    for (int i = 0; i < word.length(); i++) {
                        if (!allLetters.contains(word.charAt(i))) {
                            return false;
                        }
                    }

                    return true;
                })
                .filter(word -> word.length() >= MINIMUM_WORD_SIZE)
                .distinct()
                .collect(toList());

        if (solutions.isEmpty()) {
            log.debug("Found 0 solutions, regenerating");
        }

        return solutions;
    }

}
