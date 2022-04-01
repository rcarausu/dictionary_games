package dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.parsers.LocaleParser;
import dictionary.parsers.UsEnglishParser;
import domain.DictionaryEntry;
import domain.exceptions.ParsingError;
import lombok.Getter;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.List.of;
import static java.util.Locale.US;


public class DictionaryParser {

    @Getter
    private final Locale locale;
    @Getter
    private final String vowels;
    @Getter
    private final String consonants;

    private final ObjectMapper objectMapper;
    private final LocaleParser parser;
    private final Map<Locale, String> dictionaryPaths;


    private final static List<Locale> AVAILABLE_LOCALES = of(US);

    public DictionaryParser(ObjectMapper objectMapper, Locale locale, Map<Locale, String> dictionaryPaths) throws ParsingError {
        this.objectMapper = objectMapper;
        this.locale = locale;
        this.dictionaryPaths = dictionaryPaths;
        this.parser = initializeDictionaryParser(locale);
        this.vowels = parser.getVowels();
        this.consonants = parser.getConsonants();
    }

    private LocaleParser initializeDictionaryParser(Locale locale) throws ParsingError {
        assert AVAILABLE_LOCALES.contains(locale);

        if (locale.equals(US)) {
            return new UsEnglishParser(objectMapper, dictionaryPaths.get(locale));
        } else {
            throw new ParsingError(locale, "Locale %s is not supported");
        }
    }

    public List<DictionaryEntry> parseEntries() throws ParsingError {
        return parser.parseEntries();
    }
}
