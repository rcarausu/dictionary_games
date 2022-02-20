package domain.exceptions;

import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
public class ParsingError extends Exception {
    private final Locale locale;
    private final String message;
}
