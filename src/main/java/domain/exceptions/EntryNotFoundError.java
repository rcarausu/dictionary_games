package domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntryNotFoundError extends Exception {
    private final String message;
}
