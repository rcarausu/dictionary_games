package dictionary;

import domain.DictionaryEntry;
import domain.exceptions.EntryNotFoundEror;
import domain.exceptions.ParsingError;
import lombok.Getter;

import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

public class Dictionary {

    private final Locale locale;
    @Getter
    private final List<DictionaryEntry> entries;
    @Getter
    private final String vowels;
    @Getter
    private final String consonants;

    public Dictionary(DictionaryParser dictionaryParser) throws ParsingError {
        this.locale = dictionaryParser.getLocale();
        this.entries = dictionaryParser.retrieveEntries();
        this.vowels = dictionaryParser.getVowels();
        this.consonants = dictionaryParser.getConsonants();
    }

    public boolean hasWord(String word) {
        return entries.stream().anyMatch(e -> e.getWord().equals(word));
    }

    public DictionaryEntry getEntry(String word) throws EntryNotFoundEror {
        return entries.stream()
                .filter(e -> e.getWord().equals(word))
                .findFirst()
                .orElseThrow(() -> new EntryNotFoundEror(format("Word %s not found in %s dictionary", word, locale)));
    }
}
