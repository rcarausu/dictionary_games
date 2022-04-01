package dictionary;

import domain.DictionaryEntry;
import domain.exceptions.EntryNotFoundError;
import domain.exceptions.ParsingError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TestDictionary {

    public static final DictionaryEntry ENTRY = new DictionaryEntry("blah", "bleh");
    @Mock
    private DictionaryParser parser;

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
    public void shouldReturnTrue_whenDictionaryHasEntry() throws ParsingError {
        // when
        when(parser.parseEntries()).thenReturn(singletonList(new DictionaryEntry("blah", "bleh")));

        // then
        assertThat(new Dictionary(parser).hasEntry("blah")).isTrue();
    }

    @Test
    public void shouldReturnFalse_whenDictionaryDoesNotHaveEntry() throws ParsingError {
        // when
        when(parser.parseEntries()).thenReturn(emptyList());

        // then
        assertThat(new Dictionary(parser).hasEntry("blah")).isFalse();
    }

    @Test
    public void shouldReturnEntry() throws ParsingError, EntryNotFoundError {
        // when
        when(parser.parseEntries()).thenReturn(singletonList(ENTRY));

        // then
        assertThat(new Dictionary(parser).getEntry("blah")).isEqualTo(ENTRY);
    }

    @Test
    public void shouldThrowEntryNotFound() throws ParsingError {
        // when
        when(parser.parseEntries()).thenReturn(emptyList());
        when(parser.getLocale()).thenReturn(Locale.US);

        Exception exception = assertThrows(EntryNotFoundError.class, () -> new Dictionary(parser).getEntry("blep"));

        // then
        assertThat(exception.getClass()).isEqualTo(EntryNotFoundError.class);
        assertThat(exception.getMessage()).isEqualTo("Word 'blep' not found in en_US dictionary");
    }

}