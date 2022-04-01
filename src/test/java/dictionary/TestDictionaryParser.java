package dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.exceptions.ParsingError;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TestDictionaryParser {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Locale, String> dictionaryPaths = singletonMap(Locale.US, "src/test/resources/en_us_dictionary.json");

    @Test
    public void shouldInstantiateDictionaryParser() throws ParsingError {
        // when
        DictionaryParser parser = new DictionaryParser(mapper, Locale.US, dictionaryPaths);

        // then
        assertThat(parser.parseEntries().size()).isEqualTo(50);
        assertThat(parser.getConsonants()).isEqualTo("bcdfghjklmnpqrstvwxyz");
        assertThat(parser.getVowels()).isEqualTo("aeiou");
        assertThat(parser.getLocale()).isEqualTo(Locale.US);
    }

}