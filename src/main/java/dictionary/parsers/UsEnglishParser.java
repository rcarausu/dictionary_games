package dictionary.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.DictionaryEntry;
import domain.exceptions.ParsingError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Locale.US;
import static java.util.stream.Collectors.toList;

@Slf4j
@AllArgsConstructor
public class UsEnglishParser implements LocaleParser {

    private final ObjectMapper objectMapper;
//    private final String dictionaryPath;

    private static final String VOWELS = "aeiou";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    private static final String DICTIONARY_PATH = "src/main/resources/en_us_dictionary.json";

    @Override
    public List<DictionaryEntry> parseEntries() throws ParsingError {
        try {
            Map<String, String> entries = objectMapper.readValue(new File(DICTIONARY_PATH), new TypeReference<>() {
            });
            return entries.keySet().stream().map(key -> new DictionaryEntry(key, entries.get(key))).collect(toList());
        } catch (IOException e) {
            String message = format("An error occurred while parsing dictionary file for %s ", US);
            log.error(message, e);
            throw new ParsingError(US, message);
        } catch (Exception e) {
            String message = format("An unknown error occurred while trying to parse entries for %s ", US);
            log.error(message, e);
            throw new ParsingError(US, message);
        }
    }

    @Override
    public String getVowels() {
        return VOWELS;
    }

    @Override
    public String getConsonants() {
        return CONSONANTS;
    }
}
