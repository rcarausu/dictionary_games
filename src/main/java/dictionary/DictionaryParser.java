package dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.parsers.LocaleParser;
import dictionary.parsers.UsEnglishParser;
import domain.DictionaryEntry;
import domain.exceptions.ParsingError;
import lombok.Getter;

import java.util.List;
import java.util.Locale;

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


    private final static List<Locale> AVAILABLE_LOCALES = of(US);

    public DictionaryParser(ObjectMapper objectMapper, Locale locale) throws ParsingError {
        this.locale = locale;
        this.objectMapper = objectMapper;
        this.parser = initializeDictionaryParser(locale);
        this.vowels = parser.getVowels();
        this.consonants = parser.getConsonants();
    }

    private LocaleParser initializeDictionaryParser(Locale locale) throws ParsingError {
        assert AVAILABLE_LOCALES.contains(locale);

        if (locale.equals(US)) {
            // TODO: extract file path into configuration file
            return new UsEnglishParser(objectMapper, "src/main/resources/dictionaries/en_us_dictionary.json");
        } else {
            throw new ParsingError(locale, "Locale %s is not supported");
        }
    }

    public List<DictionaryEntry> retrieveEntries() throws ParsingError {
        return parser.parseEntries();
    }
}
