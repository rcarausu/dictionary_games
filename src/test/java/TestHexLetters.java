import com.fasterxml.jackson.databind.ObjectMapper;
import domain.games.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestHexLetters {

    private final static String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    private static final String VOWELS = "aeiou";
    private static final String DICTIONARY = "src/test/resources/en_us_dictionary.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void shouldCreateNewGame() {
//        Game game = new HexLetters(VOWELS, CONSONANTS, DICTIONARY, MAPPER).newGame();
//
//        assertNotNull(game);
    }

}