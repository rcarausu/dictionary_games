package domain;

import domain.games.HexLettersGame;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestHexLettersGame {
    @Test
    public void shouldThrowArrayIndexOutOfBoundsException_whenInstantiatingWithWrongSize() {
        // when
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new HexLettersGame('a', Arrays.asList('d', 'e'), emptyList());
        });

        // then
        assertEquals("Extra letters size must be 6", exception.getMessage(), "Wrong instantiation");
    }
}
