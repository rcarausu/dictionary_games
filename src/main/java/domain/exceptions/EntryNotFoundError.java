package domain.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntryNotFoundError extends Exception {
    private final String message;
}
