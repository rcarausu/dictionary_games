package dictionary.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.DictionaryEntry;
import domain.exceptions.ParsingError;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestUsEnglishDictionaryParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldParseEntries() throws ParsingError {
        // given
        String dictionaryFilePath = "src/test/resources/en_us_dictionary.json";

        // when
        LocaleParser parser = new UsEnglishParser(mapper, dictionaryFilePath);
        List<DictionaryEntry> entries = parser.parseEntries();

        // then
        assertThat(entries.size()).isEqualTo(50);
        assertThat(entries.get(0)).isEqualTo(new DictionaryEntry("entry_name", "entry definitions"));
        assertThat(parser.getConsonants()).isEqualTo("bcdfghjklmnpqrstvwxyz");
        assertThat(parser.getVowels()).isEqualTo("aeiou");
    }

    @Test
    public void shouldThrowParsingError_whenIOExceptionOccursWhileParsingDictionaryFile() {
        // given
        String dictionaryFilePath = "";

        // when
        Exception exception = assertThrows(ParsingError.class, new UsEnglishParser(mapper, dictionaryFilePath)::parseEntries);

        // then
        assertThat(exception.getClass()).isEqualTo(ParsingError.class);
        assertThat(exception.getMessage()).isEqualTo("An IO error occurred while parsing dictionary file for en_US");
    }

    @Test
    public void shouldThrowParsingError_whenSomeUnknownExceptionOccurs() throws IOException {
        // given
        String dictionaryFilePath = "src/test/resources/en_us_dictionary.json";
        ObjectMapper mapper = mock(ObjectMapper.class);

        // when
        TypeReference<Object> valueTypeRef = new TypeReference<>() {
        };
        when(mapper.readValue(new File(dictionaryFilePath), valueTypeRef)).thenThrow(new RuntimeException());

        Exception exception = assertThrows(ParsingError.class, () -> {
            new UsEnglishParser(mapper, dictionaryFilePath).parseEntries();
        });

        // then
        assertThat(exception.getClass()).isEqualTo(ParsingError.class);
        assertThat(exception.getMessage()).isEqualTo("An unknown error occurred while trying to parse entries for en_US");
    }

}