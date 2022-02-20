package dictionary.parsers;

import domain.DictionaryEntry;
import domain.exceptions.ParsingError;

import java.util.List;

public interface LocaleParser {
    List<DictionaryEntry> parseEntries() throws ParsingError;
    String getVowels();
    String getConsonants();
}
