package games.hexletters;

import dictionary.Dictionary;
import domain.games.HexLetters;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static java.util.Collections.emptyList;


@Slf4j
@NoArgsConstructor
public class HexLettersBuilder extends HexLettersBuilderCompanion {
    /* TODO LIST:
        - Regenerate game if number of solutions is very low or very high (parametrize via configuration)
        - Parametrize minimum word size
     */

    private Dictionary dictionary;

    public HexLettersBuilder from(Dictionary dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    public HexLetters build() {
        List<String> solutions = emptyList();
        char mainLetter = 'a';
        List<Character> letters = emptyList();

        while (solutions.isEmpty()) {
            LetterSizes letterSizes = generateLetterSizes();
            letters = chooseLetters(dictionary.getConsonants(), letterSizes.getNumberOfConsonants());
            letters.addAll(chooseLetters(dictionary.getVowels(), letterSizes.getNumberOfVowels()));

            int mainLetterIndex = new Random().nextInt(letters.size());
            mainLetter = letters.get(mainLetterIndex);

            letters.remove(mainLetterIndex);

            solutions = generateSolutions(dictionary, mainLetter, letters);
        }

        String gameID = UUID.randomUUID().toString();
        log.info("Created HexLetters game {} with {} solutions, [{}]", gameID, solutions.size(), solutions);

        return new HexLetters(gameID, dictionary, mainLetter, letters, solutions);
    }

}
