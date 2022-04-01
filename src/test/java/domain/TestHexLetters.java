package domain;

import dictionary.Dictionary;
import domain.games.HexLetters;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class TestHexLetters {
    @Test
    public void shouldThrowArrayIndexOutOfBoundsException_whenInstantiatingWithWrongSize() {
        // when
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new HexLetters(UUID.randomUUID().toString(), mock(Dictionary.class), 'a', Arrays.asList('d', 'e'), emptyList());
        });

        // then
        assertEquals("Extra letters size must be 6", exception.getMessage(), "Wrong instantiation");
    }
}
