package domain.games;

import dictionary.Dictionary;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

import static java.lang.String.format;

@Getter
public class HexLetters extends Game {

    public static final int MAX_EXTRA_LETTERS = 6;

    @NonNull
    private final String id;

    @NonNull
    private final Dictionary dictionary;

    @NonNull
    private final char mainLetter;
    @NonNull
    private final List<Character> extraLetters;
    @NonNull
    private final List<String> solutions;

    public HexLetters(String id, Dictionary dictionary, char mainLetter, List<Character> extraLetters, List<String> solutions) {
        if (extraLetters.size() != MAX_EXTRA_LETTERS) {
            throw new ArrayIndexOutOfBoundsException(format("Extra letters size must be %s", MAX_EXTRA_LETTERS));
        }

        this.id = id;
        this.dictionary = dictionary;
        this.mainLetter = mainLetter;
        this.extraLetters = extraLetters;
        this.solutions = solutions;
    }

}
