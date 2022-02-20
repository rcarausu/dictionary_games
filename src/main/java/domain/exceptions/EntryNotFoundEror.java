package domain.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntryNotFoundEror extends Exception {
    private final String message;
}
