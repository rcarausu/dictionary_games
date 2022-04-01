package dictionary;

import domain.DictionaryEntry;
import domain.exceptions.EntryNotFoundError;
import domain.exceptions.ParsingError;
import lombok.Getter;

import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

@Getter
public class Dictionary {

    private final Locale locale;
    private final String vowels;
    private final String consonants;
    private final List<DictionaryEntry> entries;

    public Dictionary(DictionaryParser parser) throws ParsingError {
        this.locale = parser.getLocale();
        this.consonants = parser.getConsonants();
        this.vowels = parser.getVowels();
        this.entries = parser.parseEntries();
    }

    public boolean hasEntry(String word) {
        return entries.stream().anyMatch(e -> e.getWord().equals(word));
    }

    public DictionaryEntry getEntry(String word) throws EntryNotFoundError {
        return entries.stream()
                .filter(e -> e.getWord().equals(word))
                .findFirst()
                .orElseThrow(() -> new EntryNotFoundError(format("Word '%s' not found in %s dictionary", word, locale)));
    }
}
