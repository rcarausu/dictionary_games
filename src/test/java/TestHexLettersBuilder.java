import com.fasterxml.jackson.databind.ObjectMapper;
import dictionary.Dictionary;
import dictionary.DictionaryParser;
import domain.exceptions.ParsingError;
import domain.games.HexLetters;
import games.hexletters.HexLettersBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Locale;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TestHexLettersBuilder {

    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldCreateNewGameWithSolutions() throws ParsingError {
        // when
        Dictionary dictionary = new Dictionary(new DictionaryParser(
                new ObjectMapper(), Locale.US, singletonMap(Locale.US, "src/test/resources/en_us_dictionary.json")
        ));

        HexLetters game = new HexLettersBuilder().from(dictionary).build();

        // then
        assertThat(game.getSolutions().size()).isNotZero();
    }

}