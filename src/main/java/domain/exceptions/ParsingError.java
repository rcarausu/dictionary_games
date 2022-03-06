package domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@Getter
@AllArgsConstructor
public class ParsingError extends Exception {
    private final Locale locale;
    private final String message;
}
